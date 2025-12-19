package com.stock.management;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class StockServiceTest {
    @Test
    void testRegisterNewItemCallsRepository() {
       
        StockRepository mockRepo = mock(StockRepository.class);
        // This will fail to compile because StockService doesn't exist yet! (to handle that i make a stock service class)
        StockService service = new StockService(mockRepo); 
        StockItem item = new StockItem("Laptop", 10, 500.0);

        service.registerItem(item);

        verify(mockRepo, times(1)).save(item);
    }
}