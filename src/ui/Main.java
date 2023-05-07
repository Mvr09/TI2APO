package ui;

import model.Inventory;

public class Main {
    public static void main(String[] args) {
        Inventory inventoryController = new Inventory();

        inventoryController.addProduct("ProductA", 10.0, "An electronic gadget", 5, 20, "ELECTRONICA");
        inventoryController.addProduct("ProductB", 20.0, "A fun toy", 3, 15, "JUGUETES_JUEGOS");

        String attributeName = "name";
        String searchValue = "ProductA";

        Object foundProduct = inventoryController.searchProduct(attributeName, searchValue);
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct);
        } else {
            System.out.println("Product not found");
        }
    }
}
