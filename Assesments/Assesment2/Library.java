package Assesments.Assesment2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private String libraryName;
    private List<LibraryItem> items;
    private List<Member> members;

    public Library() {
        this("HCL Central Library");
    }

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    public List<Member> getMembers() {
        return members;
    }

    // ================= Item Management =================

    public void addItem(LibraryItem item) {
        if (item == null) {
            System.out.println("Error: Cannot add null item.");
            return;
        }
        if (searchItemById(item.getId()) != null) {
            System.out.println("Error: Item with ID " + item.getId() + " already exists in library.");
            return;
        }
        items.add(item);
        item.registerItem();
    }

    public boolean removeItem(int id) {
        LibraryItem item = searchItemById(id);
        if (item == null) {
            System.out.println("Error: Item with ID " + id + " not found.");
            return false;
        }
        if (!item.isAvailable()) {
            System.out.println("Error: Item '" + item.getTitle() + "' is currently borrowed and cannot be deleted.");
            return false;
        }
        items.remove(item);
        item.deleteItem();
        return true;
    }

    public LibraryItem searchItemById(int id) {
        for (LibraryItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<LibraryItem> searchItemsByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayAllItems() {
        System.out.println("\n========== All Library Items in " + libraryName + " ==========");
        if (items.isEmpty()) {
            System.out.println("No items in the library catalog.");
            return;
        }
        for (LibraryItem item : items) {
            item.displayDetails();
        }
        System.out.println("Total Items: " + items.size());
    }

    public void displayAvailableItems() {
        System.out.println("\n========== Available Items in " + libraryName + " ==========");
        List<LibraryItem> availableItems = items.stream()
                .filter(LibraryItem::isAvailable)
                .collect(Collectors.toList());

        if (availableItems.isEmpty()) {
            System.out.println("No items currently available.");
            return;
        }

        for (LibraryItem item : availableItems) {
            item.displayDetails();
        }
        System.out.println("Total Available: " + availableItems.size() + " / " + items.size());
    }

    public List<LibraryItem> filterItemsByYear(int year) {
        return items.stream()
                .filter(item -> item.getYear() == year)
                .collect(Collectors.toList());
    }

    // ================= Member Management =================

    public void registerMember(Member member) {
        if (member == null) {
            System.out.println("Error: Cannot register null member.");
            return;
        }
        if (searchMemberById(member.getMemberId()) != null) {
            System.out.println("Error: Member with ID " + member.getMemberId() + " already exists.");
            return;
        }
        members.add(member);
        System.out.println("Member '" + member.getName() + "' (ID: " + member.getMemberId() + ") registered successfully.");
    }

    public boolean removeMember(int memberId) {
        Member member = searchMemberById(memberId);
        if (member == null) {
            System.out.println("Error: Member with ID " + memberId + " not found.");
            return false;
        }
        if (member.getBorrowedCount() > 0) {
            System.out.println("Error: Member has " + member.getBorrowedCount() + " unreturned items. Cannot remove member.");
            return false;
        }
        members.remove(member);
        System.out.println("Member '" + member.getName() + "' removed successfully.");
        return true;
    }

    public Member searchMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void displayAllMembers() {
        System.out.println("\n========== Registered Members in " + libraryName + " ==========");
        if (members.isEmpty()) {
            System.out.println("No registered members.");
            return;
        }
        for (Member member : members) {
            System.out.println(member);
            member.displayBorrowedItems();
        }
        System.out.println("Total Members: " + members.size());
    }

    // ================= Borrow & Return Operations =================

    public boolean borrowItem(int memberId, int itemId) {
        Member member = searchMemberById(memberId);
        if (member == null) {
            System.out.println("Borrow Failed: Member ID " + memberId + " not found.");
            return false;
        }

        LibraryItem item = searchItemById(itemId);
        if (item == null) {
            System.out.println("Borrow Failed: Item ID " + itemId + " not found.");
            return false;
        }

        if (!item.isAvailable()) {
            System.out.println("Borrow Failed: '" + item.getTitle() + "' is currently unavailable / already borrowed.");
            return false;
        }

        if (!member.canBorrow()) {
            System.out.println("Borrow Failed: " + member.getName() + " has reached borrowing limit (" + member.getMaxBooks() + " items max).");
            return false;
        }

        item.setAvailable(false);
        member.borrowItem(item);
        System.out.println("SUCCESS: '" + item.getTitle() + "' borrowed by " + member.getName() + ".");
        return true;
    }

    public boolean returnItem(int memberId, int itemId) {
        Member member = searchMemberById(memberId);
        if (member == null) {
            System.out.println("Return Failed: Member ID " + memberId + " not found.");
            return false;
        }

        LibraryItem item = searchItemById(itemId);
        if (item == null) {
            System.out.println("Return Failed: Item ID " + itemId + " not found.");
            return false;
        }

        boolean returned = member.returnItem(item);
        if (!returned) {
            System.out.println("Return Failed: " + member.getName() + " does not have '" + item.getTitle() + "' borrowed.");
            return false;
        }

        item.setAvailable(true);
        System.out.println("SUCCESS: '" + item.getTitle() + "' returned successfully by " + member.getName() + ".");
        return true;
    }
}
