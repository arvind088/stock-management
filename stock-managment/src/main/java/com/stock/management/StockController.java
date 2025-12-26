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
		}
}