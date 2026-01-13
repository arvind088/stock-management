package com.stock.management;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
	
	@Test
	void testValidConstruction() {
		StockItem item = new StockItem("Laptop", 10, 1000.0);
		assertAll("Verify all fields",
			() -> assertNotNull(item.getId()),
			() -> assertEquals("Laptop", item.getName()),
			() -> assertEquals(10, item.getQuantity()),
			() -> assertEquals(1000.0, item.getPrice())
		);
	}

	@Test
	void testNegativeQuantityThrows() {
		IllegalArgumentException ex = assertThrows(
			IllegalArgumentException.class,
			() -> new StockItem("Laptop", -1, 1000.0)
		);
		assertEquals("Quantity cannot be negative", ex.getMessage());
	}
	
	@Test
	void testNegativePriceThrows() {
		IllegalArgumentException ex = assertThrows(
			IllegalArgumentException.class,
			() -> new StockItem("Laptop", 10, -5.0)
		);
		assertEquals("Price cannot be negative", ex.getMessage());
	}

	@Test
	void testQuantityAtZeroShouldBeAllowed() {
		StockItem item = new StockItem("Apple", 0, 1.0);
		assertEquals(0, item.getQuantity());
	}

	@Test
	void testPriceAtZeroShouldBeAllowed() {
		double zeroPrice = 0.0;
		StockItem item = new StockItem("Free Sample", 10, zeroPrice);
		assertEquals(zeroPrice, item.getPrice(), 0.001);
	}
	
	@Test
	void testHashCode() {
		StockItem item1 = new StockItem("Apple", 10, 1.5);
		StockItem item2 = new StockItem("Apple", 10, 1.5);
		StockItem item3 = new StockItem("Orange", 99, 9.9);

		assertThat(item1).isEqualTo(item2);
		assertThat(item1.hashCode()).isEqualTo(item2.hashCode());

		// Kills "return 0" mutant
		assertThat(item1.hashCode()).isNotEqualTo(item3.hashCode());
	}
	
	@Test
	void testEqualsBranchCoverage() {
		StockItem base = new StockItem("Apple", 10, 1.5);

		// 1️⃣ same reference → true
		assertThat(base.equals(base)).isTrue();

		// 2️⃣ null comparison → false
		assertThat(base.equals(null)).isFalse();

		// 3️⃣ different class → false
		assertThat(base.equals("not a StockItem")).isFalse();

		// 4️⃣ price differs → false
		assertThat(base.equals(new StockItem("Apple", 10, 9.9))).isFalse();

		// 5️⃣ quantity differs → false
		assertThat(base.equals(new StockItem("Apple", 99, 1.5))).isFalse();

		// 6️⃣ name differs → false
		assertThat(base.equals(new StockItem("Orange", 10, 1.5))).isFalse();

		// 7️⃣ all fields equal → true
		assertThat(base.equals(new StockItem("Apple", 10, 1.5))).isTrue();
	}
}
