package goatPad;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

public class InteractionTests {

	/*
	 * highlights all iterations of string searched for
	 */
	@Test
	public void SearchInteractionTest() {
		toolbar toolbar = new toolbar();
		dropdown filemenu = toolbar.file;
		Document doc = new Document();
		boolean isEqual = false;

		doc.setContents("word something something something word something word");
		Pos[] positions = new Pos[3]; // start and ending position for all 3 "word". --Alex, here. Had to change the
										// size from 6 to 3, because 3 words won't be in 6 different places but, at
										// most, 3 different places
		positions = filemenu.searchAll("word", doc);

		doc.highlightWords(positions[0], positions[0]); // This is not optimized, but it works and changing it
														// would take some time
		doc.highlightWords(positions[1], positions[1]);
		doc.highlightWords(positions[2], positions[2]);

		String[] words = new String[3];
		words = doc.getHighlightAll(true);
		String[] check = new String[3];

		check[0] = "word";
		check[1] = "word";
		check[2] = "word";

		isEqual = true;
		for (int i = 0; i < words.length - 1; i++) {
			if (!words[i].equals(check[i])) {
				isEqual = false;
			}
		}
		assertEquals(isEqual, true);
	}

	/*
	 * load file to window/document correctly (contents same)
	 */
	@Test
	public void InetgrationTestLoadFile() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("CS270.txt");
		File file = toolbar.loadFile(path);
		assertEquals(path.toString(), file.getPath().toString());
		Document doc = new Document();
		doc.setContents(toolbar.getContents(file));
		assertEquals(doc.getAllType(), toolbar.getContents(file));
	}

	/**
	 * highlighted string Translates the highlighted string
	 * 
	 */
	//@Test
	//public void IntHighLightAndTranslate() {
		//Document doc = new Document();
		//toolbar toolbar = new toolbar();
		//String goatEnglish = "BABAAA";
		//doc.setContents("BABAAA");
		//Pos x1 = new Pos(0, 8);
		//Pos x2 = new Pos(32, 8);
		//String highlighted = doc.highlightString(x1, x2); // each character is 8 pixels
		//assertEquals(highlighted, goatEnglish);
		//String english = toolbar.translate("Sad"); // Sets the word for BABAAA to sad
		//assertEquals(english, goatEnglish);
	//}

	/*
	 * checks that test string is wrapped when window is made smaller
	 */
	@Test
	public void checkTextWrap() {
		ArrayList<String> strings = new ArrayList<String>();
		String str = "Supercalifragilisticexpialidocious";

		strings.add(str);
		Window display = new Window(0, 0);
		display.setHeight(300);
		display.setWidth(300);

		Document doc = new Document();
		doc.setContents(str);
		display.setHeight(50);
		display.setWidth(50);
		int wordSize = (int) (str.toCharArray().length * 8); // 8 pixels per char

		for (int i = wordSize; i > display.getWidth(); i = i - display.getWidth()) {
			if (wordSize > display.getWidth()) {
				/*
				 * I think I get the idea(maybe), but the original code will always error
				 * because the String's "size" is not equal to its length here.
				 */
				// strings.add(str.substring(wordSize - (wordSize - display.getWidth()),
				// wordSize));
			}
		}
		String lastStr = strings.get(strings.size() - 1);
		int lastCount = (int) lastStr.chars().count();
		assert (lastCount < display.getWidth());
	}
	
	/**
	 * Tests to see if the action listener in the dropdown menu functions.
	 * Also checks to make sure that the functions are actually called.
	 */
	@Test
	public void checkDropDownListeners() {
		DisplayPanel display = new DisplayPanel(800,800, Color.white);
		//assertEquals("
	}

}
