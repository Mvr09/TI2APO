package model;

import java.util.ArrayList;

public class Usuario {
    private String username;

    private ArrayList<Pedido> pedidos;
    private ArrayList<Product> products;


    public Usuario(String username, ArrayList<Product> products) {
        this.username = username;
        this.pedidos = new ArrayList<Pedido>();
        this.products = new ArrayList<Product>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
