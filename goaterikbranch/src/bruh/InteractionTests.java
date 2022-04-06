package bruh;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		doc.setContents("word something something something word something word");
		Pos[] positions = new Pos[6]; // start and ending position for all 3 "word"
		positions = filemenu.searchAll("word");
		doc.highlightWords(positions[0], positions[1]);
		doc.highlightWords(positions[2], positions[3]);
		doc.highlightWords(positions[4], positions[5]);
		String[] words = new String[3];
		words = doc.getHighlightAll();
		String[] check = new String[3];
		check[0] = "word";
		check[1] = "word";
		check[2] = "word";
		assert (words.equals(check));
	}

	/*
	 * load file to window/document correctly (contents same)
	 */
	@Test
	public void InetgrationTestLoadFile() {
		toolbar toolbar = new toolbar();
		Path path = Paths.get("CS270.txt");
		File file = toolbar.loadFile(path);
		assertEquals(path, file.getPath());
		Document doc = new Document();
		doc.setContents(toolbar.getContents(file));
		assertEquals(doc.getAllType(), toolbar.getContents(file));
	}

	/**
	 * highlighted string Translates the highlighted string
	 * 
	 */
	@Test
	public void IntHighLightAndTranslate() {
		Document doc = new Document();
		toolbar toolbar = new toolbar();
		String goatEnglish = "BABAAA";
		doc.setContents("BABAAA");
		Pos x1 = new Pos(0, 8);
		Pos x2 = new Pos(32, 8);
		String highlighted = doc.highlightString(x1, x2); // each character is 8 pixels
		assert (highlighted.equals(goatEnglish));
		String english = toolbar.translate("Sad"); // Sets the word for BABAAA to sad
		assert (english.equals(goatEnglish));
	}

	/*
	 * checks that test string is wrapped when window is made smaller
	 */
	@Test
	public void checkTextWrap() {
		ArrayList<String> strings = new ArrayList<String>();
		String str = "Supercalifragilisticexpialidocious";
		int strCount = 1;
		strings.add(str);
		Window display = new Window(0, 0);
		display.setHeight(300);
		display.setWidth(300);
		Document doc = new Document();
		doc.setContents(str);
		display.setHeight(50);
		display.setWidth(50);
		int wordSize = (int) (str.chars().count() * 8); // 8 pixels per char
		for (int i = wordSize; i > display.getWidth(); i = i - display.getWidth()) {
			if (wordSize > display.getWidth()) {
				strings.add(str.substring(wordSize - (wordSize - display.getWidth()), wordSize));
				strCount++;
			}
		}
		String lastStr = strings.get(strCount - 1);
		int lastCount = (int) lastStr.chars().count();
		assert (lastCount < display.getWidth());
	}

}
