package test;

import model.Inventory;
import model.Pedido;
import model.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PedidoTest {
    private Inventory inventory;

    @BeforeEach
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
}
