package goatPad;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class DriveFrame extends JFrame {
	
	
	DriveFrame() {
		DropDownPanel ddp = new DropDownPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(1920,1080);
		this.setVisible(true);
		
		this.add(ddp);
		
	}
	

}
