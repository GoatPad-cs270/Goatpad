package bruh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class testGoatPad {

	/*
	 * Translate Import Export Print Save
	 */

	@Test //green
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

	@Test //green
	public void checkSearchNothing() {
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		Document doc = new Document();
		String answer = doc.getHighlight();
		Pos search = filemenu.search(answer);
		assertEquals(search, null);
	}

	@Test //green
	public void checkSearchSomething() {
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		Document doc = new Document();
		String str = "searched string";
		doc.setContents(str);
		String answer = doc.getHighlight();
		Pos search = filemenu.search(answer);
		assertEquals(search, filemenu.search(str));
	}

	@Test //red
	public void checkCopyPaste() {
		Document doc = new Document();
		String myString = doc.getHighlight();
		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		assert (myString.equals(stringSelection));
	}

	@Test //green
	public void checkLoadFileWrongPath() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-not-exist.txt");
		File file = toolbar.loadFile(path);
		assertEquals(file, null);
	}

	@Test //red
	public void checkLoadFileCorrectPath() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-exist.txt");
		File file = toolbar.loadFile(path);
		assertEquals(path, file.getPath());
	}

	@Test //red
	public void checkTranslate() {
		toolbar toolbar = new toolbar();
		String goatEnglish = "BAA";
		String englishEnglish = toolbar.translate(goatEnglish);
		assert (englishEnglish.equals("Hello"));
	}

	@Test //red
	public void checkHighlight() {
		Document doc = new Document();
		String words = "highlighted words";
		String highlighted = doc.getHighlight();
		assert (highlighted.equals(words));
	}

	@Test //red
	public void checkUndo() {
		toolbar toolbar = new toolbar();
		Document doc = new Document();
		doc.setContents("words that exist in the document");
		Action lastAction = doc.lastInput();
		toolbar.undo(lastAction);
		assert (doc.getAllType().equals(""));
	}

	@Test //red
	public void checkSetContents() {
		Document doc = new Document();
		doc.setContents("Joseph Le is Poggers");
		assert (doc.getAllType().equals("Joseph Le is Poggers"));
	}

	@Test //fail
	public void checkSetHeight() {
		Window display = new Window(0, 0);
		display.setHeight(100);
		int height = display.getHeight();
		assertEquals(height, 100);
	}

	@Test //fail
	public void checkSetWidth() {
		Window display = new Window(0, 0);
		display.setWidth(100);
		int width = display.getWidth();
		assertEquals(width, 100);
	}

	@Test //red
	public void printTest() throws Exception {
		OutputStream os = new ByteArrayOutputStream();
		System.setOut((PrintStream) os);
		String items = "word";
		String actualOutput = os.toString();
		Object expectedOutput = items;
		assertEquals(expectedOutput, actualOutput);
	}

	public void CheckminimizeWindow() {
		Window display = new Window(0, 0);
		display.setHeight(0);
		int height = display.getHeight();
		display.setWidth(0);
		int width = display.getWidth();
		assertEquals(height, 0);
		assertEquals(width, 0);
	}

	@Test //red
	public void checkClear() {
		Document doc = new Document();
		doc.clear();
		String words = doc.getAllType();
		assert (words.equals(""));
	}

	@Test //red
	public void checkgetAllType() {
		Document doc = new Document();
		String str = "all of these words are in the document and are very cool";
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
}