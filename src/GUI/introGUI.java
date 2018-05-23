/**
 * 
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import assignment2.Store;

/**
 * @author Brant Geeves
 *
 */
public class introGUI extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 400;

	private JPanel pnlBtn;
	private JLabel labelName;
	private JTextArea textName;

	private JButton btnFind, btnCreate;

	final JFileChooser fc = new JFileChooser();

	Store myStore;

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public introGUI(Store store) throws HeadlessException {
		super(store.getStoreName());
		myStore = store;
	}

	private void createGUI() {

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		pnlBtn = createPanel(Color.DARK_GRAY);

		labelName = new JLabel("Enter the name of your store:");
		labelName.setForeground(Color.WHITE);

		btnFind = createButton("Add Items from file");
		btnCreate = createButton("Create new store");
		textName = createTextArea();

		layoutButtonPanel();

		this.getContentPane().add(pnlBtn, BorderLayout.CENTER);

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

	private JTextArea createTextArea() {
		JTextArea ta = new JTextArea();
		ta.setRows(1);
		return ta;
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

		addToPanel(pnlBtn, labelName, constraints, 0, 0, 2, 2);
		addToPanel(pnlBtn, textName, constraints, 0, 2, 2, 2);
		addToPanel(pnlBtn, btnFind, constraints, 0, 4, 2, 2);
		addToPanel(pnlBtn, btnCreate, constraints, 0, 6, 2, 2);

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
		if (src == btnFind) {
			fc.showOpenDialog(null);
			try {
				myStore.addInventory(fc.getSelectedFile().getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (src == btnCreate) {

			myStore.setStoreName(textName.getText());
			this.setVisible(false);
			SwingUtilities.invokeLater(new GUI(myStore));

		}
	}

}
