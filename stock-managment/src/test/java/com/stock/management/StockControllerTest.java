package com.stock.management;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
public class StockControllerTest {
	private StockController controller;
	private StockView view;
	private StockService service;
	private StockRepository repository;

	
	@BeforeEach
	void setUp() {
		view = mock(StockView.class);
		service = mock(StockService.class); // Mocking your existing StockService
		repository = mock(StockRepository.class);
		controller = new StockController(view, service);
		}
	
	@Test
	void testAddNewItemShouldCallServiceRegister() {
		StockItem item = new StockItem("Apple", 10, 1);
		controller.addNewItem(item);
		verify(service).registerItem(item);
		}
	@Test
	void testDeleteItemShouldCallServiceDelete() {
	    
		StockItem item = new StockItem("Apple", 10, 1.5);
		controller.deleteItem(item);
		verify(service).delete(item);
	}

	@Test
	void testUpdateItemShouldCallServiceAndRefreshView() {
		StockItem itemToUpdate = new StockItem("Laptop", 5, 1000.0);
		int updatedQty = 15;
		
		controller.updateStockItem(itemToUpdate, updatedQty);
		verify(service).updateItemQuantity(itemToUpdate, updatedQty);
		verify(view).showAllStock(anyList());
	}
	
	@Test
	void testUpdate() {
		// 1. Setup: Save an item
		StockItem item = new StockItem("Apple", 10, 1.5);
		repository.save(item);
		
		// 2. Action: Update the quantity to 50
		repository.update("Apple", 50);
		
		// 3. Assertion: Verify the change persisted
		List<StockItem> items = repository.findAll();
		assertThat(items).hasSize(1);
		assertThat(items.get(0).getQuantity()).isEqualTo(50);
		}
}
