package com.stock.management;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StockView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	
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
		
		// The minimum code to pass the failing test:
		txtName = new JTextField();
		txtName.setName("txtName");
		contentPane.add(txtName);
		txtName.setColumns(10);
		}
}