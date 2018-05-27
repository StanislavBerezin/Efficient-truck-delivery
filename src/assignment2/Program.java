package assignment2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import GUI.introGUI;


/**This class  is the entry point to the rest of the program.
 * It simply sets the look and feel to default and calls the Intro GUI
 * class in a thread-safe manner.
 * 
 * @author Brant Geeves 
 *
 */ 
public class Program {
	
	/**The entry point to the program.
	 * 
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new introGUI());

	}

}
