package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Inventory {
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
    public Product binarySearch(String attribute, String value) {
        ProductComparator comparator = new ProductComparator(attribute);
        Collections.sort(products, comparator);

        int low = 0;
        int high = products.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            Product midProduct = products.get(mid);
            // Creating a new temporal product with the searched attribute to compare
            Product tempProduct;
            if (attribute.equals("name") || attribute.equals("description")) {
                tempProduct = new Product(value, attribute);
            } else if (attribute.equals("price")) {
                tempProduct = new Product(Double.parseDouble(value));
            } else if (attribute.equals("numSold")) {
                tempProduct = new Product(Integer.parseInt(value));
            } else if (attribute.equals("type")) {
                tempProduct = new Product(ProductType.valueOf(value));
            } else {
                return null; // Invalid attribute
            }

            int comparison = comparator.compare(midProduct, tempProduct);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return midProduct;
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
    public List<Product> searchByPriceRange(List<Product> products, double minPrice, double maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchByQuantityRange(List<Product> products, int minQuantity, int maxQuantity) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getAvailableQuantity() >= minQuantity && product.getAvailableQuantity() <= maxQuantity) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchByNameRange(List<Product> products, String prefixStart, String prefixEnd) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            String name = product.getName();
            if (name != null && name.compareToIgnoreCase(prefixStart) >= 0 && name.compareToIgnoreCase(prefixEnd) <= 0) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> sort(List<Product> products, Comparator<Product> comparator, boolean ascending) {
        List<Product> result = new ArrayList<>(products);
        if (ascending) {
            Collections.sort(result, comparator);
        } else {
            Collections.sort(result, Collections.reverseOrder(comparator));
        }
        return result;
    }


}
