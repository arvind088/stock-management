package com.stock.management;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.bson.Document;
import org.junit.Before;
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


public class MongoStockRepositoryIT {

    // 1. Start the container once for all tests in this file
    @Container
    public static MongoDBContainer mongo = new MongoDBContainer("mongo:6.0.5");

    private MongoClient client;
    private MongoStockRepository repository;
    private MongoCollection<Document> collection;

    @Before
    public void setup() {
        client = MongoClients.create(mongo.getReplicaSetUrl());
        repository = new MongoStockRepository(client, "test-db", "test-collection");
        collection = client.getDatabase("test-db").getCollection("test-collection");
        collection.drop(); // Start fresh for every test
    }

    @Test
    public void testSaveAndFindAll() {
        StockItem item = new StockItem("Laptop", 5, 1200.0);
        repository.save(item);
        
        assertThat(repository.findAll()).containsExactly(item);
    }
}