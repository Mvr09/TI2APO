package model;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    private String attribute;

    public ProductComparator(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public int compare(Product p1, Product p2) {
        switch (attribute) {
            case "name":
                return p1.getName().compareTo(p2.getName());
            case "price":
                return Double.compare(p1.getPrice(), p2.getPrice());
            case "description":
                return p1.getDescription().compareTo(p2.getDescription());
            case "numSold":
                return Integer.compare(p1.getNumSold(), p2.getNumSold());
            case "numStored":
                return Integer.compare(p1.getNumStored(), p2.getNumStored());
            case "type":
                return p1.getType().compareTo(p2.getType());
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }
}