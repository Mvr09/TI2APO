package model;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private String description;
    private int numSold = 0;

    private int numStored = 0;
    private ProductType type;

    public Product(String name, double price, String description, int numSold, int numStored, ProductType type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.numSold = numSold;
        this.numStored = numStored;
        this.type = type;
    }

    // Constructor for searching by name or description
    public Product(String value, String attribute) {
        if (attribute.equals("name")) {
            this.name = value;
        } else {
            this.description = value;
        }
    }

    // Constructor for searching by price
    public Product(double value) {
        this.price = value;
    }

    // Constructor for searching by numSold
    public Product(int value) {
        this.numSold = value;
    }

    // Constructor for searching by type
    public Product(ProductType type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public int getNumStored() {
        return numStored;
    }

    public void setNumStored(int numStored) {
        this.numStored = numStored;
    }

    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }
}
