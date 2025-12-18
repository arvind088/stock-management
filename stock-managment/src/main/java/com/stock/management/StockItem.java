package com.stock.management;

public class StockItem {
	
	private final String name;
	private final int quantity;
	private final double price;

	public StockItem(String name, int quantity, double price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		}
	
	public String getId() {
		return null; 
		}
	
	public String getName() {
		return name;
		}
	
	public int getQuantity() {
		return quantity;
		}
	
	public double getPrice() {
		return price;
		}
}
