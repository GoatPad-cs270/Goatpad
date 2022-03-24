package goatPad;

import java.awt.Color;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		DisplayPanel display = new DisplayPanel(800, 800, Color.white);
		JFrame frame = new JFrame("Hello World Practice!");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(display);
		frame.pack();
		frame.setVisible(true);

	}

}