package bruh;

import java.io.File;
import java.nio.file.Path;

public class toolbar {

	public dropdown file = new dropdown();

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
		return null;

	}

	/**
	 * translate goat english to english
	 * 
	 * @param str
	 * @return
	 */
	public String translate(String str) {
		return null;
	}

	/**
	 * undoes the previous single KEYSTROKE action
	 * 
	 * @param lastAction
	 * @return
	 */
	public String undo(Action lastAction) {
		return null;
	}

	/**
	 * gets content from file and returns it into document form
	 * 
	 * @param file
	 * @return
	 */
	public Document getContents(File file) {
		return null;

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