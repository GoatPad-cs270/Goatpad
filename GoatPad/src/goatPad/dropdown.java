package goatPad;

import javax.swing.JComboBox;

public class dropdown extends JComboBox {

	// This is meant to test the names of the dropdown menu
	public String names;

	public dropdown() {
		this.addItem("Translate");
		this.addItem("Import");
		this.addItem("Export");
		this.addItem("Print");
		this.addItem("Save");
	}

	/**
	 * returns an array of positions for a searched string
	 * 
	 * @param str
	 * @return
	 */
	public Pos[] searchAll(String str, Document doc) {
		Pos[] positions = new Pos[3];
		int pIndex = 0;

		// Loops through the documents content until it finds the searched for word,
		// then sets a random location because we can't really find it in the document.
		for (String string : doc.inputs) {
			if (string.equals(str)) {
				positions[pIndex] = new Pos((int) (Math.random() * 40 - 0) + 40, (int) (Math.random() * 40 - 0) + 40);
				pIndex++;
			}
		}
		return positions;
	}

	public Pos[] searchAll(String str) {
		return null;
	}
}
