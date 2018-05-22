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
	//private JButton btnAdd;
	private JButton btnOrder;
	private JButton btnReceive;
	private JButton btnSales;

	// Scroll Pane to hold table
	private JScrollPane tableDisplay;

	final JFileChooser fc = new JFileChooser();

	// char c = "\u2103".toCharArray()[0];
	private String[] columnNames = { "Item", "Quantity", "Cost ($)", "Price ($)", "Re-order Point", "Re-order Amount",
			"Temp " + "\u2103".toCharArray()[0] };

	// private Object[][] data = { { "Rice", 0, 5, 10, 5, 5, 999 }, { "Beans", 0, 2,
	// 4, 5, 5, 999 },
	// { "Biscuits", 0, 3, 6, 5, 5, 999 }, { "Pasta", 0, 4, 8, 5, 5, 999 }, {
	// "Nuts", 0, 1, 2, 5, 5, 999 } };

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

		
		//myStore.addInventory();
		data = myStore.createGuiData();

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		pnlHeader = createPanel(Color.DARK_GRAY);
		pnlTable = createPanel(Color.WHITE);
		pnlBtn = createPanel(Color.DARK_GRAY);

		//btnAdd = createButton("Add Items from file");
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

		JTable jta = new JTable(data, columnNames);
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

		JLabel storeName = new JLabel(myStore.getStoreName());
		JLabel storeCapital = new JLabel("Capital: $100000.00");
		JLabel inv = new JLabel("Inventory");

		storeName.setFont(new Font("Arial", Font.PLAIN, 20));
		storeName.setForeground(Color.white);

		storeCapital.setFont(new Font("Arial", Font.PLAIN, 20));
		storeCapital.setForeground(Color.white);

		inv.setFont(new Font("Arial", Font.PLAIN, 16));
		inv.setForeground(Color.white);

		addToPanel(pnlHeader, storeName, constraints, 0, 0, 2, 2);
		addToPanel(pnlHeader, storeCapital, constraints, 2, 0, 2, 2);
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

		//addToPanel(pnlBtn, btnAdd, constraints, 0, 0, 2, 2);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			JOptionPane.showMessageDialog(this,
					"This button will generate a new manifest based on current stock levels and respective reorder points",
					"Generate manifest", JOptionPane.WARNING_MESSAGE);
		} else if (src == btnSales) {
			fc.showOpenDialog(pnlTable);
		} else if (src == btnReceive) {
			fc.showOpenDialog(pnlTable);
		}
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
//		SwingUtilities.invokeLater(new GUI("My Store Planner"));
//	}

}
