package Assesments.Assesment2;

public class Magzine extends LibraryItem {
    private int issueNumber;
    private String publisher;

    public Magzine(int id, String title, int year) {
        super(id, title, year);
        this.issueNumber = 1;
        this.publisher = "Unknown Publisher";
    }

    public Magzine(int id, String title, int year, int issueNumber, String publisher) {
        super(id, title, year);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public void displayDetails() {
        System.out.println("----- Magazine Details -----");
        displayItem();
        System.out.println("Issue Number: #" + issueNumber);
        System.out.println("Publisher: " + publisher);
        System.out.println("----------------------------");
    }

    @Override
    public String toString() {
        return "Magzine [ID=" + getId() + ", Title=" + getTitle() + ", Issue=#" + issueNumber + ", Publisher=" + publisher + ", Year=" + getYear() + ", Available=" + isAvailable() + "]";
    }
}
