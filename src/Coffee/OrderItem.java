package Coffee;

public class OrderItem {
    private Coffee coffee;
    private int quantity;

    public OrderItem(Coffee coffee, int quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return coffee.getPrice() * quantity;
    }
    @Override
    public String toString() {
        return coffee.getName() + " x" + quantity + " (" + coffee.getPrice() + " each)";
    }
}