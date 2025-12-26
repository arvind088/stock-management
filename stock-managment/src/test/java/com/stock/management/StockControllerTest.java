package com.stock.management;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockControllerTest {
	private StockController controller;
	private StockView view;
	private StockService service;
	
	@BeforeEach
	void setUp() {
		view = mock(StockView.class);
		service = mock(StockService.class); // Mocking your existing StockService
		controller = new StockController(view, service);
		}
	
	@Test
	void testAddNewItemShouldCallServiceRegister() {
		StockItem item = new StockItem("Apple", 10, 1);
		controller.addNewItem(item);
		
		// This will FAIL because addNewItem() is empty
		verify(service).registerItem(item);
		}
}
