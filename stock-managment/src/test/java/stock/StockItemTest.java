package stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockItemTest {
	
	@Test
	void productShouldBeInitializedWithNameQuantityAndPrice() {

		String name = "RAM Module 16GB";
		int quantity = 50;
		double price = 45.00;
		
		
		StockItem item = new StockItem(name, quantity, price);
		
		
		assertNotNull(item, "Product should not be null");
		assertEquals(name, item.getName(), "Name should be set");
		assertEquals(quantity, item.getQuantity(), "Quantity should be set");
		assertEquals(price, item.getPrice(), 0.001, "Price should be set");
    }
}
