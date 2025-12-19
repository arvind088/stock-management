package com.stock.management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

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
	
	@Test
	void testGetAllItemsCallsRepository() {
		List<StockItem> items = Arrays.asList(new StockItem("Laptop", 1, 500.0));
		when(mockRepo.findAll()).thenReturn(items);
		List<StockItem> result = stockService.getAllItems();
		assertEquals(1, result.size());
		verify(mockRepo, times(1)).findAll();
		}
	
}