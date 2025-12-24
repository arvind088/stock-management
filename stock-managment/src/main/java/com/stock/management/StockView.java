package com.stock.management;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StockView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JButton btnAdd;
	
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

	public StockView() {
		setTitle("Stock Management App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtName = new JTextField();
		txtName.setName("txtName");
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setName("txtPrice"); 
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setName("txtQuantity"); 
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setName("btnAdd");
		btnAdd.setEnabled(false); 
		contentPane.add(btnAdd);

		// FIX: Moved inside the constructor
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