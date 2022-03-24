package goatPad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class frameTester extends JFrame {
	
	frameTester() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		dropdown fileMenu = new dropdown();
		
		this.add(fileMenu);
		
		this.pack();
		this.setVisible(true);
	}

}
