package com.stock.management;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;

class StockServiceTest {
	private StockRepository repository;
	private StockService service;
	
	@BeforeEach
    void setUp() {
        repository = mock(StockRepository.class); // Mock the DB layer
        service = new StockService(repository); // REAL service logic
    }
	
	@Test
	void testRegisterItemCallsRepositorySave() {
		StockItem item = new StockItem("Laptop", 10, 1200.0);
		service.registerItem(item);
		
		// This will FAIL because the skeleton method is empty
		verify(repository, times(1)).save(item);
		}
	
	@Test
	void testRegisterNullItemThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.registerItem(null);
			});
		}
	
	@Test
	void testGetAllItemsCallsRepository() {
		List<StockItem> items = Arrays.asList(new StockItem("Laptop", 1, 500.0));
		when(repository.findAll()).thenReturn(items);
		List<StockItem> result = service.getAllItems();
		assertEquals(1, result.size());
		verify(repository, times(1)).findAll();
		}

	@Test
	void testDeleteShouldCallRepositoryDelete() {
		StockItem item = new StockItem("Apple", 10, 1.5);
        service.delete(item);
        verify(repository).delete(item);
        }
	
}