package com.stock.management;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;


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
		// This avoids the NullPointerException i am not returning just null returing empty list from collection i will update it in refactor phase
		return Collections.emptyList();
	}
}