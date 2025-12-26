package com.stock.management;

import java.util.List;

public interface StockRepository {
	void save(StockItem item);
	List<StockItem> findAll();
	
	void delete(StockItem item);
	}