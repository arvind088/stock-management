package com.stock.management;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Testcontainers
class MongoStockRepositoryTest {
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.5");
	
	private MongoClient client;
	private MongoStockRepository repository;
	private MongoCollection<Document> stockCollection;
	
	@BeforeEach
	void setup() {
		client = MongoClients.create(mongoDBContainer.getReplicaSetUrl());
		repository = new MongoStockRepository(client, "test-db", "test-collection");
		
		MongoDatabase database = client.getDatabase("test-db");
		stockCollection = database.getCollection("test-collection");
		stockCollection.drop(); 
		}

	@AfterEach
	void tearDown() {
		if (client != null) {
			client.close();
			}
		}

	@Test
	void testSaveAndFindAll() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		repository.save(item);
		List<StockItem> items = repository.findAll();
		assertThat(items).containsExactly(item);
		}

	@Test
	void testDelete() {
		StockItem item = new StockItem("Orange", 5, 2.0);
		repository.save(item);
		
		repository.delete("Orange");
		
		assertThat(repository.findAll()).isEmpty();
		}

	@Test
	void testUpdate() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		repository.save(item);
		
		repository.update("Apple", 50);
		
		List<StockItem> items = repository.findAll();
		assertThat(items).hasSize(1);
		assertThat(items.get(0).getQuantity()).isEqualTo(50);
		}
}