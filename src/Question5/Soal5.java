package Question5;

import java.util.Scanner;
import java.util.Stack;

class Book {
    protected String title;
    protected String author;
    protected int yearOfPublication;

    public Book(String title, String author, int yearOfPublication) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (author.length() >= 50) {
            throw new IllegalArgumentException("Author must be under 50 characters.");
        }
        if (yearOfPublication <= 1800 || yearOfPublication >= 2026) {
            throw new IllegalArgumentException("Year must be between 1800 and 2026.");
        }
        this.title = title + "AIUEO";
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year of Publication: " + yearOfPublication);
    }
}

class GeneralBook extends Book {
    private String genre;

    public GeneralBook(String title, String author, int yearOfPublication, String genre) {
        super(title, author, yearOfPublication);
        if (genre.length() > 30) {
            throw new IllegalArgumentException("Genre must be no more than 30 characters.");
        }
        this.genre = genre;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Genre: " + genre);
    }
}

class ChildrenBook extends Book {
    private int minAge;
    private boolean hasVisualisation;

    public ChildrenBook(String title, String author, int yearOfPublication, int minAge, boolean hasVisualisation) {
        super(title, author, yearOfPublication);
        if (minAge <= 3 || minAge >= 12) {
            throw new IllegalArgumentException("Minimum age must be between 3 and 12.");
        }
        this.minAge = minAge;
        this.hasVisualisation = hasVisualisation;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Minimum Age: " + minAge);
        System.out.println("Has Visualisation: " + (hasVisualisation ? "Yes" : "No"));
    }
}

public class Soal5 {

    private static Stack<Book> library = new Stack<>();

    private static void preload() {
        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));
        library.push(new GeneralBook("The Art of War", "Sun Tzu", 1910, "Military Strategy"));
        library.push(new ChildrenBook("The Little Prince", "Antoine de Saint-Exupery", 1943, 6, true));
    }

    private static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n=== Book List ===");
        Stack<Book> temp = new Stack<>();
        int index = 1;
        while (!library.isEmpty()) {
            temp.push(library.pop());
        }
        while (!temp.isEmpty()) {
            Book book = temp.pop();
            System.out.println("\n[" + index++ + "]");
            book.getInfo();
            library.push(book);
        }
    }

    private static void addBook(Scanner sc) {
        try {
            System.out.println("\nBook type: 1=Book, 2=GeneralBook, 3=ChildrenBook");
            System.out.print("Choice: ");
            int type = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Title: ");
            String title = sc.nextLine().trim();

            System.out.print("Author: ");
            String author = sc.nextLine().trim();

            System.out.print("Year of Publication: ");
            int year = Integer.parseInt(sc.nextLine().trim());

            switch (type) {
                case 1 -> {
                    library.push(new Book(title, author, year));
                    System.out.println("Book added successfully.");
                }
                case 2 -> {
                    System.out.print("Genre: ");
                    String genre = sc.nextLine().trim();
                    library.push(new GeneralBook(title, author, year, genre));
                    System.out.println("GeneralBook added successfully.");
                }
                case 3 -> {
                    System.out.print("Minimum Age: ");
                    int minAge = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Has Visualisation (true/false): ");
                    boolean hasVis = Boolean.parseBoolean(sc.nextLine().trim());
                    library.push(new ChildrenBook(title, author, year, minAge, hasVis));
                    System.out.println("ChildrenBook added successfully.");
                }
                default -> System.out.println("Error: Invalid book type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteBook(Scanner sc) {
        if (library.isEmpty()) {
            System.out.println("No books to delete.");
            return;
        }
        viewBooks();
        System.out.print("\nEnter book number to delete: ");
        try {
            int target = Integer.parseInt(sc.nextLine().trim());
            if (target < 1 || target > library.size()) {
                System.out.println("Error: Invalid book number.");
                return;
            }
            Stack<Book> temp = new Stack<>();
            while (!library.isEmpty()) {
                temp.push(library.pop());
            }
            int index = 1;
            String deletedTitle = "";
            while (!temp.isEmpty()) {
                Book book = temp.pop();
                if (index == target) {
                    deletedTitle = book.title;
                } else {
                    library.push(book);
                }
                index++;
            }
            System.out.println("Deleted: " + deletedTitle);
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        System.out.println("I HOPE YOU GET SCRATCHED IN THE FACE BY MY CAT, FATTY BOIS");
        System.out.println("\nWelcome to the Library of Alexandria");

        preload();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. View Books");
            System.out.println("2. Add Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> viewBooks();
                case "2" -> addBook(sc);
                case "3" -> deleteBook(sc);
                case "4" -> running = false;
                default  -> System.out.println("Error: Invalid choice.");
            }
        }

        System.out.println("Goodbye.");
        sc.close();
    }
}