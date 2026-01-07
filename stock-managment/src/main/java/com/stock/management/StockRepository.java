package com.stock.management;

import java.util.List;

public interface StockRepository {
	void save(StockItem item);
	List<StockItem> findAll();
	void delete(String name); // String is enough for a delete by name
	void update(String name, int quantity);
}