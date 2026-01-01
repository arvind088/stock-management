package com.stock.management;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

public class MongoStockRepository implements StockRepository {

	private MongoCollection<Document> collection;
	
	public MongoStockRepository(MongoClient client, String dbName, String collectionName) {
		this.collection = client.getDatabase(dbName).getCollection(collectionName);
		}
	
	@Override
	public void save(StockItem item) {
		// Minimum code to pass: Wrap the item in a Document and insert it
		Document doc = new Document()
				.append("name", item.getName())
				.append("price", item.getPrice())
				.append("quantity", item.getQuantity());
		collection.insertOne(doc);
		}

	@Override
	public List<StockItem> findAll() {
		// Minimum code to pass: Map all documents back to StockItem objects
		return StreamSupport.stream(collection.find().spliterator(), false)
				.map(doc -> new StockItem(
						doc.getString("name"),
						doc.getInteger("quantity"),
						doc.getDouble("price")))
				.collect(Collectors.toList());
		}

	@Override
	public void delete(StockItem item) {
		
	}
}