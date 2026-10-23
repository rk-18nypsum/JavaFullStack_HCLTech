package com.hcl.library.model;

import java.time.Year;
import java.util.Objects;

public abstract class LibraryItem implements Borrowable, Comparable<LibraryItem> {
    private static int idCounter = 1001;

    private int id;
    private String title;
    private int year;
    private boolean available;

    public LibraryItem(String title, int year) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear) {
            throw new IllegalArgumentException("Year must be between 1900 and " + currentYear);
        }
        this.id = idCounter++;
        this.title = title.trim();
        this.year = year;
        this.available = true;
    }

    public abstract double lateFee(int daysLate);

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        this.available = false;
    }

    @Override
    public void giveBack() {
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(LibraryItem other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem)) return false;
        LibraryItem that = (LibraryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ", " + title + ", " + year;
    }
}
