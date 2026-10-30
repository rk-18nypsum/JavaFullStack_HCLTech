package com.hcl.library;

import com.hcl.library.exception.BorrowLimitExceededException;
import com.hcl.library.exception.ItemNotAvailableException;
import com.hcl.library.exception.ItemNotFoundException;
import com.hcl.library.exception.LibraryException;
import com.hcl.library.model.Book;
import com.hcl.library.model.DVD;
import com.hcl.library.model.LibraryItem;
import com.hcl.library.model.Magazine;
import com.hcl.library.model.Member;
import com.hcl.library.service.LibraryService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static LibraryService service = new LibraryService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initData();

        while (true) {
            System.out.println("===== LIBRARY MENU =====");
            System.out.println("1. Add item   2. Register member   3. Borrow   4. Return");
            System.out.println("5. Waitlist   6. List items        7. Search   8. Export report   0. Exit");
            System.out.print("Choice: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Enter a valid number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Exiting library system.");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        registerMember();
                        break;
                    case 3:
                        borrowItem();
                        break;
                    case 4:
                        returnItem();
                        break;
                    case 5:
                        joinWaitlist();
                        break;
                    case 6:
                        listItems();
                        break;
                    case 7:
                        searchItem();
                        break;
                    case 8:
                        exportReport();
                        break;
                    default:
                        System.out.println("ERROR: Invalid choice.");
                }
            } catch (ItemNotAvailableException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (ItemNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (BorrowLimitExceededException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (LibraryException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
    }

    private static void initData() {
        service.addItem(new Book("Clean Code", 2008, "Robert Martin", "978-0132350884"));
        service.addItem(new Magazine("Java Magazine", 2023, 42));
        service.addItem(new DVD("Inception", 2010, 148));

        service.registerMember(new Member(1, "Alice", "alice@example.com"));
        service.registerMember(new Member(2, "Ravi", "ravi@example.com"));
    }

    private static void addItem() {
        System.out.println("Select type: 1. Book  2. Magazine  3. DVD");
        int type = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Year: ");
        int year = Integer.parseInt(sc.nextLine().trim());

        if (type == 1) {
            System.out.print("Author: ");
            String author = sc.nextLine().trim();
            System.out.print("ISBN: ");
            String isbn = sc.nextLine().trim();
            service.addItem(new Book(title, year, author, isbn));
            System.out.println("Book added successfully.");
        } else if (type == 2) {
            System.out.print("Issue number: ");
            int issue = Integer.parseInt(sc.nextLine().trim());
            service.addItem(new Magazine(title, year, issue));
            System.out.println("Magazine added successfully.");
        } else if (type == 3) {
            System.out.print("Duration in minutes: ");
            int duration = Integer.parseInt(sc.nextLine().trim());
            service.addItem(new DVD(title, year, duration));
            System.out.println("DVD added successfully.");
        } else {
            System.out.println("ERROR: Invalid item type.");
        }
    }

    private static void registerMember() {
        System.out.print("Member id: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        service.registerMember(new Member(id, name, email));
        System.out.println("Member registered successfully.");
    }

    private static void borrowItem() {
        System.out.print("Member id: ");
        int memberId = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine().trim());

        try {
            LibraryItem item = service.borrowItem(memberId, itemId);
            System.out.println("Issued: " + item);
        } catch (ItemNotAvailableException e) {
            System.out.print("ERROR: Item " + itemId + " is not available. Added to waitlist? (y/n): ");
            String ans = sc.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                service.addToWaitlist(itemId, memberId);
                System.out.println("Added to waitlist.");
            }
        }
    }

    private static void returnItem() {
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Late by (days): ");
        int days = Integer.parseInt(sc.nextLine().trim());

        double fee = service.returnItem(itemId, days);
        System.out.println("Late fee: " + fee);

        Member next = service.getNextWaitlistMember(itemId);
        if (next != null) {
            System.out.println("Notification: Member " + next.getId() + " (" + next.getName() + ") is next in the waitlist for " + itemId + ".");
        }
    }

    private static void joinWaitlist() {
        System.out.print("Item id: ");
        int itemId = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Member id: ");
        int memberId = Integer.parseInt(sc.nextLine().trim());

        service.addToWaitlist(itemId, memberId);
        System.out.println("Added to waitlist for item " + itemId + ".");
    }

    private static void listItems() {
        System.out.println("Select sort: 1. By title (natural)  2. By year descending, then title");
        int sort = Integer.parseInt(sc.nextLine().trim());

        List<LibraryItem> items = (sort == 2) ? service.getItemsSortedByYearAndTitle() : service.getItemsSortedByTitle();
        for (LibraryItem item : items) {
            String status = item.isAvailable() ? "Available" : "Borrowed";
            System.out.println(item + " [" + status + "]");
        }

        System.out.println("--- Item Statistics ---");
        Map<String, Integer> stats = service.getItemTypeStatistics();
        for (Map.Entry<String, Integer> e : stats.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    private static void searchItem() {
        System.out.print("Search title keyword: ");
        String keyword = sc.nextLine().trim();

        List<LibraryItem> list = service.searchItems(keyword);
        if (list.isEmpty()) {
            System.out.println("No items found.");
        } else {
            for (LibraryItem item : list) {
                System.out.println(item);
            }
        }
        System.out.println("Recent searches (LRU cache): " + service.getSearchCache().getRecentSearches());
    }

    private static void exportReport() {
        System.out.print("Export file path (e.g. report.txt): ");
        String path = sc.nextLine().trim();
        if (path.isEmpty()) {
            path = "report.txt";
        }
        service.exportReport(path);
        System.out.println("Report exported successfully to " + path);
    }
}
