package Assesments.Assesment2;

public class Book extends LibraryItem {
    private String author;
    private String genre;

    public Book(int id, String title, int year) {
        super(id, title, year);
        this.author = "Unknown Author";
        this.genre = "General";
    }

    public Book(int id, String title, int year, String author) {
        super(id, title, year);
        this.author = author;
        this.genre = "General";
    }

    public Book(int id, String title, int year, String author, String genre) {
        super(id, title, year);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void displayDetails() {
        System.out.println("----- Book Details -----");
        displayItem();
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("------------------------");
    }

    @Override
    public String toString() {
        return "Book [ID=" + getId() + ", Title=" + getTitle() + ", Author=" + author + ", Genre=" + genre + ", Year=" + getYear() + ", Available=" + isAvailable() + "]";
    }

    public static void main(String[] args) {
        Book book = new Book(101, "Effective Java", 2018, "Joshua Bloch", "Programming");
        book.addItem();
        book.registerItem();
        book.displayDetails();
    }
}
