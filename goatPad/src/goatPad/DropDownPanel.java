package goatPad;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
/*
 * Creates a panel containing a dropdown menu of various functions  
 */
public class DropDownPanel extends JPanel implements ActionListener {
	
	private dropdown fileMenu;
	
	DropDownPanel() {
		this.setLayout(new FlowLayout());
		this.setSize(400, 400);
		
		fileMenu = new dropdown();
		fileMenu.addActionListener(this);
		
		this.add(fileMenu);
		
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fileMenu) {
			if(fileMenu.getSelectedItem() == "Import") {
				System.out.println(fileMenu.getSelectedItem());
			}
			if(fileMenu.getSelectedItem() == "Export") {
				System.out.println(fileMenu.getSelectedItem());
			}
			if(fileMenu.getSelectedItem() == "Print") {
				System.out.println(fileMenu.getSelectedItem());
			}
			if(fileMenu.getSelectedItem() == "Open") {
				System.out.println(fileMenu.getSelectedItem());
				//openFile();
			}
			if(fileMenu.getSelectedItem() == "Save") {
				System.out.println(fileMenu.getSelectedItem());
				//saveFile();
			}
			if(fileMenu.getSelectedItem() == "Translate"){
				System.out.println(fileMenu.getSelectedItem());
			}
		}
	}
	

}
