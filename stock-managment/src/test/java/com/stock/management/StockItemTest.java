package com.stock.management;

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
	
	@Test
	void eachProductShouldHaveAUniqueId() {
		
		StockItem item1 = new StockItem("RAM Module", 10, 45.00);
		StockItem item2 = new StockItem("RAM Module", 10, 45.00);
		
		assertNotNull(item1.getId(), "First item ID should not be null");
		assertNotEquals(item1.getId(), item2.getId(), "Each product should have a unique ID");
		}
}
