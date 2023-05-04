package model;

import java.util.ArrayList;
import java.util.Collections;

public class Inventory {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Product> products;
    private ArrayList<Usuario> usuarios;

    public Inventory(ArrayList<Pedido> pedidos, ArrayList<Product> products, ArrayList<Usuario> usuarios) {
        this.pedidos = new ArrayList<Pedido>();
        this.products = new ArrayList<Product>();
        this.usuarios = new ArrayList<Usuario>();
    }


}


//Inventory se encarga de guardar la informacion de los usuarios, los productos y las órdenes