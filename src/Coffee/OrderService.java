package Coffee;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderService {
    private List<Order> cashier1Sales = new ArrayList<>();
    private List<Order> cashier2Sales = new ArrayList<>();
    private int nextOrderId = 1;
    private String currentUser = null;  
    private List<Order> currentUserOrders = new ArrayList<>();  

    // Order coffee
    public void orderCoffee(Scanner sc, CoffeeService coffeeService) {
        System.out.println("Placing an order...");
        LocalTime currentTime = LocalTime.now();

        //cashier to assign based on the current time
        boolean isMorning = currentTime.isBefore(LocalTime.NOON);

        Order order = new Order(nextOrderId++, currentUser);
        boolean ordering = true;

        while (ordering) {
            coffeeService.viewCoffees();
            System.out.print("Enter coffee ID to order (or 0 to finish): ");
            int coffeeId = sc.nextInt();
            

            if (coffeeId == 0) {
                ordering = false;
                break;
            }
            
            Coffee coffee = coffeeService.getCoffeeById(coffeeId);
            if (coffee == null) {
                System.out.println("Invalid coffee ID. Try again.");
                continue;
            }

            if (coffee.getStock() == 0) {
                System.out.println("This coffee is out of stock.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            if (quantity > coffee.getStock()) {
                System.out.println("Insufficient stock. Available: " + coffee.getStock());
                continue;
            }

            coffeeService.decreaseStock(coffeeId, quantity);
            OrderItem item = new OrderItem(coffee, quantity);
            order.addItem(item);
            System.out.println("Added to order: " + item);
        }

        if (!order.getItems().isEmpty()) {
            // Record order for the respective cashier based on time of day
            if (isMorning) {
                cashier1Sales.add(order);
                System.out.println("Order recorded under Cashier 1 (Morning).");
            } else {
                cashier2Sales.add(order);
                System.out.println("Order recorded under Cashier 2 (Afternoon).");
            }

            // Add the order to the current user's list of orders
            currentUserOrders.add(order);
            System.out.println("Order placed successfully: " + order);
        } else {
            System.out.println("Order canceled.");
        }
    }

    // Set current user to handle new login and reset previous user orders
    public void setCurrentUser(String username) {
        clearPreviousOrders();
        this.currentUser = username;
        this.currentUserOrders.clear(); 
    }

    // Clear order when a new user logs in
    private void clearPreviousOrders() {
        if (currentUserOrders != null && !currentUserOrders.isEmpty()) {
            System.out.println("Previous orders cleared for: " + currentUser);
            currentUserOrders.clear(); 
        }
    }

    // View orders for the current log in user
    public void viewOrders() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        System.out.println("Viewing orders for: " + currentUser);
         
        if (currentUserOrders.isEmpty()) {
            System.out.println("No orders placed for this user.");
        } else {
            for (Order order : currentUserOrders) {
                double totalCost = order.getTotalCost();
                for (OrderItem item : order.getItems()) {
                    System.out.println("Coffee name: " + item.getCoffee().getName());
                    System.out.println("Quantity: " + item.getQuantity());
                    System.out.println("Total cost for this item: " + item.getTotalPrice());
                }
                System.out.println("Total cost for order: " + totalCost);
            }
        }
       
    }

    // View sales for a specific cashier
    public void viewSales(String cashier) {
        List<Order> sales = cashier.equals("cashier1") ? cashier1Sales : cashier2Sales;
        System.out.println("Sales for " + cashier + ":");
        for (Order order : sales) {
            System.out.println(order);
        }
    }

    // View total sales across both cashiers
     public void viewTotalSales() {
        double totalSales = 0;

        for (Order order : cashier1Sales) {
            totalSales += order.getTotalCost();
            double totalCost = order.getTotalCost();
                for (OrderItem item : order.getItems()) {
                    System.out.println("Coffee name: " + item.getCoffee().getName());
                    System.out.println("Quantity: " + item.getQuantity());
                    System.out.println("Total cost for this item: " + item.getTotalPrice());
                }
                System.out.println("Total cost for order: " + totalCost);
        }

        for (Order order : cashier2Sales) {
            totalSales += order.getTotalCost();
            double totalCost = order.getTotalCost();
                for (OrderItem item : order.getItems()) {
                    System.out.println("Coffee name: " + item.getCoffee().getName());
                    System.out.println("Quantity: " + item.getQuantity());
                    System.out.println("Total cost for this item: " + item.getTotalPrice());
                }
                System.out.println("Total cost for order: " + totalCost);
        }
        
        
        System.out.println("Total Daily Sales: " + totalSales);
    }
    

}