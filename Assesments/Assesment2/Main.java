package Assesments.Assesment2;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library("City Knowledge Hub");
        seedSampleData(library);

        // Check if there is console input available for interactive mode
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("   WELCOME TO LIBRARY MANAGEMENT SYSTEM (ASSESSMENT 2)   ");
        System.out.println("==========================================================");

        boolean forceDemo = argsContainsOnlyDemo(args);
        if (forceDemo) {
            System.out.println("[Running Complete Automated Demonstration]\n");
            runDemo(library);
            return;
        }

        runInteractiveMenu(library, scanner);
    }

    private static boolean argsContainsOnlyDemo(String[] args) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--demo")) {
                return true;
            }
        }
        return false;
    }

    public static void seedSampleData(Library library) {
        // Add Books
        library.addItem(new Book(101, "Effective Java", 2018, "Joshua Bloch", "Software Engineering"));
        library.addItem(new Book(102, "Clean Code", 2008, "Robert C. Martin", "Software Engineering"));
        library.addItem(new Book(103, "Design Patterns", 1994, "Erich Gamma et al.", "Computer Science"));

        // Add DVDs
        library.addItem(new Dvd(201, "Inception", 2010, "Christopher Nolan", 148));
        library.addItem(new Dvd(202, "The Matrix", 1999, "Lana & Lilly Wachowski", 136));

        // Add Magazines
        library.addItem(new Magzine(301, "National Geographic", 2024, 154, "NatGeo Society"));
        library.addItem(new Magzine(302, "Tech Innovators", 2025, 42, "HCL Media"));

        // Register Members
        library.registerMember(new Member(1, "Alice Sharma", "9876543210", 2));
        library.registerMember(new Member(2, "Bob Kumar", "9123456780", 1));
        library.registerMember(new Member(3, "Charlie Verma", "9988776655", 3));
    }

    public static void runDemo(Library library) {
        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 1: DISPLAY ALL REGISTERED LIBRARY ITEMS");
        System.out.println("----------------------------------------------------------");
        library.displayAllItems();

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 2: DISPLAY ALL REGISTERED MEMBERS");
        System.out.println("----------------------------------------------------------");
        library.displayAllMembers();

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 3: BORROWING OPERATIONS & BUSINESS RULE VALIDATION");
        System.out.println("----------------------------------------------------------");

        System.out.println("\n-> Transaction 1: Alice (Max: 2) borrows 'Effective Java' (ID: 101):");
        library.borrowItem(1, 101);

        System.out.println("\n-> Transaction 2: Alice borrows 'Inception' DVD (ID: 201):");
        library.borrowItem(1, 201);

        System.out.println("\n-> Transaction 3: Alice attempts to borrow 'Clean Code' (ID: 102) [Exceeding maxBooks]:");
        library.borrowItem(1, 102);

        System.out.println("\n-> Transaction 4: Bob attempts to borrow 'Effective Java' (ID: 101) [Already Borrowed]:");
        library.borrowItem(2, 101);

        System.out.println("\n-> Transaction 5: Bob borrows 'Clean Code' (ID: 102):");
        library.borrowItem(2, 102);

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 4: DISPLAY CURRENT AVAILABLE ITEMS");
        System.out.println("----------------------------------------------------------");
        library.displayAvailableItems();

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 5: SEARCH FUNCTIONALITY");
        System.out.println("----------------------------------------------------------");
        System.out.println("Searching for items with title matching 'Java':");
        List<LibraryItem> javaItems = library.searchItemsByTitle("Java");
        for (LibraryItem it : javaItems) {
            it.displayDetails();
        }

        System.out.println("Searching for items with title matching 'Matrix':");
        List<LibraryItem> matrixItems = library.searchItemsByTitle("Matrix");
        for (LibraryItem it : matrixItems) {
            it.displayDetails();
        }

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 6: RETURN OPERATIONS");
        System.out.println("----------------------------------------------------------");
        System.out.println("\n-> Transaction 6: Alice returns 'Effective Java' (ID: 101):");
        library.returnItem(1, 101);

        System.out.println("\n-> Transaction 7: Alice can now borrow another item ('Clean Code' is borrowed, so borrows Magazine 301):");
        library.borrowItem(1, 301);

        System.out.println("\n----------------------------------------------------------");
        System.out.println(" STEP 7: FINAL MEMBER & CATALOG STATUS");
        System.out.println("----------------------------------------------------------");
        library.displayAllMembers();
        library.displayAvailableItems();

        System.out.println("\n==========================================================");
        System.out.println("           DEMONSTRATION COMPLETED SUCCESSFULLY           ");
        System.out.println("==========================================================");
    }

    private static void runInteractiveMenu(Library library, Scanner scanner) {
        while (true) {
            System.out.println("\n================== LIBRARY MENU ==================");
            System.out.println("1. Display All Items");
            System.out.println("2. Display Available Items");
            System.out.println("3. Display All Members");
            System.out.println("4. Add New Book");
            System.out.println("5. Add New DVD");
            System.out.println("6. Add New Magazine");
            System.out.println("7. Register New Member");
            System.out.println("8. Borrow an Item");
            System.out.println("9. Return an Item");
            System.out.println("10. Search Item by Title");
            System.out.println("11. Run Automated Demonstration");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                break;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.displayAllItems();
                    break;
                case 2:
                    library.displayAvailableItems();
                    break;
                case 3:
                    library.displayAllMembers();
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    int bId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String bTitle = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int bYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String bAuthor = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String bGenre = scanner.nextLine();
                    library.addItem(new Book(bId, bTitle, bYear, bAuthor, bGenre));
                    break;
                case 5:
                    System.out.print("Enter DVD ID: ");
                    int dId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String dTitle = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int dYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Director: ");
                    String dDirector = scanner.nextLine();
                    System.out.print("Enter Duration (mins): ");
                    int dDuration = scanner.nextInt();
                    scanner.nextLine();
                    library.addItem(new Dvd(dId, dTitle, dYear, dDirector, dDuration));
                    break;
                case 6:
                    System.out.print("Enter Magazine ID: ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String mTitle = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int mYear = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Issue Number: ");
                    int mIssue = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Publisher: ");
                    String mPublisher = scanner.nextLine();
                    library.addItem(new Magzine(mId, mTitle, mYear, mIssue, mPublisher));
                    break;
                case 7:
                    System.out.print("Enter Member ID: ");
                    int memId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Member Name: ");
                    String memName = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String memPhone = scanner.nextLine();
                    System.out.print("Enter Max Books Allowed: ");
                    int memMax = scanner.nextInt();
                    scanner.nextLine();
                    library.registerMember(new Member(memId, memName, memPhone, memMax));
                    break;
                case 8:
                    System.out.print("Enter Member ID: ");
                    int borrowMemId = scanner.nextInt();
                    System.out.print("Enter Item ID to borrow: ");
                    int borrowItemId = scanner.nextInt();
                    scanner.nextLine();
                    library.borrowItem(borrowMemId, borrowItemId);
                    break;
                case 9:
                    System.out.print("Enter Member ID: ");
                    int retMemId = scanner.nextInt();
                    System.out.print("Enter Item ID to return: ");
                    int retItemId = scanner.nextInt();
                    scanner.nextLine();
                    library.returnItem(retMemId, retItemId);
                    break;
                case 10:
                    System.out.print("Enter search keyword in title: ");
                    String keyword = scanner.nextLine();
                    List<LibraryItem> found = library.searchItemsByTitle(keyword);
                    System.out.println("Found " + found.size() + " matches:");
                    for (LibraryItem item : found) {
                        item.displayDetails();
                    }
                    break;
                case 11:
                    runDemo(library);
                    break;
                case 0:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from menu.");
            }
        }
    }
}
