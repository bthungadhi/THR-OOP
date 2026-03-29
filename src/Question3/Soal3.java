package Question3;

import java.util.Scanner;

class Food {
    protected String name;
    protected int basePrice;
    private static final int LABOUR_COST = 5000;

    public Food(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public int calcPrice() {
        return basePrice + LABOUR_COST;
    }

    public void getInfo() {
        System.out.println("Name: " + name);
        System.out.println("Price: " + calcPrice());
    }
}

class RegularMenu extends Food {
    private static final int REGULAR_TAX = 10000;

    public RegularMenu(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    public int calcPrice() {
        return super.calcPrice() + REGULAR_TAX;
    }
}

class SpecialMenu extends Food {
    private static final int SPECIAL_TAX = 20000;

    public SpecialMenu(String name, int basePrice) {
        super(name, basePrice);
    }

    @Override
    public int calcPrice() {
        return super.calcPrice() + SPECIAL_TAX;
    }
}

public class Soal3 {

    private static Food createMenu(Scanner sc) {
        System.out.print("Type (1=Food, 2=RegularMenu, 3=SpecialMenu): ");
        int type = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Base price: ");
        int price = Integer.parseInt(sc.nextLine().trim());

        if (price <= 1000000) {
            throw new IllegalArgumentException("Base price must be above 1000000. Got: " + price);
        }

        return switch (type) {
            case 1 -> new Food(name, price);
            case 2 -> new RegularMenu(name, price);
            case 3 -> new SpecialMenu(name, price);
            default -> throw new IllegalArgumentException("Invalid menu type: " + type);
        };
    }

    public static void main(String[] args) {
        Food beef        = new Food("Beef Rendang", 15000);
        RegularMenu ramen = new RegularMenu("Chicken Ramen", 20000);
        SpecialMenu rice  = new SpecialMenu("Fiery Fried Rice", 80000);

        beef.getInfo();
        System.out.println();
        ramen.getInfo();
        System.out.println();
        rice.getInfo();

        System.out.println("\n--- Main Menu ---");
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add menu item");
            System.out.println("2. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    try {
                        Food item = createMenu(sc);
                        item.getInfo();
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid number.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case "2" -> running = false;
                default  -> System.out.println("Error: Invalid choice.");
            }
        }

        sc.close();
    }
}