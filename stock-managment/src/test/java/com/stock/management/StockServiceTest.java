package com.stock.management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockServiceTest {
	private StockRepository mockRepo;
	private StockService stockService;
	
	@BeforeEach
	void setUp() {
		mockRepo = mock(StockRepository.class);
		stockService = new StockService(mockRepo);
		}
	
	@Test
	void testRegisterItemCallsRepositorySave() {
		StockItem item = new StockItem("Laptop", 10, 1200.0);
		stockService.registerItem(item);
		
		// This will FAIL because the skeleton method is empty
		verify(mockRepo, times(1)).save(item);
		}
	
	@Test
	void testRegisterNullItemThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			stockService.registerItem(null);
			});
		}
}