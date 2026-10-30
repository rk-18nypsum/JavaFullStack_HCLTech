package Assesments.Assesment2;

public class Dvd extends LibraryItem {
    private String director;
    private int duration; // in minutes

    public Dvd(int id, String title, int year) {
        super(id, title, year);
        this.director = "Unknown Director";
        this.duration = 0;
    }

    public Dvd(int id, String title, int year, String director, int duration) {
        super(id, title, year);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void displayDetails() {
        System.out.println("----- DVD Details -----");
        displayItem();
        System.out.println("Director: " + director);
        System.out.println("Duration: " + duration + " mins");
        System.out.println("-----------------------");
    }

    @Override
    public String toString() {
        return "DVD [ID=" + getId() + ", Title=" + getTitle() + ", Director=" + director + ", Duration=" + duration + " mins, Year=" + getYear() + ", Available=" + isAvailable() + "]";
    }
}
