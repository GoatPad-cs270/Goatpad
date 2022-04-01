package goatPad;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		DisplayPanel display = new DisplayPanel(800, 800, Color.white);
		JFrame frame = new JFrame("GoatPad");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding different components of the displayPanel to the frame including the
		// display itself
		frame.add(display.statusBar, BorderLayout.SOUTH);
		frame.add(display.undoButton);
		frame.add(display.redoButton);
		frame.add(display.textArea);
		frame.add(display);

		frame.pack();
		frame.setVisible(true);
	}
}
