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
    // Method to convert an ArrayList to a BinarySearchTree
    private <T extends Comparable<T>> BinarySearchTree<T> arrayListToBST(ArrayList<T> arrayList) {
        BinarySearchTree<T> bst = new BinarySearchTree<>();
        // Iterate through the arrayList and insert each item into the bst
        for (T item : arrayList) {
            bst.insert(item);
        }
        return bst;
    }

    // Method to convert a BinarySearchTree to an ArrayList
    private <T extends Comparable<T>> ArrayList<T> bstToArraylist(BinarySearchTree<T> bst) {
        ArrayList<T> arrayList = new ArrayList<>();
        // Perform inorder traversal to get the sorted elements in the arrayList
        bst.inorderTraversal(bst.getRoot(), arrayList);
        return arrayList;
    }

    // Method to insert a product into the products ArrayList
    public void insertProduct(Product product) {
        // Convert products ArrayList to a BinarySearchTree
        BinarySearchTree<Product> bst = arrayListToBST(products);
        // Insert the product into the bst
        bst.insert(product);
        // Convert bst back to ArrayList and update the products ArrayList
        products = bstToArraylist(bst);
    }

    // Method to delete a product from the products ArrayList
    public void deleteProduct(Product product) {
        // Convert products ArrayList to a BinarySearchTree
        BinarySearchTree<Product> bst = arrayListToBST(products);
        // Remove the product from the bst
        bst.remove(product);
        // Convert bst back to ArrayList and update the products ArrayList
        products = bstToArraylist(bst);
    }

    // Method to search for a product in the products ArrayList
    public Product searchProduct(Product product) {
        // Convert products ArrayList to a BinarySearchTree
        BinarySearchTree<Product> bst = arrayListToBST(products);
        // Search for the product in the bst
        return bst.search(product);
    }
}


//Inventory se encarga de guardar la informacion de los usuarios, los productos y las órdenes