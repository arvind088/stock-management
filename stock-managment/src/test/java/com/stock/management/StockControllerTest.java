package com.stock.management;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.anyList;

public class StockControllerTest {
	private StockController controller;
	private StockView view;
	private StockService service;

	@BeforeEach
	void setUp() {
		view = mock(StockView.class);
		service = mock(StockService.class);
		controller = new StockController(view, service);
		
		// Provide a default return to avoid NullPointer on refresh
		when(service.getAllItems()).thenReturn(Collections.emptyList());
	}

	@Test
	void testAddNewItemRefreshesView() {
		StockItem item = new StockItem("Apple", 10, 1.0);
		controller.addNewItem(item);
		verify(service).registerItem(item);
		verify(view).showAllStock(anyList());
	}

	@Test
	void testUpdateItemRefreshesView() {
		StockItem item = new StockItem("Apple", 10, 1.0);
		controller.updateStockItem(item, 50);
		verify(service).updateItemQuantity(item, 50);
		verify(view).showAllStock(anyList());
	}

	@Test
	void testDeleteItemRefreshesView() {
		StockItem item = new StockItem("Apple", 10, 1.0);
		controller.deleteStockItem(item);
		verify(service).deleteItem(item);
		verify(view).showAllStock(anyList());
	}
}