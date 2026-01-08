package com.stock.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import org.testcontainers.containers.MongoDBContainer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class StockAppE2E extends AssertJSwingJUnitTestCase {

    private static final String DB_NAME = "test-db";
    private static final String COLLECTION_NAME = "items";

    public static final MongoDBContainer mongo = new MongoDBContainer("mongo:6.0.5");

    private FrameFixture window;
    private MongoClient mongoClient;

    @Override
    protected void onSetUp() {
        if (!mongo.isRunning()) mongo.start();

        String mongoUri = mongo.getReplicaSetUrl();
        mongoClient = MongoClients.create(mongoUri);
        mongoClient.getDatabase(DB_NAME).drop();

        StockView view = GuiActionRunner.execute(() -> {
            StockRepository repository = new MongoStockRepository(mongoClient, DB_NAME, COLLECTION_NAME);
            StockService service = new StockService(repository);
            StockView v = new StockView();
            StockController controller = new StockController(v, service);
            v.setController(controller);
            return v;
        });

        window = new FrameFixture(robot(), view);
        window.show();

        robot().settings().delayBetweenEvents(500);
    }

    @Override
    protected void onTearDown() {
        if (window != null) window.cleanUp();
        if (mongoClient != null) mongoClient.close();
        // optional:
        // if (mongo.isRunning()) mongo.stop();
    }

    @Test
    public void testAddAndRemoveProduct() {
        window.textBox("txtName").enterText("E2E Laptop");
        window.textBox("txtQuantity").enterText("5");
        window.textBox("txtPrice").enterText("1000");
        window.button(JButtonMatcher.withName("btnAdd")).click();

        assertThat(window.table().contents()[0]).contains("E2E Laptop", "5", "1000.0");

        window.table().selectRows(0);
        window.button(JButtonMatcher.withName("btnDelete")).click();

        assertThat(window.table().contents()).isEmpty();
    }
}
