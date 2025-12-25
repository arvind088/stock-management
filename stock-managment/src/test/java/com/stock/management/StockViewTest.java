package com.stock.management;

import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static org.assertj.core.api.Assertions.assertThat;

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
	@Test
	void testErrorMessageLabelShouldBePresentAndEmpty() {
		window.label("errorMessageLabel").requireText(" ");
	    }
	@Test
	void testDeleteButtonShouldBeEnabledOnlyWhenARowIsSelected() {
		// Manually add a row to the table model (we'll do this simply for now)
		JTable table = window.table("stockTable").target();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Column 1");
		model.addRow(new Object[] {"Item 1"});
		
		// Select the row
		window.table("stockTable").selectRows(0);
		window.button("btnDelete").requireEnabled();
		
		// Clear selection
		window.table("stockTable").unselectRows(0);
		window.button("btnDelete").requireDisabled();
		}
	@Test
	void testErrorMessageLabelShouldBePresentAndInitiallyEmpty() {
		// Change the name to something unique we haven't added yet
		window.label("errorMessageLabel").requireVisible();
		window.label("errorMessageLabel").requireText(" ");
		}
	@Test
	void testGetStockTableShouldReturnTheTableComponent() {
	    // Cast the target to StockView so you can call your custom methods
	    StockView view = (StockView) window.target(); 
	    
	    JTable table = view.getStockTable();
	    assertThat(table).isNotNull();
	    assertThat(table.getName()).isEqualTo("stockTable");
	}
}