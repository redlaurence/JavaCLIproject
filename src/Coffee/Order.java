package Coffee;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private List<OrderItem> items;
    private String cashier;

    //Constructor now accept a cashier name
    public Order(int orderId, String cashier) {
        this.orderId = orderId;
        this.cashier = cashier;  
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCashier() {
        return cashier;
    }

    // Set cashier in case it needs to be updated later
    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
    
    // Calculate total cost of the order
    public double getTotalCost() {
        double totalCost = 0;
        for (OrderItem item : items) {
            totalCost += item.getTotalPrice();  
        }
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items:\n");
        for (OrderItem item : items) {
            sb.append(" - ").append(item).append("\n");
        }
        sb.append("Total cost: " + getTotalCost());
        return sb.toString();
    }
}
