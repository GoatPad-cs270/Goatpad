package goatPad;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;

public class DropDownPanel extends JPanel implements ActionListener {
	
	private dropdown fileMenu;
	
	DropDownPanel() {
		this.setLayout(new FlowLayout());
		this.setSize(1000, 1000);
		
		fileMenu = new dropdown();
		fileMenu.addActionListener(this);
		this.setLocation(0, 0);
		
		this.add(fileMenu);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fileMenu) {
			if(fileMenu.getSelectedItem() == "Import") {
				System.out.println(fileMenu.getSelectedItem());
			}
			else if(fileMenu.getSelectedItem() == "Export") {
				System.out.println(fileMenu.getSelectedItem());
			}
			else if(fileMenu.getSelectedItem() == "Print") {
				System.out.println(fileMenu.getSelectedItem());
			}
			else if(fileMenu.getSelectedItem() == "Save") {
				System.out.println(fileMenu.getSelectedItem());
			}
			else {
				System.out.println(fileMenu.getSelectedItem());
			}
		}
	}
	
	public Object getItemAt(int index) {
		return fileMenu.getItemAt(index);
	}

}
