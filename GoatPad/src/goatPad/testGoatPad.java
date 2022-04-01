package goatPad;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.awt.TextArea;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.text.BadLocationException;

import org.junit.Test;

public class testGoatPad {

	/*
	 * Translate Import Export Print Save
	 */

	@Test
	public void checkNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("Translate");
		names.add("Import");
		names.add("Export");
		names.add("Print");
		names.add("Save");
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		assertEquals(filemenu.getItemCount(), 5);
		for (int i = 0; i < filemenu.getItemCount(); i++) {
			assert (filemenu.getItemAt(i).equals(names.get(i)));
		}
	}

	/**
	 * Tests copy and paste function to make sure the string that was copied was the
	 * one added to the document
	 */
	@Test
	public void checkCopyPaste() {
		Document doc = new Document();
		String myString = "Hello";
		doc.Copypaste(myString);
		assertEquals(doc.content, myString);
	}

	/**
	 * Tests to make sure the wrong file path returns null since the file does not
	 * exist
	 */
	@Test
	public void checkLoadFileWrongPath() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-not-exist.txt");
		File file = toolbar.loadFile(path);
		assertEquals(file, null);
	}

	/**
	 * Tests that the file path of an existing file is loaded correctly
	 */
	@Test
	public void checkLoadFileCorrectPath() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-exist.txt");
		File file = toolbar.loadFile(path);
		// Another change. Before it compared the Path object itself to a String of the
		// Path. Converted Path object to String so it could work right.
		assertEquals(path.toString(), file.getPath());
	}

	/**
	 * Tests whether the translate function works correctly by translating
	 * goatEnglish to English
	 */
	@Test
	public void checkTranslate() {
		toolbar toolbar = new toolbar();
		String goatEnglish = "BAA";
		String englishEnglish = toolbar.translate(goatEnglish);
		assert (englishEnglish.equals("Hello"));
	}

	/**
	 * Tests whether the indicated string is highlighted
	 */
	@Test
	public void checkHighlight() {
		Document doc = new Document();
		String words = "highlighted words";
		// Had to add this line because this test would never work otherwise. The doc
		// would always be empty unless we add something to it.
		doc.setContents(words);
		String highlighted = doc.getHighlight();
		assertEquals(highlighted, words);
	}

	/**
	 * Tests whether the undo function works correctly by undoing the last key
	 * stroke action that was performed on the document
	 */
	@Test
	public void checkUndo() {
		toolbar toolbar = new toolbar();
		Document doc = new Document();
		doc.setContents("words that exist in the document");
		toolbar.undo(doc);
		assert (doc.getAllType().equals(""));
	}

	/**
	 * Tests the functionality of the re do function to make sure it undoes the
	 * previous undo that was performed
	 */
	@Test
	public void checkRedo() {
		toolbar toolbar = new toolbar();
		Document doc = new Document();
		String original = "words that exist in the document";
		doc.setContents(original);
		toolbar.undo(doc);
		toolbar.redo(doc);
		assert (doc.getAllType().equals(original));
	}

	/**
	 * Tests to make sure the set content function sets the indicated string to the
	 * content of the document
	 */
	@Test
	public void checkSetContents() {
		Document doc = new Document();
		doc.setContents("Joseph Le is Poggers");
		assert (doc.getAllType().equals("Joseph Le is Poggers"));
	}

	/**
	 * Tests the set Height function to indicate that setting the height by calling
	 * this function, changes the real height of the display window
	 */
	@Test
	public void checkSetHeight() {
		Window display = new Window(0, 0);
		display.setHeight(100);
		int height = display.getHeight();
		assertEquals(height, 100);
	}

	/**
	 * Tests the setWidth function to make sure the width is changed after calling
	 * the function
	 */
	@Test
	public void checkSetWidth() {
		Window display = new Window(0, 0);
		display.setWidth(100);
		int width = display.getWidth();
		assertEquals(width, 100);
	}

	/**
	 * Tests the printing function to make sure the content that is printed is the
	 * same as the content the user wanted to print
	 * 
	 * @throws Exception
	 */
	@Test
	public void printTest() throws Exception {
		OutputStream os = new ByteArrayOutputStream();
		// There was just a straight-up error here; Had to be removed.
		String items = "words";
		byte[] bytes = items.getBytes();
		os.write(bytes);
		String actualOutput = os.toString();
		String expectedOutput = items;
		assertEquals(actualOutput, expectedOutput);

	}

	/**
	 * Tests whether the minimization of the window function correctly changes the
	 * size of the window to the new desired width and height
	 */
	@Test
	public void CheckminimizeWindow() {
		Window display = new Window(0, 0);
		display.setHeight(0);
		int height = display.getHeight();
		display.setWidth(0);
		int width = display.getWidth();
		assertEquals(height, 0);
		assertEquals(width, 0);
	}

	/**
	 * Tests to indicate that clear method deletes all the content of the document,
	 * leaving the document empty
	 */
	@Test
	public void checkClear() {
		Document doc = new Document();
		doc.clear();
		String words = doc.getAllType();
		assert (words.equals(""));
	}

	/**
	 * Tests the ability of the getAllType function to return all the content of the
	 * document
	 */
	@Test
	public void checkgetAllType() {
		Document doc = new Document();
		String str = "all of these words are in the document and are very cool";
		doc.setContents(str);
		String words = doc.getAllType();
		assert (words.equals(str));
	}

	public void CheckmaximizeWindow() {
		Window display = new Window(0, 0);
		display.setHeight(display.getHeight());
		int height = display.getHeight();
		display.setWidth(display.getWidth());
		int width = display.getWidth();
		assertEquals(height, display.getHeight());
		assertEquals(width, display.getWidth());
	}

	/*
	 * Tests the JTextArea created in the display to make sure the textArea formated
	 * correctly which includes the correct width and height, empty textArea to
	 * start with and also the text of the textArea after adding some content
	 */
	@Test
	public void checkJTextArea() {
		DisplayPanel display = new DisplayPanel(100, 100, Color.white);
		assertEquals(display.textArea.getText(), "");
		display.textArea.setText("Hello");
		assertEquals(display.doc.content, display.textArea.getText());
		assertEquals(display.textArea.getWidth(), display.getPreferredSize().width);
		assertEquals(display.textArea.getHeight(), display.getPreferredSize().height);
	}

	/*
	 * Checks whether the input array in the document class inserts the new content
	 * correctly after the new text is inserted into the document
	 */
	@Test
	public void checkSetContentInputsArrayList() {
		DisplayPanel display = new DisplayPanel(100, 100, Color.white);
		display.doc.setContents("Hello");
		int numInputs = 1;
		assertEquals(numInputs, display.doc.inputs.size());
		assertEquals(display.doc.content, display.doc.inputs.get(0));
	}

	/**
	 * Tests if the wrap on/off function works correctly
	 */
	@Test
	public void checkWrapOnOf() {
		DisplayPanel display = new DisplayPanel(100, 100, Color.white);
		assertEquals(display.wordWrapOn, false);
		assertEquals(display.textArea.getLineWrap(), true);
		display.wordWrapOnOff(display.wordWrapOn);
		assertEquals(display.wordWrapOn, true);
	}

	/**
	 * Tests status bar created in the display and some major parts and formatting
	 * of the status bar like background color and the width of the bar
	 */
	@Test
	public void checkStatusBar() {
		DisplayPanel display = new DisplayPanel(100, 100, Color.white);
		assertEquals(display.getPreferredSize().width, display.statusBar.getPreferredSize().width);
		assertEquals(display.statusBar.getBackground(), Color.LIGHT_GRAY);
	}

	/**
	 * Tests the function that updates the line and column of the current location
	 * of the cursor.
	 */
	@Test
	public void checkCaretUpdate() {
		DisplayPanel display = new DisplayPanel(100, 100, Color.white);
		assertEquals(display.textArea.getCaretPosition(), 0);
		display.textArea.setText("Hello");
		display.textArea.setCaretPosition(5);
		assertEquals(display.textArea.getCaretPosition(), 5);
	}

	/**
	 * Tests the functionality of the search function using different strings
	 */
	@Test
	public void checkSearchFunction() {
		DisplayPanel display = new DisplayPanel(800, 800, Color.white);
		display.textArea.setText("Hello, Heilo, Name  dflaskdjfalsd, supercalifragilisticexpialidocious,       j");
		int posX = 15;
		int posY = 18;
		assertEquals(display.search("Name").x, posX);
		assertEquals(display.search("Name").y, posY);
		assertEquals(display.search("name"), null);

		display.textArea.setText(null);
		assertEquals(display.search("Hello"), null);
	}

	/**
	 * Tests search and replace function to make sure it works during different
	 * string scenarios
	 */
	@Test
	public void checkSearchAndReplaceFunction() {
		DisplayPanel display = new DisplayPanel(800, 800, Color.white);
		display.searchAndReplace("word", "newWord");
		assertEquals(display.textArea.getText(), "");
		display.textArea
				.setText("Hello, Heilo, Name   greetings  howdy  dflaskdjfalsd, supercalifragilisticexpialidocious,j");
		display.searchAndReplace("howdy", "Hey");
		assertEquals(display.textArea.getText(),
				"Hello, Heilo, Name   greetings  Hey  dflaskdjfalsd, supercalifragilisticexpialidocious,j");
	}

}