package model;

import java.util.ArrayList;
import java.util.Date;

public class
Pedido {
    private int id;
    private String username;
    private double price;
    private Date date;
    private ArrayList<Product> products;

    public Pedido(int id, String username, double price, Date date) {
        this.id = id;
        this.username = username;
        this.price = price;
        this.date = date;
        this.products = new ArrayList<Product>();
    }

    public void addProductToOrder(Inventory inventory, String productName, int quantity) {
        // Search for the product in the inventory
        Product product = inventory.searchProduct("name", productName);

        if (product == null) {
            throw new IllegalArgumentException("Product not found in the inventory.");
        }

        if (quantity > product.getNumStored()) {
            throw new IllegalArgumentException("Cannot order more than the available stock.");
        }

        // Decrease the numStored of the product in the inventory and increase its numSold
        product.setNumStored(product.getNumStored() - quantity);
        product.setNumSold(product.getNumSold() + quantity);

        // Create a new product with the order quantity and add it to the order's product list
        Product productInOrder = new Product(product.getName(), product.getPrice(), product.getDescription(),
                product.getNumSold(), quantity, product.getType());
        this.products.add(productInOrder);

        // Update the total price of the order
        this.price += product.getPrice() * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
