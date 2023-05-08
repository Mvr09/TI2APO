package ui;

import model.Inventory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventory inventoryController = new Inventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add product");
            System.out.println("2. Delete product");
            System.out.println("3. Search product");
            System.out.println("4. Exit");

            System.out.print("Please enter your choice (1-4): ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product description: ");
                    String description = scanner.next();
                    System.out.print("Enter product numSold: ");
                    int numSold = scanner.nextInt();
                    System.out.print("Enter product numStored: ");
                    int numStored = scanner.nextInt();
                    System.out.print("Enter product type: ");
                    String type = scanner.next();

                    inventoryController.addProduct(name, price, description, numSold, numStored, type);
                    System.out.println("Product added successfully!");
                    break;

                case 2:
                    System.out.print("Enter product name to delete: ");
                    String productName = scanner.next();

                    inventoryController.deleteProduct(productName);
                    break;

                case 3:
                    System.out.println("Enter the attribute to search by: (name, price, description, numSold, numStored, type)");
                    String attribute = scanner.next();
                    System.out.print("Enter the value to search for: ");
                    String value = scanner.next();

                    String foundProduct = inventoryController.binarySearch(attribute, value);
                    if (foundProduct != null) {
                        System.out.println("Product found: " + foundProduct);
                    } else {
                        System.out.println("Product not found");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
