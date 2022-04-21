package finalPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputListener;
import javax.swing.text.BadLocationException;

//Consider making hotkeys for undo and redo
//Joe Comment

public class DisplayPanel extends JFrame implements MouseInputListener, KeyListener, DocumentListener, ActionListener {

	Document doc = new Document();
	toolbar toolbar = new toolbar();
	Clipboard clipboard = getToolkit().getSystemClipboard();
	JTextArea textArea;
	JFrame window;
	JScrollPane scrollPane;
	JMenuBar menuBar;
	JMenu menuFile, menuEdit, menuTranslate;
	JMenuItem iTranslateToEnglish, iTranslateToGoat, iCut, iCopy, iPaste, iImport, iExport, iPrint, iOpen,iSave,iUndo,iRedo;


	JPanel statusBar = new JPanel();
	JLabel status = new JLabel();

	dropdown drop = new dropdown();

	boolean wordWrapOn = true;
	int currentLine, currentCol;

	public DisplayPanel(int width, int height, Color c) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(c);
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createEditMenu();
		createTranslateMenu();		

		textArea.getDocument().addDocumentListener(this);
		textArea.setBounds(2, 40, width, height);

		drop.addActionListener(this);
		

		// Creates a status bar which has line and column number of the current caret
		// location
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(4, 4, 4, 4)));
		statusBar.setPreferredSize(new Dimension(width, 40));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		statusBar.add(status);
		statusBar.setBackground(Color.LIGHT_GRAY);
		status.setText("Line:  Col: ");
		this.wordWrapOnOff(wordWrapOn);

		//window.add(drop);

		window.setVisible(true);

		// Creates a listener that listens to movement by the caret
		textArea.addCaretListener(new CaretListener() {

			// Updates the line and column number of the caret
			public void caretUpdate(CaretEvent e) {
				int lineNum = 1;
				int columnNum = 1;

				try {
					int caretPos = textArea.getCaretPosition();
					lineNum = textArea.getLineOfOffset(caretPos);
					columnNum = caretPos - textArea.getLineStartOffset(lineNum);
					lineNum += 1;

				} catch (BadLocationException e1) {
					System.err.println("Invalid Location!");
				}
				updateLineAndCol(lineNum, columnNum);
			}
		});
	}

	protected void paintComponent(Graphics g) {

	}

	public dropdown getDropDown() {
		return drop;
	}
	public void createTextArea() {
		textArea = new JTextArea();
		window.add(textArea);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
	}
	public void createWindow() {
		window = new JFrame("LLGSHH-PAD");
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void createMenuBar() {
		menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);
		menuTranslate = new JMenu("Translate");
		menuBar.add(menuTranslate);
	}
	public void createFileMenu() {
		iImport = new JMenuItem("Import");
		iImport.addActionListener(this);
		iImport.setActionCommand("Import");
		menuFile.add(iImport);	
		
		iExport = new JMenuItem("Export");
		iExport.addActionListener(this);
		iExport.setActionCommand("Export");
		menuFile.add(iExport);	
		
		iPrint = new JMenuItem("Print");
		iPrint.addActionListener(this);
		iPrint.setActionCommand("Print");
		menuFile.add(iPrint);
		
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		menuFile.add(iOpen);
		
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		menuFile.add(iSave);

	}
	public void createEditMenu() {
		iCut = new JMenuItem("Cut");
		iCut.addActionListener(this);
		iCut.setActionCommand("Cut");
		menuEdit.add(iCut);
		
		iCopy = new JMenuItem("Copy");
		iCopy.addActionListener(this);
		iCopy.setActionCommand("Copy");
		menuEdit.add(iCopy);	
		
		iPaste = new JMenuItem("Paste");
		iPaste.addActionListener(this);
		iPaste.setActionCommand("Paste");
		menuEdit.add(iPaste);
		
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		menuEdit.add(iUndo);
		
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		menuEdit.add(iRedo);
	}
	public void createTranslateMenu() {
		iTranslateToEnglish = new JMenuItem("Translate to English");
		iTranslateToEnglish.addActionListener(this);
		iTranslateToEnglish.setActionCommand("Translate to English");
		menuTranslate.add(iTranslateToEnglish);	
		
		iTranslateToGoat = new JMenuItem("Translate to Goat");
		iTranslateToGoat.addActionListener(this);
		iTranslateToGoat.setActionCommand("Translate to Goat");
		menuTranslate.add(iTranslateToGoat);	
	}

	// Calls functions to do the indicated features
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		//FILE OPTIONS
		case "Import": System.out.println("Import Clicked"); break;
		case "Export": System.out.println("Export Clicked"); break;
		case "Print": System.out.println("Print Clicked"); break;
		case "Open": textArea.append(toolbar.openFile(textArea)); System.out.println("Open Clicked"); break;
		case "Save": toolbar.saveFile(textArea); System.out.println("Save Clicked"); break;
		// EDIT OPTIONS
		case "Cut": textArea.cut(); System.out.println("Cut Clicked"); break;
		case "Copy": textArea.copy(); System.out.println("Copy Clicked"); break;
		case "Paste": textArea.paste(); System.out.println("Paste Clicked"); break;
		case "Undo":toolbar.undo(doc); textArea.setText(doc.content); System.out.println("Undo Clicked"); break;
		case "Redo":toolbar.redo(doc);textArea.setText(doc.content); System.out.println("Redo Clicked"); break;
		// TRANSLATE OPTIONS
		case "Translate to English":String text = textArea.getText(); textArea.setText(""); textArea.append(doc.translateTextToEnglish(text)); System.out.println("Translate to English Clicked"); break;
		case "Translate to Goat": String text1 = textArea.getText(); textArea.setText(""); textArea.append(doc.translateTextToGoat(text1)); System.out.println("Translate to Goat Clicked"); break;

		
		}
		
		if (e.getSource() == menuBar) {
			if (drop.getSelectedItem()== "Import") {
				System.out.println(drop.getSelectedItem());
			}
			if (drop.getSelectedItem() == "Export") {
				System.out.println(drop.getSelectedItem());
			}
			if (drop.getSelectedItem() == "Print") {
				System.out.println(drop.getSelectedItem());
			}
			if (drop.getSelectedItem() == "Cut") {
				System.out.println(drop.getSelectedItem());
				textArea.cut();
			}
			if (drop.getSelectedItem() == "Copy") {
				System.out.println(drop.getSelectedItem());
				textArea.copy();
		}
		if (drop.getSelectedItem() == "Paste") {
			System.out.println(drop.getSelectedItem());
			textArea.paste();
		}
		if (drop.getSelectedItem() == "Open") {
			System.out.println(drop.getSelectedItem());
			textArea.append(toolbar.openFile(textArea));
		}
		if (drop.getSelectedItem() == "Save") {
			System.out.println(drop.getSelectedItem());
			toolbar.saveFile(textArea);
		}
		if (drop.getSelectedItem() == "Translate To English") {
			System.out.println(drop.getSelectedItem());
			String text = textArea.getText();
			textArea.setText("");
			textArea.append(doc.translateTextToEnglish(text));
		}
		if (drop.getSelectedItem() == "Translate To Goat") {
			System.out.println(drop.getSelectedItem());
			String text = textArea.getText();
			textArea.setText("");
			textArea.append(doc.translateTextToGoat(text));
		}
		if (drop.getSelectedItem() == "Redo") {
			System.out.println(drop.getSelectedItem());
			toolbar.redo(doc);
			textArea.setText(doc.content);
		}
		if (drop.getSelectedItem() == "Undo") {
			System.out.println(drop.getSelectedItem());
			toolbar.undo(doc);
			textArea.setText(doc.content);
		}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.inputManager(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// Updates the content of the document each time the text was added to the
		// textArea
		doc.clear();
		doc.setContents(textArea.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		/*
		 * Updates the content of the document each time the text is deleted from the
		 * textArea
		 */
		doc.clear();
		doc.setContents(textArea.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// Updates the content of the document each time there was a change to the
		// textArea
		doc.clear();
		doc.setContents(textArea.getText());
	}

	/**
	 * Handles click events
	 * 
	 * @param input
	 */
	public void inputManager(MouseEvent input) {
	}

	/**
	 * Turns on the word wrap if it is currently off and turns word wrap off if it
	 * is currently on in the textArea
	 * 
	 * @param isOn
	 */
	public void wordWrapOnOff(boolean isOn) {
		if (wordWrapOn == true) {
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			wordWrapOn = !wordWrapOn;
		} else {
			textArea.setLineWrap(false);
			textArea.setWrapStyleWord(false);
			wordWrapOn = !wordWrapOn;
		}
	}

	/**
	 * Updates the line and column number each time the cursor is moved to a
	 * different location and prints the numbers to the status bar
	 * 
	 * @param lineNum
	 * @param columnNum
	 */
	private void updateLineAndCol(int lineNum, int columnNum) {
		status.setText("Line: " + lineNum + " Column: " + columnNum);
		currentLine = lineNum;
		currentCol = columnNum;
	}

	/**
	 * Searches for a given string and returns the position of the string in the
	 * document
	 * 
	 * @param str
	 * @return
	 */
	public Pos search(String str) {

		int startIndex = textArea.getText().indexOf(str);
		if (startIndex == -1) {
			System.out.println("The given string is not found!");
			return null;
		}

		else {
			int endCaretPos = startIndex + str.length();
			Pos p = new Pos(startIndex + 1, endCaretPos);
			return p;
		}
	}

	/**
	 * Searches for a given string, when found
	 * 
	 * @param s
	 * @param str
	 */
	public void searchAndReplace(String s, String str) {
		int startIndex = textArea.getText().indexOf(s);
		if (startIndex == -1) {
			System.out.println("The given string is not found!");
		} else {
			int endCaretPos = startIndex + s.length();
			textArea.select(startIndex, endCaretPos);
			textArea.replaceSelection(str);
		}
	}

}