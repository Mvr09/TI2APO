package ui;

import model.Inventory;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        Inventory inventoryController = loadData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add product");
            System.out.println("2. Delete product");
            System.out.println("3. Search product");
            System.out.println("4. Search by quantity range");
            System.out.println("5. Search by prefix range");
            System.out.println("6. Search by attribute");
            System.out.println("0. Exit");

            System.out.print("Please enter your choice (0-6): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                saveData(inventoryController);
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
                case 4:
                    System.out.print("Enter minimum quantity: ");
                    int minQuantity = scanner.nextInt();
                    System.out.print("Enter maximum quantity: ");
                    int maxQuantity = scanner.nextInt();

                    List<String> productsByQuantity = inventoryController.searchByQuantityRange(minQuantity, maxQuantity);
                    System.out.println("Products within the specified quantity range:");
                    for (String product : productsByQuantity) {
                        System.out.println(product);
                    }
                    break;

                case 5:
                    System.out.print("Enter starting name prefix: ");
                    String prefixStart = scanner.next();
                    System.out.print("Enter ending name prefix: ");
                    String prefixEnd = scanner.next();

                    List<String> productsByNameRange = inventoryController.searchByNameRange(prefixStart, prefixEnd);
                    System.out.println("Products within the specified name range:");
                    for (String product : productsByNameRange) {
                        System.out.println(product);
                    }
                    break;

                case 6:
                    System.out.println("Enter the attribute to sort by: (name, price, description, numSold, numStored, type)");
                    String attribute1 = scanner.next();
                    System.out.println("Enter sorting order (1 for ascending, 2 for descending): ");
                    int sortOrder = scanner.nextInt();
                    boolean ascending = (sortOrder == 1);

                    Comparator<String> comparator = inventoryController.getComparatorForAttribute(attribute1);
                    List<String> sortedProducts = inventoryController.sort(comparator, ascending);
                    System.out.println("Sorted products:");
                    for (String product : sortedProducts) {
                        System.out.println(product);
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void saveData(Inventory inventory) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("inventory_data.ser"))) {
            objectOutputStream.writeObject(inventory);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static Inventory loadData() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("inventory_data.ser"))) {
            return (Inventory) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No data found, initializing an empty inventory.");
            return new Inventory();
        }
    }
}
