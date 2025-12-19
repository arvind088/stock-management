package com.stock.management;

public class StockService {
	private StockRepository repository;
	
	public StockService(StockRepository repository) {
		this.repository = repository;
		}
	
	public void registerItem(StockItem item) {
        // leaving it empty to make the code compile
    }
}