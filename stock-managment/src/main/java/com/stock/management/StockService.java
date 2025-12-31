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
		repository.delete(item);
	}
	
	public void updateItemQuantity(StockItem item, int newQuantity) {
		StockItem updatedItem = new StockItem(item.getName(), newQuantity, item.getPrice());
		repository.save(updatedItem);
	   
	}
}