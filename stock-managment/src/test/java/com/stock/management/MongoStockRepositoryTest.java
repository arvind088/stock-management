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

	// To starts a real MongoDB instance in a Docker container
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.5");

	private MongoClient client;
	private MongoStockRepository repository;
	private MongoCollection<Document> stockCollection;
	
	@BeforeEach
	void setup() {
		// Connect to the container's dynamic URL
		client = MongoClients.create(mongoDBContainer.getReplicaSetUrl());
		repository = new MongoStockRepository(client, "test-db", "test-collection");
		
		MongoDatabase database = client.getDatabase("test-db");
		stockCollection = database.getCollection("test-collection");
		stockCollection.drop(); // Ensure a clean slate for every test
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
}