import java.time.LocalDate;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static List<Book> books = new ArrayList<>();
    static List<Member> members = new ArrayList<>();

    static Set<String> memberIds = new HashSet<>();
    static Map<String, Member> memberAccounts = new HashMap<>();

    public static void main(String[] args) {

        initialize();

        System.out.println("=================================");
        System.out.println("   LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");

        createProfile();

        mainMenu();
    }

    public static void initialize() {

        System.out.println("Initializing library system...");

        books.add(new Book(
                "Java Basics",
                "James",
                "ISBN001",
                LocalDate.of(2024, 1, 10)
        ));

        books.add(new Book(
                "Spring Boot",
                "John",
                "ISBN002",
                LocalDate.of(2025, 3, 15)
        ));

        books.add(new Book(
                "Algorithms",
                "Robert",
                "ISBN003",
                LocalDate.of(2023, 5, 20)
        ));

        System.out.println("System initialized.");
    }

    public static void createProfile() {

        System.out.println("\nChoose your role:");
        System.out.println("1. Student");
        System.out.println("2. Publisher");
        System.out.println("3. Librarian");

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Member member;

        if (choice == 1) {
            member = new StudentMember(id, name);
        } else if (choice == 2) {
            member = new PublisherMember(id, name);
        } else if (choice == 3) {
            member = new LibrarianMember(id, name);
        } else {
            System.out.println("Invalid role.");
            return;
        }

        members.add(member);
        memberIds.add(id);
        memberAccounts.put(id, member);

        System.out.println("\nProfile created successfully!");
        System.out.println(member);
    }

    public static void mainMenu() {

        int choice;

        do {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Book & Publisher");
            System.out.println("2. Members & Borrowings");
            System.out.println("3. Reports");
            System.out.println("4. View Books");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    bookMenu();
                    break;

                case 2:
                    memberMenu();
                    break;

                case 3:
                    reportMenu();
                    break;

                case 4:
                    viewBooks();
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    public static void bookMenu() {

        System.out.println("\n====== BOOK MENU ======");
        System.out.println("1. Add book");
        System.out.println("2. View books");

        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            System.out.print("Publication year: ");
            int year = scanner.nextInt();

            Book book = new Book(
                    title,
                    author,
                    isbn,
                    LocalDate.of(year, 1, 1)
            );

            books.add(book);

            System.out.println("Book added successfully.");

        } else if (choice == 2) {

            viewBooks();

        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void viewBooks() {

        System.out.println("\n====== ALL BOOKS ======");

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        books.forEach(System.out::println);
    }

    public static void memberMenu() {

        System.out.println("\n====== MEMBER MENU ======");
        System.out.println("1. Register member");
        System.out.println("2. Borrow book");
        System.out.println("3. Return book");
        System.out.println("4. View active loans");

        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                registerMember();
                break;

            case 2:
                borrowBook();
                break;

            case 3:
                returnBook();
                break;

            case 4:
                viewLoans();
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public static void registerMember() {

        System.out.print("Member ID: ");
        String id = scanner.nextLine();

        if (memberIds.contains(id)) {
            System.out.println("Member ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("1. Student");
        System.out.println("2. Publisher");
        System.out.println("3. Librarian");

        int role = scanner.nextInt();
        scanner.nextLine();

        Member member;

        if (role == 1) {
            member = new StudentMember(id, name);
        } else if (role == 2) {
            member = new PublisherMember(id, name);
        } else if (role == 3) {
            member = new LibrarianMember(id, name);
        } else {
            System.out.println("Invalid role.");
            return;
        }

        members.add(member);
        memberIds.add(id);
        memberAccounts.put(id, member);

        System.out.println("Member registered successfully.");
    }

    public static void borrowBook() {

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();

        Member member = memberAccounts.get(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        viewBooks();

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        for (Book book : books) {

            if (book.getIsbn().equalsIgnoreCase(isbn)) {

                member.borrowBook(book);

                System.out.println("Book borrowed successfully.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    public static void returnBook() {

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();

        Member member = memberAccounts.get(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (member.getBorrowedBooks().isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }

        System.out.println("Borrowed books:");

        for (Book book : member.getBorrowedBooks()) {
            System.out.println(book);
        }

        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        for (Book book : member.getBorrowedBooks()) {

            if (book.getIsbn().equalsIgnoreCase(isbn)) {

                member.returnBook(book);

                System.out.println("Book returned successfully.");
                return;
            }
        }

        System.out.println("Book not found in active loans.");
    }

    public static void viewLoans() {

        System.out.print("Enter member ID: ");
        String id = scanner.nextLine();

        Member member = memberAccounts.get(id);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("\nActive loans:");

        if (member.getBorrowedBooks().isEmpty()) {
            System.out.println("No active loans.");
            return;
        }

        member.getBorrowedBooks()
                .forEach(System.out::println);
    }

    public static void reportMenu() {

        System.out.println("\n====== REPORTS ======");
        System.out.println("1. Total borrowed books");
        System.out.println("2. Books by starting letter");

        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {

            LibraryReports.countBorrowedBooks(members);

        } else if (choice == 2) {

            LibraryReports.groupByStartingLetter(books);

        } else {

            System.out.println("Invalid option.");
        }
    }
}