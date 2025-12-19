package com.stock.management;

public class StockService {
	private StockRepository repository;
	
	public StockService(StockRepository repository) {
		this.repository = repository;
		}
	
	public void registerItem(StockItem item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null");
			}
		repository.save(item);
		}
}