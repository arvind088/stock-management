package com.stock.management;

import java.util.UUID;

public class StockItem {
	
	private final String id;
	private final String name;
	private final int quantity;
	private final double price;

	public StockItem(String name, int quantity, double price) {
		//REFACTOR 
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
			}
		if (price < 0) {
			throw new IllegalArgumentException("Price cannot be negative");
			}
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		}
		
	public String getId() {
		return id; 
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
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    StockItem other = (StockItem) obj;
	    // We compare values so the test passes regardless of memory address
	    return Double.compare(other.price, price) == 0 && 
	           quantity == other.quantity && 
	           java.util.Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
	    return java.util.Objects.hash(name, quantity, price);
	}
}
