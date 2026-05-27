package Coffee;


import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, String> users = new HashMap<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private static final String CASHIER_M_USERNAME = "cashier1";
    private static final String CASHIER_M_PASSWORD = "cashier1";
    private static final String CASHIER_A_USERNAME = "cashier2";
    private static final String CASHIER_A_PASSWORD = "cashier2";

    private String currentUser;
    private String currentRole;


    // Validate user login
    public boolean login(String username, String password, OrderService orderService) {
        // Admin
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            currentUser = username;
            currentRole = "admin";
            System.out.println("Admin logged in.");
            orderService.setCurrentUser(username);  
            return true;
        }
        // Cashier 1
        if (CASHIER_M_USERNAME.equals(username) && CASHIER_M_PASSWORD.equals(password)) {
            currentUser = username;
            currentRole = "cashierMorning";
            System.out.println("Cashier Morning (logged in) .");
            orderService.setCurrentUser(username);  
            return true;
        }
        // Cashier 2
        if (CASHIER_A_USERNAME.equals(username) && CASHIER_A_PASSWORD.equals(password)) {
            currentUser = username;
            currentRole = "cashierAfternoon";
            System.out.println("Cashier Afternoon  (logged in).");
            orderService.setCurrentUser(username); 
            return true;
        }

        System.out.println("Account does not exist or incorrect password.");
        return false;
    }

    // Get current logged-in user's role
    public String getCurrentRole() {
        return currentRole;
    }

    // Get current logged-in user
    public String getCurrentUser() {
        return currentUser;
    }

    // Logout current user
    public void logout(OrderService orderService) {
        System.out.println("Logging out user: " + currentUser);
        orderService.setCurrentUser(null); 
        currentUser = null;
        currentRole = null;
        System.out.println("Logged out successfully.");
    }
}