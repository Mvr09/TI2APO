package test;

import model.Inventory;
import model.Pedido;
import model.Product;
import model.ProductType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PedidoTest {
    private Inventory inventory;


    public void setUp() {
        inventory = new Inventory();
        inventory.addProduct("T-shirt", 19.99, "A comfortable T-shirt", 10, 50, "ROPA_ACCESORIOS");
    }

    @Test
    public void testCreatePedido() {
        Pedido newPedido = inventory.createPedido("JohnDoe");
        assertNotNull(newPedido);
        assertEquals(1, newPedido.getId());
        assertEquals("JohnDoe", newPedido.getUsername());
        assertEquals(0, newPedido.getPrice());
    }

    @Test
    public void testAddProductToOrder() {
        Pedido newPedido = inventory.createPedido("JohnDoe");
        inventory.addProductToOrder(newPedido.getId(), "T-shirt", 2);

        assertEquals(1, newPedido.getProducts().size());
        assertEquals("T-shirt", newPedido.getProducts().get(0).getName());
        assertEquals(2, newPedido.getProducts().get(0).getNumStored());
        assertEquals(39.98, newPedido.getPrice());
    }

    @Test
    public void testAddNonExistentProductToOrder() {
        Pedido newPedido = inventory.createPedido("JohnDoe");
        inventory.addProductToOrder(newPedido.getId(), "NonExistent", 1);

        assertEquals(0, newPedido.getProducts().size());
        assertEquals(0, newPedido.getPrice());
    }

    @Test
    public void testAddProductToOrderExceedingStock() {
        Pedido newPedido = inventory.createPedido("JohnDoe");
        inventory.addProductToOrder(newPedido.getId(), "T-shirt", 60);

        assertEquals(0, newPedido.getProducts().size());
        assertEquals(0, newPedido.getPrice());
    }

    @Test
    public void testUpdateInventoryAfterAddingProductToOrder() {
        Pedido newPedido = inventory.createPedido("JohnDoe");
        inventory.addProductToOrder(newPedido.getId(), "T-shirt", 2);

        assertEquals(48, inventory.searchProduct("name", "T-shirt").getNumStored());
    }
    @Test
    public void deleteProduct1(){
        List<Product> products = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.addProduct("ProductA", 10.0, "An electronic gadget", 5, 20, "ELECTRONICA");
        inventory.addProduct("ProductB", 20.0, "A fun toy", 3, 15, "JUGUETES_JUEGOS");
        inventory.addProduct("ProductC",5.00,"A best seller book", 7, 15, "LIBROS");

        inventory.deleteProduct("ProductB");
        assertEquals(0, products.size());
        assertEquals("ProducA", products.get(0).getName());
        assertEquals("ProductC", products.get(2).getName());

    }
    @Test
    public void deleteProduct2() {
        List<Product> products = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.addProduct("ProductB", 10.0, "An electronic gadget", 5, 20, "ELECTRONICA");
        inventory.addProduct("ProductC", 20.0, "A fun toy", 3, 15, "JUGUETES_JUEGOS");
        inventory.addProduct("ProductD", 5.00, "A best seller book", 7, 15, "LIBROS");


        inventory.deleteProduct("ProductB");
        assertEquals(0, products.size());
        assertEquals("ProductC", products.get(0).getName());
        assertEquals("ProductD", products.get(2).getName());
    }



}
