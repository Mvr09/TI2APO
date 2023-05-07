package ui;

import model.Inventory;

import model.ProductType;

public class Main {
    public static void main(String[] args) {
        Inventory inventoryController = new Inventory();

        inventoryController.addProduct("ProductA", 10.0, "An electronic gadget", 5, 20, ProductType.ELECTRONICA);
        inventoryController.addProduct("ProductB", 20.0, "A fun toy", 3, 15, ProductType.JUGUETES_JUEGOS);

        Product foundProduct = inventoryController.searchProduct("name", "ProductA");
        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct.getName());
        } else {
            System.out.println("Product not found");
        }
    }
}

