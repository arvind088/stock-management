package com.stock.management;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

class StockViewTest {
	
	private FrameFixture window;
	
	@BeforeEach
	void setUp() {
		JFrame frame = GuiActionRunner.execute(StockView::new);
		window = new FrameFixture(frame);
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
		// The robot looks for a component with name txtName
		window.textBox("txtName").requireVisible();
		}
	@Test
	void testPriceTextFieldShouldBePresent() {
		// This will fail because txtQuantity doesn't exist yet
		window.textBox("txtPrice").requireVisible();
	    }
	@Test
	void testQuantityTextFieldShouldBePresent() {
		// This will fail because txtQuantity doesn't exist yet
		window.textBox("txtQuantity").requireVisible();
	    }
	@Test
	void testAddButtonShouldBePresentAndDisabled() {
		// This will fail because 'btnAdd' doesn't exist yet
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
		// Fill all fields to enable button
		window.textBox("txtName").enterText("Laptop");
		window.textBox("txtQuantity").enterText("10");
		window.textBox("txtPrice").enterText("500");
		
		// Clear one field
		window.textBox("txtName").deleteText();
		
		// Verify it is disabled again
		window.button("btnAdd").requireDisabled();
		}
	@Test
	void testStockTableShouldBePresent() {
		// for JTable named 'stockTable'
		window.table("stockTable").requireVisible();
		}
	@Test
	void testDeleteButtonShouldBePresentAndDisabled() {
		// for the  btnDelete
		window.button("btnDelete").requireVisible();
		window.button("btnDelete").requireDisabled();
		}
}

