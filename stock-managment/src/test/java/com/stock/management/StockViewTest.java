package com.stock.management;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

class StockViewTest {
	
	private FrameFixture window;
	private StockView view; // Keep a reference to the view directly
	
	@BeforeEach
	void setUp() {
		// Initialize the view and window correctly
		view = GuiActionRunner.execute(StockView::new);
		window = new FrameFixture(view);
		window.show();
	}
	
	@AfterEach
	void tearDown() {
		window.cleanUp();
	}
	
	@Test
	void window_should_be_visible() {
		window.requireVisible();
	}

	@Test
	void testNameTextFieldShouldBePresent() {
		window.textBox("txtName").requireVisible();
	}

	@Test
	void testPriceTextFieldShouldBePresent() {
		window.textBox("txtPrice").requireVisible();
	}

	@Test
	void testQuantityTextFieldShouldBePresent() {
		window.textBox("txtQuantity").requireVisible();
	}

	@Test
	void testAddButtonShouldBePresentAndDisabled() {
		window.button("btnAdd").requireVisible();
		window.button("btnAdd").requireDisabled();
	}

	@Test
	void testAddButtonShouldBeEnabledWhenAllFieldsAreNotEmpty() {
		window.textBox("txtName").enterText("Laptop");
		window.textBox("txtQuantity").enterText("10");
		window.textBox("txtPrice").enterText("500");
		window.button("btnAdd").requireEnabled();
	}

	@Test
	void testAddButtonShouldBeDisabledWhenAnyFieldIsCleared() {
		window.textBox("txtName").enterText("Laptop");
		window.textBox("txtQuantity").enterText("10");
		window.textBox("txtPrice").enterText("500");
		window.textBox("txtName").deleteText();
		window.button("btnAdd").requireDisabled();
	}

	@Test
	void testStockTableShouldBePresent() {
		window.table("stockTable").requireVisible();
	}

	@Test
	void testDeleteButtonShouldBePresentAndDisabled() {
		window.button("btnDelete").requireVisible();
		window.button("btnDelete").requireDisabled();
	}

	@Test
	void testErrorMessageLabelShouldBePresentAndEmpty() {
		window.label("errorMessageLabel").requireText(" ");
	}

	@Test
	void testDeleteButtonShouldBeEnabledOnlyWhenARowIsSelected() {
		// Setup table with data
		GuiActionRunner.execute(() -> {
			DefaultTableModel model = (DefaultTableModel) view.getStockTable().getModel();
			model.setRowCount(0);
			model.addRow(new Object[] {"Item 1", "10.0", "5"});
		});
		
		window.table("stockTable").selectRows(0);
		window.button("btnDelete").requireEnabled();
		
		window.table("stockTable").unselectRows(0);
		window.button("btnDelete").requireDisabled();
	}

	@Test
	void testGetStockTableShouldReturnTheTableComponent() {
		JTable table = view.getStockTable();
		assertThat(table).isNotNull();
		assertThat(table.getName()).isEqualTo("stockTable");
	}
	
	@Test
	public void testShowAllStockShouldUpdateTableRows() {
		StockItem item = new StockItem("Apple", 10, 1.5);
		List<StockItem> items = Arrays.asList(item);
		GuiActionRunner.execute(() -> view.showAllStock(items));
	
		String[][] contents = window.table("stockTable").contents();
		assertEquals(1, contents.length);
		assertEquals("Apple", contents[0][0]);
		assertEquals("1.5", contents[0][1]);
		assertEquals("10", contents[0][2]);
	}

	// --- NEW RED TEST FOR UPDATE BUTTON ---
	@Test
	public void testUpdateProductQuantityUpdatesTableDisplay() {
		// 1. Arrange: Put an item in and select it
		StockItem item = new StockItem("Apple", 10, 1.5);
		GuiActionRunner.execute(() -> view.showAllStock(Arrays.asList(item)));
		window.table("stockTable").selectRows(0);
		
		// 2. Act: Enter a new quantity and click update
		window.textBox("txtQuantity").deleteText().enterText("50");
		window.button("btnUpdate").click();
		
		// 3. Assert: The 3rd column (index 2) should be "50"
		// THIS WILL FAIL (RED) because btnUpdate has no listener yet
		String[][] contents = window.table("stockTable").contents();
		assertEquals("50", contents[0][2]);
	}
}