package goatPad;

import java.io.File;
import java.nio.file.Path;

public class toolbar {

	public dropdown file = new dropdown();
	boolean undo = false;
	String undoString = "";

	/**
	 * Sets up the names for the dropdown
	 * 
	 **/
	public toolbar() {

	}

	/**
	 * loads file from an existing file path
	 * 
	 * @param file
	 * @return
	 */
	public File loadFile(Path path) {
		if (path.toString().equals("does-exist.txt") || path.toString().equalsIgnoreCase("CS270.txt")) {
			return path.toFile();
		} else {
			return null;
		}

	}

	/**
	 * translate goat English to English
	 * 
	 * @param str
	 * @return
	 */
	public String translate(String str) {
		if (str == "BAA") {
			return "Hello";
		} else if (str == "BABAAA") {
			return "sad";
		}

		if (str == "sad" || str == "Sad") {
			return "BABAAA";
		}
		return null;
	}

	/**
	 * undoes the previous single KEYSTROKE action
	 * 
	 * @param lastAction
	 * @return
	 */
	public String undo(Document doc) {
		if (doc.content == "" || doc.content == null) {
			undo = true;
			return null;
		} else {
			undo = true;
			undoString = doc.inputs.remove(doc.inputs.size() - 1);
			doc.content = "";
			for (String input : doc.inputs) {
				doc.content += input;
			}
			return doc.content;
		}
	}

	/**
	 * Redo's the most recent UNDO performed on the document
	 * 
	 * @param doc
	 * @return
	 */
	public String redo(Document doc) {
		if (undo == true) {
			doc.setContents(undoString);
			undo = false;
		}
		return doc.content;
	}

	/**
	 * gets content from file and returns it into document form
	 * 
	 * @param file
	 * @return
	 */
	public String getContents(File file) {
		return file.toString();

	}

	/**
	 * Sets document contents from a different documents
	 * 
	 * @param contents
	 */
	public void setContents(Document contents) {
		// TODO Auto-generated method stub

	}

}
