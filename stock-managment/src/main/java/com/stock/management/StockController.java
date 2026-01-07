package com.stock.management;

public class StockController {
	private StockView view;
	private StockService service;
	
	public StockController(StockView view, StockService service) {
		this.view = view;
		this.service = service;
	}

	public void addNewItem(StockItem item) {
		service.registerItem(item);
		view.showAllStock(service.getAllItems()); 
	}

	public void updateStockItem(StockItem item, int newQuantity) {
		service.updateItemQuantity(item, newQuantity);
		view.showAllStock(service.getAllItems());
	}

	public void deleteStockItem(StockItem item) {
		// FIX: Use the corrected service method name
		service.deleteItem(item); 
		view.showAllStock(service.getAllItems());
	}
}