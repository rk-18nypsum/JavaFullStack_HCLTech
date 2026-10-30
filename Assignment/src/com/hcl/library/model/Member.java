package com.hcl.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String email;
    private List<LibraryItem> borrowedItems;

    public Member(int id, String name, String email) {
        this(id, name, email, new ArrayList<>());
    }

    public Member(int id, String name, String email, List<LibraryItem> borrowedItems) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email: must contain @");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email.trim();
        this.borrowedItems = new ArrayList<>(borrowedItems);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<LibraryItem> getBorrowedItems() {
        return Collections.unmodifiableList(borrowedItems);
    }

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    @Override
    public String toString() {
        return "Member[" + id + ", " + name + ", " + email + "]";
    }
}
