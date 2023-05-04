package model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private String username;
    private double price;
    private Date date;
    private ArrayList<Product> products;

    public Pedido(int id, String username, double price, Date date, ArrayList<Product> products) {
        this.id = id;
        this.username = username;
        this.price = price;
        this.date = date;
        this.products = new ArrayList<Product>();
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
