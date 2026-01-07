package com.stock.management;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockServiceTest {
	private StockRepository repository;
	private StockService service;
	
	@BeforeEach
	void setUp() {
		repository = mock(StockRepository.class);
		service = new StockService(repository);
	}
	
	@Test
	void testRegisterItemCallsSave() {
		StockItem item = new StockItem("Laptop", 10, 1200.0);
		service.registerItem(item);
		verify(repository).save(item);
	}

	@Test
	void testDeleteItemCallsDelete() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		service.deleteItem(item);
		verify(repository).delete("Apple");
	}

	@Test
	void testUpdateQuantityCallsUpdate() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		service.updateItemQuantity(item, 25);
		verify(repository).update("Apple", 25);
	}
	
	@Test
	public void testRegisterItemNullThrowsException() {
		assertThatThrownBy(() -> service.registerItem(null))
				.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	public void testGetAllItemsCallsRepository() {
		service.getAllItems();
		verify(repository).findAll();
	}
}