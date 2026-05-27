package Coffee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeService {
    private List<Coffee> coffeeList = new ArrayList<>();
    private int nextId = 1;

    // Add coffee
    public void addCoffee(Scanner scanner) {
        System.out.print("Enter coffee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter coffee price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter coffee stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Coffee coffee = new Coffee(nextId++ , name , price , stock );
        coffeeList.add(coffee);
        System.out.println("\nCoffee added: \n" + coffee);
    }

    // View all coffees
    public void viewCoffees() {
        if (coffeeList.isEmpty()) {
            System.out.println("No coffees available.");
            return;
        }

        System.out.println("\nAvailable Coffees:");
        for (Coffee coffee : coffeeList) {
            String status = coffee.getStock() > 0 ? "In Stock" : "Out of Stock";
            System.out.println(coffee + " - " + status);
        }
    }

    // Update coffee details
    public void updateCoffee(Scanner scanner) {
        System.out.print("\nEnter coffee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Coffee coffee = getCoffeeById(id);
        if (coffee == null) {
            System.out.println("Coffee not found.");
            return;
        }

        System.out.print("Enter new coffee price (-1 to keep current): ");
        double newPrice = scanner.nextDouble();
        if (newPrice >= 0) {
            coffee.setPrice(newPrice);
        }

        System.out.print("Enter new coffee stock (-1 to keep current): ");
        int newStock = scanner.nextInt();
        if (newStock >= 0) {
            coffee.setStock(newStock);
        }

        System.out.println("\nCoffee updated: \n" + coffee);
    }

    // Delete coffee
    public void deleteCoffee(Scanner scanner) {
        System.out.print("Enter coffee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        boolean removed = coffeeList.removeIf(coffee -> coffee.getId() == id);
        if (removed) {
            System.out.println("\nCoffee deleted successfully.");
        } else {
            System.out.println("Coffee not found.");
        }
    }

    // Get coffee by ID
    public Coffee getCoffeeById(int id) {
        return coffeeList.stream()
                .filter(coffee -> coffee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Decrease stock after purchase
    public boolean decreaseStock(int id, int quantity) {
        Coffee coffee = getCoffeeById(id);
        if (coffee != null && coffee.getStock() >= quantity) {
            coffee.setStock(coffee.getStock() - quantity);
            return true;
        }
        return false;
    }
}