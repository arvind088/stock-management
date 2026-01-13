package com.stock.management;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

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
	void testRegisterItemNullThrowsException() {
		assertThatThrownBy(() -> service.registerItem(null))
			.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void testGetAllItemsReturnsRepositoryItems() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		List<StockItem> repoItems = List.of(item);

		when(repository.findAll()).thenReturn(repoItems);

		List<StockItem> result = service.getAllItems();

		assertThat(result).isEqualTo(repoItems);
		assertThat(result).hasSize(1);
		assertThat(result).containsExactly(item);

		verify(repository).findAll();
	}
}
