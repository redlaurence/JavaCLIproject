
package Coffee;
import java.util.*;
public class Main {
    
  public static void main(String[]args) {
   
        CoffeeService coffeeService = new CoffeeService();
        OrderService orderService = new OrderService();
        UserService userService = new UserService();
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        
        while (running) {
            try{
            System.out.println("Coffee System - Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your number to choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    // Login
                    System.out.print("\nEnter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                   if (userService.login(username, password, orderService)) {
                        if (userService.getCurrentRole().equals("admin")) {
                            adminMenu(sc, coffeeService, orderService);
                        }else if(userService.getCurrentRole().equals("cashierMorning")){
                            cashiermenu(sc, coffeeService, orderService);
                        }else if(userService.getCurrentRole().equals("cashierAfternoon")){
                            cashiermenu(sc, coffeeService, orderService);    
                        } 
                    }
                    break;
                    
                case 2:
                    running = false;
                    System.out.println("\nExit Successfully.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }catch(Exception e){
                sc.nextLine();
                System.out.println("Number Only.");
        }
      }      
       sc.close();
    }
  
    // Admin menu actions
    private static void adminMenu(Scanner sc, CoffeeService coffeeService, OrderService orderService) {
        boolean adminRunning = true;
        while (adminRunning) {
            try{
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Coffee");
            System.out.println("2. View All Coffees");
            System.out.println("3. Update Coffee");
            System.out.println("4. Delete Coffee");
            System.out.println("5. View Cashier 1 (Morning) Sales");
            System.out.println("6. View Cashier 2 (Afternoon) Sales");
            System.out.println("7. View Total Sales");
            System.out.println("8. Log Out");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    coffeeService.addCoffee(sc);
                    break;

                case 2:
                    coffeeService.viewCoffees();
                    break;

                case 3:
                    coffeeService.updateCoffee(sc);
                    break;

                case 4:
                    coffeeService.deleteCoffee(sc);
                    break;

                case 5:
                    orderService.viewSales("cashier1");
                    break;

                case 6:
                    orderService.viewSales("cashier2");
                    break;
                    
                case 7:
                    orderService.viewTotalSales();
                    break;

                case 8:
                    adminRunning = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }catch(Exception e){
            sc.nextLine();
            System.out.println("Number Only.");
        }
                
        }
    }

    // Cashier menu actions
    private static void cashiermenu(Scanner sc, CoffeeService coffeeService, OrderService orderService) {
        boolean userRunning = true;
        while (userRunning) {
            try{
            System.out.println("\nCashier Menu");
            System.out.println("1. View Coffees");
            System.out.println("2. Order Coffee");
            System.out.println("3. View Order Receipt");
            System.out.println("4. Log Out");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    coffeeService.viewCoffees();
                    break;

                case 2:
                    orderService.orderCoffee(sc, coffeeService);
                    break;

                case 3:
                    orderService.viewOrders();
                    break;

                case 4:
                    userRunning = false;
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }catch(Exception e){
                sc.nextLine();
                System.out.println("Number Only.");
         }
        }
     }   
}