package com.stock.management;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class StockView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate; 
	private JTable stockTable;
	private JLabel lblErrorMessage;
	
	// Added controller field to satisfy tests
	private StockController controller;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			try {
				StockView frame = new StockView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// Added setter for the controller
	public void setController(StockController controller) {
		this.controller = controller;
	}

	public JTable getStockTable() {
		return stockTable;
	}

	public void showAllStock(List<StockItem> items) {
		DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
		model.setRowCount(0);
		for (StockItem item : items) {
			model.addRow(new Object[] { 
					item.getName(), 
					String.valueOf(item.getPrice()), 
					String.valueOf(item.getQuantity()) 
			});
		}	
	}

	public StockView() {
		setTitle("Stock Manager Pro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600); 

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 100, 200 };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// --- Name ---
		gbc.gridx = 0; gbc.gridy = 0;
		contentPane.add(new JLabel("Product Name:"), gbc);
		txtName = new JTextField();
		txtName.setName("txtName");
		gbc.gridx = 1;
		contentPane.add(txtName, gbc);

		// --- Price ---
		gbc.gridx = 0; gbc.gridy = 1;
		contentPane.add(new JLabel("Price ($):"), gbc);
		txtPrice = new JTextField();
		txtPrice.setName("txtPrice");
		gbc.gridx = 1;
		contentPane.add(txtPrice, gbc);

		// --- Quantity ---
		gbc.gridx = 0; gbc.gridy = 2;
		contentPane.add(new JLabel("Quantity:"), gbc);
		txtQuantity = new JTextField();
		txtQuantity.setName("txtQuantity");
		gbc.gridx = 1;
		contentPane.add(txtQuantity, gbc);

		// --- Add Button ---
		btnAdd = new JButton("Add Item");
		btnAdd.setName("btnAdd");
		btnAdd.setEnabled(false);
		btnAdd.setBackground(new Color(70, 130, 180));
		btnAdd.setForeground(Color.BLACK);
		gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
		contentPane.add(btnAdd, gbc);

		// --- Table ---
		stockTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Product", "Price", "Qty" }));
		stockTable.setName("stockTable");
		stockTable.setRowHeight(25);
		
		stockTable.getSelectionModel().addListSelectionListener(e -> {
			boolean rowSelected = stockTable.getSelectedRow() != -1;
			btnDelete.setEnabled(rowSelected);
			btnUpdate.setEnabled(rowSelected);
		});

		JScrollPane scrollPane = new JScrollPane(stockTable);
		gbc.gridy = 4; gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(scrollPane, gbc);

		// --- Update Button ---
		btnUpdate = new JButton("Update Selected Quantity");
		btnUpdate.setName("btnUpdate");
		btnUpdate.setEnabled(false); 
		gbc.gridy = 5; gbc.weighty = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(btnUpdate, gbc);

		// --- Delete Button ---
		btnDelete = new JButton("Delete Selected");
		btnDelete.setName("btnDelete");
		btnDelete.setEnabled(false); 
		btnDelete.setForeground(new Color(178, 34, 34));
		gbc.gridy = 6;
		contentPane.add(btnDelete, gbc);

		// --- Error Message ---
		lblErrorMessage = new JLabel(" ");
		lblErrorMessage.setName("errorMessageLabel");
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setFont(new Font("SansSerif", Font.ITALIC, 11));
		gbc.gridy = 7;
		contentPane.add(lblErrorMessage, gbc);

		// --- Update Action Listener ---
		
		btnUpdate.addActionListener(e -> {
			int selectedRow = stockTable.getSelectedRow();
				try{
					String name = (String) stockTable.getValueAt(selectedRow, 0);
					double price = Double.parseDouble(stockTable.getValueAt(selectedRow, 1).toString());
					int newQty = Integer.parseInt(txtQuantity.getText());
					controller.updateStockItem(new StockItem(name, 0, price), newQty);
					}catch (NumberFormatException ex) {
						lblErrorMessage.setText("Invalid quantity");
						}
				
		});

		// --- Key Listeners ---
		KeyAdapter btnAddEnabler = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnAdd.setEnabled(
					!txtName.getText().trim().isEmpty() && 
					!txtQuantity.getText().trim().isEmpty() && 
					!txtPrice.getText().trim().isEmpty()
				);
			}
		};

		txtName.addKeyListener(btnAddEnabler);
		txtQuantity.addKeyListener(btnAddEnabler);
		txtPrice.addKeyListener(btnAddEnabler);
	}
}