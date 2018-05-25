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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	private JLabel labelCap;
	private JLabel capital;
	private JLabel inv;

	final JFileChooser fc = new JFileChooser();

	private String[] columnNames = { "Item", "Quantity", "Cost ($)", "Price ($)", "Re-order Point", "Re-order Amount",
			"Temp " + "\u2103".toCharArray()[0] };

	Store myStore;

	private Object[][] data;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUI(Store store) throws HeadlessException {
		super(store.getStoreName());
		myStore = store;
	}

	private void createGUI() throws IOException {

		data = myStore.createGuiData();

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

		repaint();
		this.setVisible(true);
	}

	private void refreshGUI() {

		capital.setText(Double.toString(myStore.getCapital()));
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

		storeName = new JLabel(myStore.getStoreName());
		labelCap = new JLabel("Capital: $");
		capital = new JLabel(Double.toString(myStore.getCapital()));
		inv = new JLabel("Inventory");

		storeName.setFont(new Font("Arial", Font.PLAIN, 20));
		storeName.setForeground(Color.white);

		labelCap.setFont(new Font("Arial", Font.PLAIN, 20));
		labelCap.setForeground(Color.WHITE);

		capital.setFont(new Font("Arial", Font.PLAIN, 20));
		capital.setForeground(Color.GREEN);

		inv.setFont(new Font("Arial", Font.PLAIN, 16));
		inv.setForeground(Color.white);

		addToPanel(pnlHeader, storeName, constraints, 0, 0, 2, 2);
		addToPanel(pnlHeader, labelCap, constraints, 2, 0, 2, 2);
		addToPanel(pnlHeader, capital, constraints, 4, 0, 2, 2);
		addToPanel(pnlHeader, inv, constraints, 0, 4, 2, 2);

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

		// Consider the alternatives - not all active at once.
		if (src == btnOrder) {
			fc.showSaveDialog(pnlTable);
			try {
				Manifest.createManifest(myStore, fc.getSelectedFile().getAbsolutePath());

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// Button for loading sales logs
		else if (src == btnSales) {
			fc.showOpenDialog(pnlTable);
			try {
				Manifest.loadSalesLog(myStore, fc.getSelectedFile().getAbsolutePath());
				refreshGUI();
				myStore.printInventory();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		// Button for loading manifests
		else if (src == btnReceive) {
			fc.showOpenDialog(pnlTable);
			try {
				Manifest.receiveManifest(myStore, fc.getSelectedFile().getAbsolutePath());
				refreshGUI();
				myStore.printInventory();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	// JFrame.setDefaultLookAndFeelDecorated(true);
	// SwingUtilities.invokeLater(new GUI("My Store Planner"));
	// }

}
