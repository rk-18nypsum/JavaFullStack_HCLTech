package Assesments.Assesment2;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private int memberId;
    private String name;
    private String phone;
    private int maxBooks;
    private List<LibraryItem> borrowedItems;

    public Member(int memberId, String name, String phone, int maxBooks) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.maxBooks = maxBooks;
        this.borrowedItems = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public int getBorrowedCount() {
        return borrowedItems.size();
    }

    public boolean canBorrow() {
        return borrowedItems.size() < maxBooks;
    }

    public boolean borrowItem(LibraryItem item) {
        if (canBorrow()) {
            borrowedItems.add(item);
            return true;
        }
        return false;
    }

    public boolean returnItem(LibraryItem item) {
        return borrowedItems.remove(item);
    }

    public void displayBorrowedItems() {
        System.out.println("Items currently borrowed by " + name + " (ID: " + memberId + "):");
        if (borrowedItems.isEmpty()) {
            System.out.println("  No items currently borrowed.");
        } else {
            for (LibraryItem item : borrowedItems) {
                System.out.println("  - [ID: " + item.getId() + "] " + item.getTitle() + " (" + item.getClass().getSimpleName() + ")");
            }
        }
    }

    @Override
    public String toString() {
        return "Member [ID=" + memberId + ", Name=" + name + ", Phone=" + phone + ", Borrowed=" + borrowedItems.size() + "/" + maxBooks + "]";
    }
}
