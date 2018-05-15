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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * @author Brant Geeves
 *
 */
public class GUI extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	// Main GUI Panels
	private JPanel pnlHeader;
	private JPanel pnlTable;
	private JPanel pnlBtn;

	// Popup GUI Panels
	// private JPanel pnlAddItems;

	private JButton btnAdd;
	private JButton btnOrder;
	private JButton btnReceive;
	private JButton btnSales;

	private JScrollPane tableDisplay;

	// char c = "\u2103".toCharArray()[0];
	private String[] columnNames = { "Item", "Cost ($)", "Price ($)", "Re-order Point", "Re-order Amount",
			"Temp " + "\u2103".toCharArray()[0] };

	private Object[][] data = { { "Rice", 5, 10, 5, 5, 999 }, { "Beans", 2, 4, 5, 5, 999 },
			{ "Biscuits", 3, 6, 5, 5, 999 }, { "Pasta", 4, 8, 5, 5, 999 }, { "Nuts", 1, 2, 5, 5, 999 } };

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUI(String arg0) throws HeadlessException {
		super(arg0);
	}

	private void createGUI() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		pnlHeader = createPanel(Color.DARK_GRAY);
		pnlTable = createPanel(Color.WHITE);
		pnlBtn = createPanel(Color.DARK_GRAY);

		btnAdd = createButton("Add Items from file");
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

		JLabel storeName = new JLabel("My Store Name");
		JLabel storeFunds = new JLabel("Funds: $10000");
		JLabel inv = new JLabel("Inventory");

		storeName.setFont(new Font("Arial", Font.PLAIN, 20));
		storeName.setForeground(Color.white);

		storeFunds.setFont(new Font("Arial", Font.PLAIN, 20));
		storeFunds.setForeground(Color.white);

		inv.setFont(new Font("Arial", Font.PLAIN, 16));
		inv.setForeground(Color.white);

		addToPanel(pnlHeader, storeName, constraints, 0, 0, 2, 2);
		addToPanel(pnlHeader, storeFunds, constraints, 2, 0, 2, 2);
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

		addToPanel(pnlBtn, btnAdd, constraints, 0, 0, 2, 2);
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
		createGUI();
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
		if (src == btnAdd) {
			JOptionPane.showMessageDialog(this, "A new pane will popup here to find item list CSV file",
					"Add Items to inventory", JOptionPane.WARNING_MESSAGE);
		} else if (src == btnOrder) {
			JOptionPane.showMessageDialog(this,
					"This button will generate a new manifest based on current stock levels and respective reorder points",
					"Generate manifest", JOptionPane.WARNING_MESSAGE);
		} else if (src == btnSales) {
			JOptionPane.showMessageDialog(this, "A new pane will popup to find sales list file ", "Upload sales list",
					JOptionPane.WARNING_MESSAGE);
		} else if (src == btnReceive) {
			JOptionPane.showMessageDialog(this, "A new pane will popup to find manifest file to upload ",
					"Upload sales list", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new GUI("My Store Planner"));
	}

}
