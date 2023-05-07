package model;

import java.util.ArrayList;
import java.util.Collections;

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
    public void addProduct(String name, double price, String description, int numSold, int numStored, String type) {
        products.add(new Product(name, price, description, numSold, numStored, ProductType.valueOf(type)));
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

    //Deprecated, no usaremos bst
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