package assignment2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import GUI.introGUI;

public class Program {

	
	public static void main(String[] args) {
		
		
		Store myStore = new Store();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new introGUI(myStore));

	}

}
