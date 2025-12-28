package com.stock.management;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
