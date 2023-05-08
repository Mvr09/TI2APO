package test;

import model.Inventory;
import model.Product;
import model.ProductType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setUp() {
        inventory = new Inventory();
        inventory.addProduct("ProductA", 10.0, "An electronic gadget", 5, 20, "ELECTRONICA");
        inventory.addProduct("ProductB", 20.0, "A fun toy", 3, 15, "JUGUETES_JUEGOS");
    }

    @Test
    public void testAddProduct() {
        inventory.addProduct("ProductC", 30.0, "A popular book", 7, 50, "LIBROS");
        Product foundProduct = inventory.searchProduct("name", "ProductC");
        assertNotNull(foundProduct);
        assertEquals("ProductC", foundProduct.getName());
        assertEquals(30.0, foundProduct.getPrice(), 0.0);
        assertEquals("A popular book", foundProduct.getDescription());
        assertEquals(7, foundProduct.getNumSold());
        assertEquals(50, foundProduct.getNumStored());
        assertEquals(ProductType.LIBROS, foundProduct.getType());

        // Adding an identical product to verify its increasing the amount
        inventory.addProduct("ProductC", 30.0, "A popular book", 0, 25, "LIBROS");
        foundProduct = inventory.searchProduct("name", "ProductC");
        assertNotNull(foundProduct);
        assertEquals("ProductC", foundProduct.getName());
        assertEquals(30.0, foundProduct.getPrice(), 0.0);
        assertEquals("A popular book", foundProduct.getDescription());
        assertEquals(7, foundProduct.getNumSold());
        assertEquals(75, foundProduct.getNumStored());
        assertEquals(ProductType.LIBROS, foundProduct.getType());

    }

    @Test
    public void testSearchProduct() {
        Product foundProduct;

        foundProduct = inventory.searchProduct("name", "ProductA");
        assertEquals("ProductA", foundProduct.getName());

        foundProduct = inventory.searchProduct("price", "10.0");
        assertEquals(10.0, foundProduct.getPrice(), 0.0);

        foundProduct = inventory.searchProduct("description", "A fun toy");
        assertEquals("A fun toy", foundProduct.getDescription());

        foundProduct = inventory.searchProduct("numSold", "3");
        assertEquals(3, foundProduct.getNumSold());

        foundProduct = inventory.searchProduct("numStored", "15");
        assertEquals(15, foundProduct.getNumStored());

        foundProduct = inventory.searchProduct("type", "JUGUETES_JUEGOS");
        assertEquals(ProductType.JUGUETES_JUEGOS, foundProduct.getType());
    }

    @Test
    public void testSearchNonExistentProduct() {
        Object foundProduct = inventory.searchProduct("name", "ProductX");
        assertNull(foundProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAttribute() {
        inventory.searchProduct("invalidAttribute", "value");
    }
}
