package edu.uga.miage.m1.polygons.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

/**
 *  @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class GUIHelper {
	
	public static void showOnFrame(String frameName) {
		JFrame frame = new JDrawingFrame(frameName);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(wa);
		frame.pack();
		frame.setVisible(true);
	}

	public static String generateImportMenu(Component parent){
		//popup to select file to import
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	/**
	 * Generates popup menu to select export format
	 */
	public static String generateQuestionMenu(String[] choices, String title,String message){
		//popup a dialog to ask if we export in json or xml
		return (String) JOptionPane.showInputDialog(
				null,
				message,
				title,
				JOptionPane.QUESTION_MESSAGE,
				null,
				choices, // Array of choices
				choices[0] // Initial choice
		);
	}

}