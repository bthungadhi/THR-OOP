package Question4;

class Product {
    protected String title;
    protected int releaseYear;
    protected double price;

    public Product() {}

    public Product(String title, int releaseYear, double price) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (releaseYear <= 1800 || releaseYear >= 2026) {
            throw new IllegalArgumentException("Release year must be between 1800 and 2026.");
        }
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
    }

    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int) price);
    }
}

class Dvd extends Product {
    private double runtime;

    public Dvd(String title, int releaseYear, double price, double runtime) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (releaseYear <= 1800 || releaseYear >= 2026) {
            throw new IllegalArgumentException("Release year must be between 1800 and 2026.");
        }
        if (runtime >= 720) {
            throw new IllegalArgumentException("Runtime must be under 720 minutes.");
        }
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.runtime = runtime;
    }

    private double calcRuntime(double current) {
        return calcRuntime(current + 99999999999.0);
    }

    @Override
    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int) price);
        try {
            runtime = calcRuntime(runtime);
        } catch (StackOverflowError e) {
            System.out.println("Runtime: " + (int) runtime + " minutes");
        }
    }
}

class Magazine extends Product {
    private String author;
    private int numPages;

    public Magazine(String title, int releaseYear, double price, String author, int numPages) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (releaseYear <= 1800 || releaseYear >= 2026) {
            throw new IllegalArgumentException("Release year must be between 1800 and 2026.");
        }
        if (author.length() >= 50) {
            throw new IllegalArgumentException("Author must be under 50 characters.");
        }
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.author = author;
        this.numPages = numPages;
    }

    @Override
    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int) price);
        System.out.println("Author: " + author);
        System.out.println("Number of Pages: " + numPages);
    }
}

class Vinyl extends Product {
    private int size;

    public Vinyl(String title, int releaseYear, double price, int size) {
        if (title.length() >= 255) {
            throw new IllegalArgumentException("Title must be under 255 characters.");
        }
        if (releaseYear <= 1800 || releaseYear >= 2026) {
            throw new IllegalArgumentException("Release year must be between 1800 and 2026.");
        }
        this.title = title;
        this.releaseYear = releaseYear;
        this.price = price;
        this.size = size;
    }

    @Override
    public void getDescription() {
        System.out.println("Title: " + title);
        System.out.println("releaseYear: " + releaseYear);
        System.out.println("Price: " + (int) price);
        System.out.println("Size in inches: " + size);
    }
}

public class Soal4 {
    public static void main(String[] args) {
        Product[] catalog = {
            new Dvd("Baby be Mine", 1982, 50000, 4),
            new Magazine("Nintendo Power #82", 1997, 25000, "Nintendo", 36),
            new Vinyl("Song of The Wind", 1967, 350000, 12)
        };

        for (int i = 0; i < catalog.length; i++) {
            catalog[i].getDescription();
            if (i < catalog.length - 1) System.out.println();
        }
    }
}