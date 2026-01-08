package com.stock.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

@Testcontainers
public class MongoStockRepositoryIT {

    @Container
    public static MongoDBContainer mongo = new MongoDBContainer("mongo:6.0.5");

    private MongoClient client;
    private MongoStockRepository repository;
    private MongoCollection<Document> collection;

    @BeforeEach
    void setup() {
        client = MongoClients.create(mongo.getReplicaSetUrl());
        repository = new MongoStockRepository(client, "test-db", "test-collection");
        collection = client.getDatabase("test-db").getCollection("test-collection");
        collection.drop(); // Start fresh for every test
    }

    @Test
    void testSaveAndFindAll() {
        StockItem item = new StockItem("Laptop", 5, 1200.0);

        repository.save(item);

        assertThat(repository.findAll()).containsExactly(item);
    }
}
