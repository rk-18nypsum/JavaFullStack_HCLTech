package com.hcl.library.service;

import com.hcl.library.exception.BorrowLimitExceededException;
import com.hcl.library.exception.ItemNotAvailableException;
import com.hcl.library.exception.ItemNotFoundException;
import com.hcl.library.exception.LibraryException;
import com.hcl.library.model.Book;
import com.hcl.library.model.LibraryItem;
import com.hcl.library.model.Member;
import com.hcl.library.repository.InMemoryRepository;
import com.hcl.library.repository.Repository;
import com.hcl.library.util.SearchCache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LibraryService {
    public static final Comparator<LibraryItem> BY_YEAR_THEN_TITLE = 
            Comparator.comparing(LibraryItem::getYear).reversed()
                    .thenComparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER);

    private Repository<LibraryItem, Integer> itemRepo = new InMemoryRepository<>(LibraryItem::getId);
    private Repository<Member, Integer> memberRepo = new InMemoryRepository<>(Member::getId);
    private Set<String> isbns = new HashSet<>();
    private Map<Integer, Queue<Member>> waitlist = new HashMap<>();
    private SearchCache searchCache = new SearchCache();

    public void addItem(LibraryItem item) {
        if (item instanceof Book) {
            Book b = (Book) item;
            if (isbns.contains(b.getIsbn())) {
                throw new IllegalArgumentException("ISBN " + b.getIsbn() + " already exists");
            }
            isbns.add(b.getIsbn());
        }
        itemRepo.save(item);
    }

    public void registerMember(Member member) {
        memberRepo.save(member);
    }

    public LibraryItem borrowItem(int memberId, int itemId) {
        Member m = memberRepo.findById(memberId)
                .orElseThrow(() -> new ItemNotFoundException("Member " + memberId + " not found"));
        LibraryItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found"));

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item " + itemId + " is not available");
        }
        if (m.getBorrowedItems().size() >= 3) {
            throw new BorrowLimitExceededException("Member " + memberId + " reached borrow limit");
        }

        item.borrow();
        m.borrowItem(item);
        return item;
    }

    public double returnItem(int itemId, int daysLate) {
        LibraryItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found"));

        item.giveBack();
        for (Member m : memberRepo.findAll()) {
            if (m.getBorrowedItems().contains(item)) {
                m.returnItem(item);
                break;
            }
        }
        return item.lateFee(daysLate);
    }

    public void addToWaitlist(int itemId, int memberId) {
        itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found"));
        Member m = memberRepo.findById(memberId)
                .orElseThrow(() -> new ItemNotFoundException("Member " + memberId + " not found"));

        if (!waitlist.containsKey(itemId)) {
            waitlist.put(itemId, new ArrayDeque<>());
        }
        waitlist.get(itemId).offer(m);
    }

    public Member getNextWaitlistMember(int itemId) {
        Queue<Member> q = waitlist.get(itemId);
        return (q != null && !q.isEmpty()) ? q.poll() : null;
    }

    public List<LibraryItem> getItemsSortedByTitle() {
        List<LibraryItem> list = new ArrayList<>(itemRepo.findAll());
        Collections.sort(list);
        return Collections.unmodifiableList(list);
    }

    public List<LibraryItem> getItemsSortedByYearAndTitle() {
        List<LibraryItem> list = new ArrayList<>(itemRepo.findAll());
        list.sort(BY_YEAR_THEN_TITLE);
        return Collections.unmodifiableList(list);
    }

    public void removeOldItems(int beforeYear) {
        List<LibraryItem> list = new ArrayList<>(itemRepo.findAll());
        Iterator<LibraryItem> it = list.iterator();
        while (it.hasNext()) {
            LibraryItem item = it.next();
            if (item.getYear() < beforeYear) {
                it.remove();
                itemRepo.deleteById(item.getId());
                if (item instanceof Book) {
                    isbns.remove(((Book) item).getIsbn());
                }
            }
        }
    }

    public List<LibraryItem> searchItems(String keyword) {
        searchCache.recordSearch(keyword);
        List<LibraryItem> res = new ArrayList<>();
        for (LibraryItem item : itemRepo.findAll()) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(item);
            }
        }
        return Collections.unmodifiableList(res);
    }

    public Map<String, Integer> getItemTypeStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        for (LibraryItem item : itemRepo.findAll()) {
            stats.merge(item.getClass().getSimpleName(), 1, Integer::sum);
        }
        return Collections.unmodifiableMap(stats);
    }

    public void exportReport(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            StringBuilder sb = new StringBuilder();
            for (LibraryItem item : itemRepo.findAll()) {
                sb.append(item.toString())
                  .append(" - ")
                  .append(item.isAvailable() ? "Available" : "Borrowed")
                  .append("\n");
            }
            bw.write(sb.toString());
        } catch (IOException e) {
            throw new LibraryException("Export failed for " + path, e);
        }
    }

    public List<LibraryItem> getAllItems() {
        return itemRepo.findAll();
    }

    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }

    public SearchCache getSearchCache() {
        return searchCache;
    }
}
