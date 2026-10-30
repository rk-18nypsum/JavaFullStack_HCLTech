package Assesments.Assesment2;

public abstract class LibraryItem {
    private int id;
    private String title;
    private int year;
    private boolean available;

    public LibraryItem(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.available = true;
    }

    public LibraryItem(int id, String title, int year, boolean available) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.available = available;
    }

    public void addItem() {
        System.out.println("'" + title + "' added to library.");
    }

    public void deleteItem() {
        System.out.println("'" + title + "' deleted from library.");
    }

    public void registerItem() {
        System.out.println("'" + title + "' registered successfully.");
    }

    public abstract void displayDetails();

    public void displayItem() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Available: " + (available ? "Yes" : "No"));
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "LibraryItem [ID=" + id + ", Title=" + title + ", Year=" + year + ", Available=" + available + "]";
    }
}
