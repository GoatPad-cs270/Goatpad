package goatPad;

//
import java.util.ArrayList;

public class Document {

	/**
	 * Keeps track of all the user inputs.
	 */
	ArrayList<String> inputs = new ArrayList<String>();
	String currentInput;

	/**
	 * Contains all the content (words, spaces, etc.) of the Document as a String
	 */
	String content = "";

	String copiedContent;
	toolbar toolbar = new toolbar();

	/**
	 * 
	 */
	public Document() {

	}

	/**
	 * returns highlighted string
	 * 
	 * @return
	 */
	public String getHighlight() {
		return content;
	}

	/**
	 * tracks last input to undo/re-do
	 * 
	 * @param file
	 * @return
	 */
	public String lastInput() {
		return inputs.get(inputs.size() - 1);
	}

	/**
	 * returns highlighted strings in an array
	 * 
	 * @return
	 */
	public String[] getHighlightAll() {
		return null;
	}

	public String[] getHighlightAll(boolean posInDropdown) {
		String[] strings = new String[3];
		strings[0] = "word";
		strings[1] = "word";
		strings[2] = "word";

		return strings;
	}

	/**
	 * clears document
	 */
	public void clear() {
		this.content = "";
	}

	/**
	 * returns all words in document
	 * 
	 * @return
	 */
	public String getAllType() {
		return content;
	}

	/**
	 * adds content to the document
	 * 
	 * @param string
	 */
	public void setContents(String string) {
		if (inputs.isEmpty() == true) {
			currentInput = string;
			inputs.add(string);
		} else {
			for (int i = 0; i < string.length(); i++) {
				if (currentInput.length() == i) {
					inputs.add(string.substring(i));
					currentInput += string.substring(i);
				}
			}
		}
		content += string;
	}

	/**
	 * highlights string between given coordinates (Pos)
	 * 
	 * @param x
	 * @param y
	 */
	public void highlightWords(Pos x, Pos y) {

	}

	public void Copypaste(String str) {
		this.copiedContent = str;
		content += str;
	}

	/**
	 * highlights and returns string between given coordinates (Pos)
	 * 
	 * @param x
	 * @param y
	 */
	public String highlightString(Pos x, Pos y) {
		// This is interesting: The x has an (x,y) and the y has an (x,y)
		return "BABAAA";
	}

	/**
	 * Sets document contents from a different documents
	 * 
	 * @param contents
	 */
	public void setContents(Document contents) {
		this.content = contents.content;

	}

	/**
	 * Directly translates the passed in content to Goat English.
	 * 
	 * @param content
	 * @return
	 */
	public String translateTextToGoat(String content) {
		return toolbar.translateToGoat(content);
	}

	/**
	 * Directly translates the passed in content to Human English.
	 * 
	 * @param content
	 * @return
	 */
	public String translateTextToEnglish(String content) {
		return toolbar.translateToEnglish(content);
	}

	/**
	 * Translates the content of the Document from Standard English to Goat English.
	 * Has a return type of void.
	 */
	public void translateContentToGoat() {
		this.content = toolbar.translateToGoat(this.content);

	}

	/**
	 * Translates the content of the Document from Goat English to Standard English.
	 */
	public void translateContentToEnglish() {
		this.content = toolbar.translateToEnglish(this.content);
	}

}
