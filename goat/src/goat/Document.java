package goat;

public class Document {

	public Document() {

	}

	/**
	 * tracks last input to undo/redo
	 * 
	 * @param file
	 * @return
	 */
	public Action lastInput() {
		return null;
	}

	/**
	 * returns highlighted string
	 * 
	 * @return
	 */
	public String getHighlight() {
		return null;
	}

	/**
	 * returns highlighted strings in an array
	 * 
	 * @return
	 */
	public String[] getHighlightAll() {
		return null;
	}

	/**
	 * clears document
	 */
	public void clear() {

	}

	/**
	 * returns all words in document
	 * 
	 * @return
	 */
	public String getAllType() {
		return null;
	}

	/**
	 * adds content to the document
	 * 
	 * @param string
	 */
	public void setContents(String string) {

	}

	/**
	 * highlights string between given coordinates (Pos)
	 * 
	 * @param x
	 * @param y
	 */
	public void highlightWords(Pos x, Pos y) {

	}

	/**
	 * highlights and returns string between given coordinates (Pos)
	 * 
	 * @param x
	 * @param y
	 */
	public String highlightString(Pos x, Pos y) {
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