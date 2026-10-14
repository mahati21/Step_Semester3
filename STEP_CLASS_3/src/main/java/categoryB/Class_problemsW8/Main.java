package categoryB.Class_problemsW8;

import java.util.ArrayList;
import java.util.List;

class OrderStatus {
    public static final String PENDING = "Pending";
    public static final String PAID = "Paid";
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderCustomer {
    private String name;

    public OrderCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}

class Order {
    private OrderCustomer customer;
    private String orderId;
    private List<OrderItem> items;
    private String status;

    public Order(OrderCustomer customer, String orderId) {
        this.customer = customer;
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double getTotalAmount() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

interface PaymentMethod {
    boolean processPayment(double amount, String orderId);
}

class CreditCardPayment implements PaymentMethod {
    private boolean simulateSuccess;

    public CreditCardPayment(boolean simulateSuccess) {
        this.simulateSuccess = simulateSuccess;
    }

    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("Payment initiated via Credit Card for Order " + orderId + ".");

        if (simulateSuccess) {
            System.out.println("Payment for Order " + orderId + " successful.");
            return true;
        }

        System.out.println("Payment for Order " + orderId + " failed.");
        return false;
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean simulateSuccess;

    public PayPalPayment(boolean simulateSuccess) {
        this.simulateSuccess = simulateSuccess;
    }

    @Override
    public boolean processPayment(double amount, String orderId) {
        System.out.println("Payment initiated via PayPal for Order " + orderId + ".");

        if (simulateSuccess) {
            System.out.println("Payment for Order " + orderId + " successful.");
            return true;
        }

        System.out.println("Payment for Order " + orderId + " failed.");
        return false;
    }
}

class PaymentProcessor {
    public static void checkout(Order order, PaymentMethod paymentMethod) {
        if (order.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        double amount = order.getTotalAmount();
        boolean isSuccess = paymentMethod.processPayment(amount, order.getOrderId());

        if (isSuccess) {
            order.setStatus(OrderStatus.PAID);
        }

        System.out.println("Order status: " + order.getStatus() + ".");
    }
}

public class Main {
    public static void main(String[] args) {
        Product productA = new Product("Product A", 50.0);
        Product productB = new Product("Product B", 30.0);
        Product productC = new Product("Product C", 100.0);

        OrderCustomer customerX = new OrderCustomer("X");
        Order orderX = new Order(customerX, "X");

        System.out.println("Order created for Customer " + customerX.getName() + ".");

        orderX.addItem(productA, 2);
        orderX.addItem(productB, 1);

        PaymentMethod ccSuccess = new CreditCardPayment(true);
        PaymentProcessor.checkout(orderX, ccSuccess);

        OrderCustomer customerY = new OrderCustomer("Y");
        Order orderY = new Order(customerY, "Y");

        PaymentProcessor.checkout(orderY, ccSuccess);

        OrderCustomer customerZ = new OrderCustomer("Z");
        Order orderZ = new Order(customerZ, "Z");

        System.out.println("Order created for Customer " + customerZ.getName() + ".");

        orderZ.addItem(productC, 1);

        PaymentMethod payPalFail = new PayPalPayment(false);
        PaymentProcessor.checkout(orderZ, payPalFail);
    }
}