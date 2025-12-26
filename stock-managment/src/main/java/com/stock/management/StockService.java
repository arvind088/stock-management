package com.stock.management;

import java.util.List;

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
	
	public List<StockItem> getAllItems() {
		return repository.findAll();
	}
	public void delete(StockItem item) {
		// This can remain empty or call repository.delete(item)
	}
}