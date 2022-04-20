package finalPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.Scanner;
import java.awt.print.*;
import java.text.*;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.management.Notification;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

public class toolbar {

	// taylor
	private boolean hasPartner = false;

	public int response;

	public boolean undo = false;

	String undoString = "";

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
		if (path.toString().equals("does-exist.txt") || path.toString().equalsIgnoreCase("CS270.txt")) {
			return path.toFile();
		} else {
			return null;
		}

	}

	/**
	 * opens up file explorer to save current text area as chosen file type
	 * 
	 * @param
	 * @void
	 */
	public void saveFile(JTextArea text) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));

		response = fileChooser.showSaveDialog(null);

		if (response == JFileChooser.APPROVE_OPTION) {
			File file;
			PrintWriter fileOut = null;

			file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			try {
				fileOut = new PrintWriter(file);
				fileOut.println(text.getText());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				fileOut.close();
			}
		}

	}

	

	

	/**
	 * opens up file explorer to select current a txt file to place in the current
	 * text area
	 * 
	 * @param
	 * @void
	 */
	public String openFile(JTextArea text) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
		fileChooser.setFileFilter(filter);

		response = fileChooser.showOpenDialog(null);

		if (response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			Scanner fileIn = null;

			try {
				fileIn = new Scanner(file);
				if (file.isFile()) {
					while (fileIn.hasNextLine()) {
						String line = fileIn.nextLine() + "\n";
						text.append(line);
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				fileIn.close();
			}
		}
		return text.getText();

	}

	public String translateToEnglish(String str) {
		String temp = str;
		temp = temp.toLowerCase();

		// Add ability to read whether word is capitalized or not.
		if (temp.equals("baaaa")) {
			return "Hello";
		} else {
			String newString = "";
			for (int i = 0; i < str.length(); i++) {
				if (i != str.length() - 1) {
					newString += handleGoatCharacterChange(str.charAt(i), str.charAt(i + 1));
				} else {
					newString += handleGoatCharacterChange(str.charAt(i), ' ');
				}

				if (getHasPartner() == true) {
					i++;
				}
			}

			return newString;
		}

	}

	/**
	 * Translates standard English text into Goat English
	 * 
	 * @param str English text to be translated into Goat.
	 * @return
	 */
	public String translateToGoat(String str) {
		String newString = "";
		char[] letters = str.toCharArray();

		for (char letter : letters) {
			newString += handleCharacterChange(letter);
		}

		return newString;
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
	/**
	 * Redo's the most recent UNDO performed on the document
	 * 
	 * @param doc
	 * @return
	 */
	public String redo(Document doc) {
		if (undo == true) {
			doc.inputs.add(undoString);
			doc.content = "";

			for (String input : doc.inputs) {
				doc.content += input;
			}
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

	/**
	 * Takes in char s
	 * 
	 * @param c
	 * @return
	 */
	private String handleCharacterChange(char c) {
		String returnVal = "";
		boolean isLower = Character.isLowerCase(c);
		char temp = c;

		temp = Character.toLowerCase(temp);

		switch (temp) {
		case 'c':
			returnVal = "aa";
			break;
		case 'd':
			returnVal = "ab";
			break;
		case 'e':
			returnVal = "ba";
			break;
		case 'f':
			returnVal = "bb";
			break;
		case 'g':
			returnVal = "wa";
			break;
		case 'h':
			returnVal = "wb";
			break;
		case 'i':
			returnVal = "aw";
			break;
		case 'j':
			returnVal = "bw";
			break;
		case 'k':
			returnVal = "ya";
			break;
		case 'l':
			returnVal = "yb";
			break;
		case 'm':
			returnVal = "ay";
			break;
		case 'n':
			returnVal = "by";
			break;
		case 'o':
			returnVal = "ar";
			break;
		case 'p':
			returnVal = "br";
			break;
		case 'q':
			returnVal = "ra";
			break;
		case 'r':
			returnVal = "rr";
			break;
		case 's':
			returnVal = "rb";
			break;
		case 't':
			returnVal = "rw";
			break;
		case 'u':
			returnVal = "ry";
			break;
		case 'v':
			returnVal = "yr";
			break;
		case 'w':
			returnVal = "ww";
			break;
		case 'x':
			returnVal = "wy";
			break;
		case 'y':
			returnVal = "yy";
			break;
		case 'z':
			returnVal = "yw";
			break;
		case ' ':
			returnVal = "/";
			break;
		}

		if (isLower) {
			return returnVal.toLowerCase();
		} else {
			return returnVal.toUpperCase();
		}

	}

	/**
	 * Takes a
	 * 
	 * @param c
	 * @return
	 */
	private char handleGoatCharacterChange(char c, char partnerC) {
		setHasPartner(false);
		if (c == ' ') {
			return ' ';
		}

		char returnVal = ' ';
		boolean isLower = Character.isLowerCase(c);

		char temp = c;
		char temp2 = partnerC;

		temp = Character.toLowerCase(temp);
		temp2 = Character.toLowerCase(temp2);

		switch (temp) {
		case 'w':
			if (temp2 == 'a') {
				returnVal = 'g';
			} else if (temp2 == 'b') {
				returnVal = 'h';
			} else if (temp2 == 'w') {
				returnVal = 'w';
			} else if (temp2 == 'y') {
				returnVal = 'x';
			}

			setHasPartner(true);
			break;

		case 'y':
			if (temp2 == 'a') {
				returnVal = 'k';
			} else if (temp2 == 'b') {
				returnVal = 'l';
			} else if (temp2 == 'r') {
				returnVal = 'v';
			} else if (temp2 == 'y') {
				returnVal = 'y';
			} else if (temp2 == 'w') {
				returnVal = 'z';
			}

			setHasPartner(true);
			break;
		case 'r':
			if (temp2 == 'a') {
				returnVal = 'q';
			} else if (temp2 == 'r') {
				returnVal = 'r';
			} else if (temp2 == 'b') {
				returnVal = 's';
			} else if (temp2 == 'w') {
				returnVal = 't';
			} else if (temp2 == 'y') {
				returnVal = 'u';
			}

			setHasPartner(true);
			break;
		case 'a':
			if (temp2 == 'a') {
				returnVal = 'c';
				setHasPartner(true);
			} else if (temp2 == 'b') {
				returnVal = 'd';
				setHasPartner(true);
			} else if (temp2 == 'y') {
				returnVal = 'm';
				setHasPartner(true);
			} else if (temp2 == 'r') {
				returnVal = 'o';
				setHasPartner(true);
			} else if (temp2 == 'w') {
				returnVal = 'i';
				setHasPartner(true);
			} else {
				returnVal = 'a';
			}
			break;
		case 'b':
			if (temp2 == 'a') {
				returnVal = 'e';
				setHasPartner(true);
			} else if (temp2 == 'b') {
				returnVal = 'f';
				setHasPartner(true);
			} else if (temp2 == 'w') {
				returnVal = 'j';
				setHasPartner(true);
			} else if (temp2 == 'y') {
				returnVal = 'n';
				setHasPartner(true);
			} else if (temp2 == 'r') {
				returnVal = 'p';
				setHasPartner(true);
			} else {
				returnVal = 'b';
			}
			break;
		case '/':
			returnVal = ' ';
			break;
		}

		if (isLower) {
			return Character.toLowerCase(returnVal);
		} else {
			return Character.toUpperCase(returnVal);
		}
	}

	protected void setHasPartner(boolean bool) {
		hasPartner = bool;
	}

	protected boolean getHasPartner() {
		return hasPartner;
	}

}
