package model;

import java.util.*;
import java.io.Serializable;

public class Inventory implements Serializable {
    private ArrayList<Pedido> pedidos;
    private ArrayList<Product> products;
    private ArrayList<Usuario> usuarios;

    // Default constructor
    public Inventory() {
        this.pedidos = new ArrayList<Pedido>();
        this.products = new ArrayList<Product>();
        this.usuarios = new ArrayList<Usuario>();
    }

    // Checks for duplicate and adds to numStored if found. Criteria is name
    public void addProduct(String name, double price, String description, int numSold, int numStored, String type) {
        Product existingProduct = searchProduct("name", name);
        if (existingProduct != null &&
                existingProduct.getName() == name &&
                existingProduct.getPrice() == price) {
            existingProduct.setNumStored(existingProduct.getNumStored() + numStored);
        } else {
            products.add(new Product(name, price, description, numSold, numStored, ProductType.valueOf(type)));
        }
    }

    public void deleteProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getName().equals(name)) {
                products.remove(i);
                System.out.println("Product " + name + " deleted successfully.");
                return;
            }
        }
        System.out.println("Product " + name + " not found.");
    }

    public Product searchProduct(String attribute, String value) {
        ProductComparator comparator = new ProductComparator(attribute);
        Collections.sort(products, comparator);

        Product searchProduct = new Product();
        switch (attribute) {
            case "name":
                searchProduct.setName(value);
                break;
            case "price":
                searchProduct.setPrice(Double.parseDouble(value));
                break;
            case "description":
                searchProduct.setDescription(value);
                break;
            case "numSold":
                searchProduct.setNumSold(Integer.parseInt(value));
                break;
            case "numStored":
                searchProduct.setNumStored(Integer.parseInt(value));
                break;
            case "type":
                searchProduct.setType(ProductType.valueOf(value));
                break;
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }

        int index = Collections.binarySearch(products, searchProduct, comparator);
        if (index >= 0) {
            return products.get(index);
        } else {
            return null;
        }
    }


    // Method using BinaryComparator for product
    // attribute: name, price, description, numSold, numStored
    public String binarySearch(String attribute, String value) {
        ProductComparator comparator = new ProductComparator(attribute);
        Collections.sort(products, comparator);

        int low = 0;
        int high = products.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products.get(mid);

            Product tempProduct = createTempProductForComparison(attribute, value);

            int comparison = comparator.compare(midProduct, tempProduct);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return midProduct.toString();
            }
        }
        return null; // Return null if no matching product is found
    }


    private Product createTempProductForComparison(String attribute, String value) {
        String name = attribute.equals("name") ? value : "";
        double price = attribute.equals("price") ? Double.parseDouble(value) : 0;
        String description = attribute.equals("description") ? value : "";
        int numSold = attribute.equals("numSold") ? Integer.parseInt(value) : 0;
        int numStored = attribute.equals("numStored") ? Integer.parseInt(value) : 0;
        ProductType type = ProductType.ROPA_ACCESORIOS;

        return new Product(name, price, description, numSold, numStored, type);
    }

    public Pedido createPedido(String username) {
        int newId = pedidos.size() + 1;
        Date currentDate = new Date();
        Pedido newPedido = new Pedido(newId, username, 0, currentDate);
        pedidos.add(newPedido);
        return newPedido;
    }

    public void addProductToOrder(int orderId, String productName, int quantity) {
        Pedido foundPedido = null;

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == orderId) {
                foundPedido = pedido;
                break;
            }
        }

        if (foundPedido == null) {
            System.out.println("Order not found.");
            return;
        }

        try {
            foundPedido.addProductToOrder(this, productName, quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<String> searchByQuantityRange(int minQuantity, int maxQuantity) {
        List<String> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getNumStored() >= minQuantity && product.getNumStored() <= maxQuantity) {
                result.add(product.toString());
            }
        }
        return result;
    }

    public List<String> searchByNameRange(String prefixStart, String prefixEnd) {
        List<String> result = new ArrayList<>();
        for (Product product : products) {
            String name = product.getName();
            if (name != null && name.compareToIgnoreCase(prefixStart) >= 0 && name.compareToIgnoreCase(prefixEnd) <= 0) {
                result.add(product.toString());
            }
        }
        return result;
    }

    public List<String> sort(Comparator<String> comparator, boolean ascending) {
        List<String> result = new ArrayList<>();
        for (Product product : products) {
            result.add(product.toString());
        }
        if (ascending) {
            Collections.sort(result, comparator);
        } else {
            Collections.sort(result, Collections.reverseOrder(comparator));
        }
        return result;
    }

    public Comparator<String> getComparatorForAttribute(String attribute) {
        // Return a suitable Comparator<String> based on the attribute
        switch (attribute) {
            case "name":
                return Comparator.comparing(String::toString);
            case "price":
                // Extract price from the product string and compare
                return Comparator.comparingDouble(s -> Double.parseDouble(s.split(",")[1].trim()));
            case "description":
                // Extract description from the product string and compare
                return Comparator.comparing(s -> s.split(",")[2].trim());
            case "numSold":
                // Extract numSold from the product string and compare
                return Comparator.comparingInt(s -> Integer.parseInt(s.split(",")[3].trim()));
            case "numStored":
                // Extract numStored from the product string and compare
                return Comparator.comparingInt(s -> Integer.parseInt(s.split(",")[4].trim()));
            case "type":
                // Extract type from the product string and compare
                return Comparator.comparing(s -> s.split(",")[5].trim());
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }
}