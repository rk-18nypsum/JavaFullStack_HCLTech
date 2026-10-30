package com.hcl.library.model;

public class Book extends LibraryItem {
    private static final double DAILY_FEE = 2.0;

    private String author;
    private String isbn;

    public Book(String title, int year, String author, String isbn) {
        super(title, year);
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be blank");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be blank");
        }
        this.author = author.trim();
        this.isbn = isbn.trim();
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate * DAILY_FEE;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Book[" + super.toString() + ", " + author + "]";
    }
}
