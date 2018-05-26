/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Exceptions.CSVFormatException;
import Exceptions.DeliveryException;
import Exceptions.StockException;
import assignment2.Manifest;
import assignment2.Store;

/**
 * @author Brant Geeves
 *
 */
public class GUI extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	// GUI Panels
	private JPanel pnlHeader;
	private JPanel pnlTable;
	private JPanel pnlBtn;

	// GUI Buttons
	// private JButton btnAdd;
	private JButton btnOrder;
	private JButton btnReceive;
	private JButton btnSales;

	// Scroll Pane to hold table
	private JScrollPane tableDisplay;

	// Table goes inside Scroll Pane
	private JTable jta;
	DefaultTableModel dm = new DefaultTableModel();

	private JLabel storeName;
	private JLabel capital;
	private JLabel inv;

	final JFileChooser fc = new JFileChooser();

	private String[] columnNames = { "Item", "Quantity", "Cost ($)", "Price ($)", "Re-order Point", "Re-order Amount",
			"Temp " + "\u2103".toCharArray()[0] };

	private Object[][] data;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUI() throws HeadlessException {
		super("Inventory Controller 5000");
		
		
	}

	private void createGUI() throws IOException {

		data = Store.getInstance().createGuiData();

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		pnlHeader = createPanel(Color.DARK_GRAY);
		pnlTable = createPanel(Color.WHITE);
		pnlBtn = createPanel(Color.DARK_GRAY);

		btnOrder = createButton("Generate manifest");
		btnReceive = createButton("Receive manifest");
		btnSales = createButton("Add Sales from file");

		tableDisplay = createTableArea();

		pnlTable.setLayout(new BorderLayout());
		pnlTable.add(tableDisplay, BorderLayout.CENTER);

		layoutHeaderPanel();
		layoutButtonPanel();

		this.getContentPane().add(pnlHeader, BorderLayout.NORTH);
		this.getContentPane().add(pnlTable, BorderLayout.CENTER);
		this.getContentPane().add(pnlBtn, BorderLayout.SOUTH);

		this.setLocationRelativeTo(null);
		repaint();
		this.setVisible(true);
	}

	private void refreshGUI() {

		capital.setText(String.format("Capital: $%.2f", Store.getInstance().getCapital()));
		checkCapital();
		dm.setDataVector(Store.getInstance().createGuiData(), columnNames);

	}

	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}

	private JButton createButton(String str) {
		JButton jb = new JButton(str);
		jb.addActionListener(this);
		return jb;
	}

	private JScrollPane createTableArea() {

		dm.setDataVector(data, columnNames);
		jta = new JTable(dm);
		jta.setFocusable(false);
		jta.setFillsViewportHeight(true);
		jta.setFont(new Font("Arial", Font.PLAIN, 12));

		JScrollPane scrl = new JScrollPane(jta);

		return scrl;
	}

	private void layoutHeaderPanel() {

		GridBagLayout layout = new GridBagLayout();
		pnlHeader.setLayout(layout);

		// add components to grid
		GridBagConstraints constraints = new GridBagConstraints();

		// Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 100;
		constraints.weighty = 100;

		storeName = new JLabel(Store.getInstance().getStoreName());
		capital = new JLabel(String.format("Capital: $%.2f", Store.getInstance().getCapital()));
		inv = new JLabel("Inventory");

		storeName.setFont(new Font("Arial", Font.PLAIN, 20));
		storeName.setForeground(Color.white);

		capital.setFont(new Font("Arial", Font.PLAIN, 20));
		capital.setForeground(Color.GREEN);

		inv.setFont(new Font("Arial", Font.PLAIN, 16));
		inv.setForeground(Color.white);

		addToPanel(pnlHeader, storeName, constraints, 0, 0, 2, 2);
		addToPanel(pnlHeader, capital, constraints, 4, 0, 2, 2);
		addToPanel(pnlHeader, inv, constraints, 0, 4, 2, 2);

	}

	private void checkCapital() {

		if (Store.getInstance().getCapital() >= 0) {
			capital.setForeground(Color.GREEN);
		} else {
			capital.setForeground(Color.RED);
		}

	}

	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		pnlBtn.setLayout(layout);

		// add components to grid
		GridBagConstraints constraints = new GridBagConstraints();

		// Defaults
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;

		addToPanel(pnlBtn, btnOrder, constraints, 2, 0, 2, 2);
		addToPanel(pnlBtn, btnReceive, constraints, 4, 0, 2, 2);
		addToPanel(pnlBtn, btnSales, constraints, 6, 0, 2, 2);

	}

	/**
	 * 
	 * A convenience method to add a component to given grid bag layout locations.
	 * Code due to Cay Horstmann
	 *
	 * @param c
	 *            the component to add
	 * @param constraints
	 *            the grid bag constraints to use
	 * @param x
	 *            the x grid position
	 * @param y
	 *            the y grid position
	 * @param w
	 *            the grid width
	 * @param h
	 *            the grid height
	 */
	private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		jp.add(c, constraints);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			createGUI();
		} catch (IOException e) {

			e.printStackTrace();
		}
		refreshGUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get event source
		Object src = e.getSource();

		// Button for creating manifests
		if (src == btnOrder) {
			fc.showSaveDialog(pnlTable);
			try {
				Manifest.createManifest(fc.getSelectedFile().getAbsolutePath());

			}	catch (StockException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: Creating Manifest", JOptionPane.WARNING_MESSAGE);
			}	catch (CSVFormatException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: CSV Format", JOptionPane.WARNING_MESSAGE);
			}	catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.",
						"Error: IOException", JOptionPane.WARNING_MESSAGE);
			}	catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(this, "In order to create a new Manifest, you must save the file.",
						"Error: Manifest file not saved", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		// Button for loading manifests
		else if (src == btnReceive) {
			fc.showOpenDialog(pnlTable);
			try {
				Manifest.receiveManifest(fc.getSelectedFile().getAbsolutePath());
				refreshGUI();
				// Store.getInstance().printInventory();
			} 	catch (CSVFormatException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: CSV Format", JOptionPane.WARNING_MESSAGE);
			} 	catch (DeliveryException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: Manifest Format", JOptionPane.WARNING_MESSAGE);
			}	catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.",
						"Error: IOException", JOptionPane.WARNING_MESSAGE);
			}	catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(this, "In order to receive a new delivery, you must upload a Manifest file.",
						"Error: Manifest file not found", JOptionPane.WARNING_MESSAGE);
			}
		}
		// Button for loading sales logs
		else if (src == btnSales) {
			fc.showOpenDialog(pnlTable);
			try {
				Manifest.loadSalesLog(fc.getSelectedFile().getAbsolutePath());
				refreshGUI();
				// Store.getInstance().printInventory();
			} 	catch (CSVFormatException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: CSV Format", JOptionPane.WARNING_MESSAGE);
			} 	catch (StockException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(),
						"Error: Stock", JOptionPane.WARNING_MESSAGE);
			} 	catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.",
						"Error: IOException", JOptionPane.WARNING_MESSAGE);
			}	catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(this, "In order to update sales data, you must upload a Sales Log file.",
						"Error: Sales Log not found", JOptionPane.WARNING_MESSAGE);
			}	
		}
	}


}
