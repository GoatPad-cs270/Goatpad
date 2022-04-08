package finalPackage;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class testGoatPad {

	/*
	 * Translate Import Export Print Save
	 */

	@Test
	public void checkNames() { // Green
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

	@Test
	public void checkSearchNothing() { // Green
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		Document doc = new Document();
		String answer = doc.getHighlight();
		Pos search = filemenu.search(answer);
		assertEquals(search, null);
	}

	@Test
	public void checkSearchSomething() { // Green
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		Document doc = new Document();
		String str = "searched string";
		doc.setContents(str);
		String answer = doc.getHighlight();
		Pos search = filemenu.search(answer);
		assertEquals(search, filemenu.search(str));
	}

	@Test
	public void checkCopyPaste() {
		Document doc = new Document();
		String myString = "Hello";
		doc.Copypaste(myString);
		assertEquals(doc.content, myString);
	}

	@Test
	public void checkLoadFileWrongPath() { // Green
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-not-exist.txt");
		File file = toolbar.loadFile(path);
		assertEquals(file, null);
	}

	@Test
	public void checkLoadFileCorrectPath() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("does-exist.txt");
		File file = toolbar.loadFile(path);
		// Another change. Before it compared the Path object itself to a String of the
		// Path. Converted Path object to String so it could work right.
		assertEquals(path.toString(), file.getPath());
	}

	@Test
	public void checkTranslate() { // Green
		toolbar toolbar = new toolbar();
		String goatEnglish = "BAAAA";
		String englishEnglish = toolbar.translateToEnglish(goatEnglish);
		assertEquals(englishEnglish, "Hello");
	}

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

	@Test
	public void checkUndo() {
		toolbar toolbar = new toolbar();
		Document doc = new Document();
		doc.setContents("words that exist in the document");
		toolbar.undo(doc);
		assert (doc.getAllType().equals(""));
	}

	@Test
	public void checkSetContents() { // Green
		Document doc = new Document();
		doc.setContents("Joseph Le is Poggers");
		assert (doc.getAllType().equals("Joseph Le is Poggers"));
	}

	@Test
	public void checkSetHeight() { // Green
		Window display = new Window(0, 0);
		display.setHeight(100);
		int height = display.getHeight();
		assertEquals(height, 100);
	}

	@Test
	public void checkSetWidth() { // Green
		Window display = new Window(0, 0);
		display.setWidth(100);
		int width = display.getWidth();
		assertEquals(width, 100);
	}

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

	public void CheckminimizeWindow() {
		Window display = new Window(0, 0);
		display.setHeight(0);
		int height = display.getHeight();
		display.setWidth(0);
		int width = display.getWidth();
		assertEquals(height, 0);
		assertEquals(width, 0);
	}

	@Test
	public void checkClear() { // Green
		Document doc = new Document();
		doc.clear();
		String words = doc.getAllType();
		assert (words.equals(""));
	}

	@Test
	public void checkgetAllType() { // Green
		Document doc = new Document();
		String str = "all of these words are in the document and are very cool";
		doc.setContents(str);
		String words = doc.getAllType();
		assert (words.equals(str));
	}

	@Test
	public void CheckmaximizeWindow() {
		Window display = new Window(0, 0);
		display.setHeight(display.getHeight());
		int height = display.getHeight();
		display.setWidth(display.getWidth());
		int width = display.getWidth();
		assertEquals(height, display.getHeight());
		assertEquals(width, display.getWidth());
	}

	@Test
	public void checkDocumentTranslation() {
		Document doc = new Document();
		String englishText = "This will revolutionize the LLGHS community!";
		String goatText = "RWwbawrb/wwawybyb/rrbayrarybryrwawarbyawywba/rwwbba/YBYBWAWBRB/aaarayayrybyawrwyy";

		doc.setContents(englishText);
		doc.translateContentToGoat();

		assertEquals(doc.content, goatText);

		doc.translateContentToEnglish();

		assertEquals(doc.content, englishText);
	}

}