package bruh;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class dropdown extends JComboBox {

	// This is meant to test the names of the dropdown menu
	public ArrayList<String> names;
	
	public dropdown() {
		names = new ArrayList<String>();
		this.addItem("Translate");
		this.addItem("Import");
		this.addItem("Export");
		this.addItem("Print");
		this.addItem("Save");
		

	}

	/**
	 * searches content of document for inputted word returns position
	 * 
	 * @param str
	 * @return
	 */
	public Pos search(String str) {
		return null;
	}

	/**
	 * returns an array of positions for a searched string
	 * 
	 * @param str
	 * @return
	 */
	public Pos[] searchAll(String str) {
		return null;
	}
}
