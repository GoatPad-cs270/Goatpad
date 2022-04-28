package finalPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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

	/**
	 * Allows for the setting up of the printing component/UI
	 */
	PrinterJob printer;

	// Menu bar containing menus (e.g. File, Edit, etc.)
	JMenuBar menuBar = new JMenuBar();

	// Menus contained within the menu bar
	JMenu fileMenu = new JMenu();
	JMenu editMenu = new JMenu();
	JMenu formatMenu = new JMenu();
	JMenu viewMenu = new JMenu();
	JMenu translateMenu = new JMenu();

	// Menu items contained within the menus

	// File menu items
	JMenuItem newItem = new JMenuItem("New");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem importItem = new JMenuItem("Import");
	JMenuItem export = new JMenuItem("Export");
	JMenuItem print = new JMenuItem("Print");
	JMenuItem save = new JMenuItem("Save");

	// Edit menu items
	JMenuItem undo = new JMenuItem("Undo");
	JMenuItem redo = new JMenuItem("Redo");
	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");

	// Format menu items
	JMenuItem goTranslate = new JMenuItem("Translate to Goat");
	JMenuItem enTranslate = new JMenuItem("Translate to English");
	JMenuItem wordWrap = new JMenuItem("Word Wrap");

	// View menu items
	JMenuItem zoomIn = new JMenuItem("Zoom In");
	JMenuItem zoomOut = new JMenuItem("Zoom Out");

	Document doc = new Document();
	toolbar toolbar = new toolbar();
	Clipboard clipboard = getToolkit().getSystemClipboard();
	JTextArea textArea = new JTextArea();

	static final int DEFAULT_FONT_SIZE = 12;
	static final Font DEFAULT_FONT = new Font("Arial", DEFAULT_FONT_SIZE, DEFAULT_FONT_SIZE);

	int fontSize = DEFAULT_FONT_SIZE;

	JPanel statusBar = new JPanel();
	JLabel status = new JLabel();

	JFrame window, searchWindow;
	JScrollPane scrollPane;

	boolean wordWrapOn = true;
	int currentLine, currentCol;

	public DisplayPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));

		createWindow(width, height);
		createTextArea();

		textArea.getDocument().addDocumentListener(this);

		// Creates a status bar which has line and column number of the current caret
		// location
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(4, 4, 4, 4)));
		statusBar.setPreferredSize(new Dimension(width, 40));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		statusBar.add(status);
		statusBar.setBackground(Color.LIGHT_GRAY);
		status.setText("Line:  Col: ");
		this.wordWrapOnOff(wordWrapOn);


		window.setJMenuBar(menuBar);
    
		// Instantiation of different Menus
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		formatMenu = new JMenu("Format");
		viewMenu = new JMenu("View");

		// Testing
		// Addition of Menus to Menu Bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		menuBar.add(viewMenu);

		// Assignment of Menu items to appropriate Menus

		// FileMenu
		fileMenu.add(newItem);
		fileMenu.add(save);
		fileMenu.add(open);
		fileMenu.add(print);

		newItem.addActionListener(this);
		newItem.setActionCommand("New");

		save.addActionListener(this);
		save.setActionCommand("Save");

		open.addActionListener(this);
		open.setActionCommand("Open");

		export.addActionListener(this);
		export.setActionCommand("Export");

		importItem.addActionListener(this);
		importItem.setActionCommand("Import");

		print.addActionListener(this);
		print.setActionCommand("Print");

		// EditMenu
		editMenu.add(undo);
		editMenu.add(redo);
		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);

		undo.addActionListener(this);
		undo.setActionCommand("Undo");

		redo.addActionListener(this);
		redo.setActionCommand("Redo");

		cut.addActionListener(this);
		cut.setActionCommand("Cut");

		copy.addActionListener(this);
		copy.setActionCommand("Copy");

		paste.addActionListener(this);
		paste.setActionCommand("Paste");

		// FormatMenu
		formatMenu.add(goTranslate);
		formatMenu.add(enTranslate);
		formatMenu.add(wordWrap);

		goTranslate.addActionListener(this);
		goTranslate.setActionCommand("Translate to Goat");

		enTranslate.addActionListener(this);
		enTranslate.setActionCommand("Translate to English");

		wordWrap.addActionListener(this);
		enTranslate.setActionCommand("Word Wrap");

		// ViewMenu
		viewMenu.add(zoomIn);
		viewMenu.add(zoomOut);

		zoomIn.addActionListener(this);
		zoomIn.setActionCommand("Zoom In");

		zoomOut.addActionListener(this);
		zoomOut.setActionCommand("Zoom Out");

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

	public void createTextArea() {
		textArea = new JTextArea();
		window.add(textArea);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
	}

	public void createWindow(int width, int height) {
		window = new JFrame("LLGSHH-PAD");
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// Calls functions to do the indicated features
	@Override
	public void actionPerformed(ActionEvent e) {
		// @formatter:off
		switch (e.getActionCommand()) {
			case "New":
				doc.content = "";
				textArea.setText(doc.content);
				break;
			case "Save":
				toolbar.saveFile(textArea);
				break;
			case "Open":
				toolbar.openFile(textArea);
				break;
			case "Export":
				// Export function
				break;
			case "Import":
				// Import function
				break;
			case "Print":
				print();
				break;
			case "Undo":
				toolbar.undo(doc);
				textArea.setText(doc.content);
				break;
			case "Redo":
				toolbar.redo(doc);
				textArea.setText(doc.content);
				break;
			case "Cut":
				textArea.cut();
				break;
			case "Copy":
				textArea.copy();
				break;
			case "Paste":
				textArea.paste();
				break;
			case "Translate to Goat":
				translateToGoat();
				break;
			case "Translate to English":
				translateToEnglish();
				break;
			case "Zoom In":
				zoomIn();
				break;
			case "Zoom Out":
				zoomOut();
				break;
			case "Word Wrap":
				wordWrapOnOff(wordWrapOn);
				break;
			}
		// @formatter:on

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

	public void print() {
		printer = PrinterJob.getPrinterJob();
		printer.setJobName(" Print Component ");

		printer.setPrintable(new Printable() {
			public int print(Graphics pg, PageFormat pf, int pageNum) {
				if (pageNum > 0)
					return Printable.NO_SUCH_PAGE;

				Graphics2D g = (Graphics2D) pg;
				g.translate(pf.getImageableX(), pf.getImageableY());
				paintComponent(g);
				return Printable.PAGE_EXISTS;
			}
		});

		if (printer.printDialog() == false)
			return;

		try {
			printer.print(); // This out to work
		} catch (PrinterException ex) {
			System.err.println("Printing problem!");
		}
	}

	public void translateToGoat() {
		String text = textArea.getText();
		textArea.setText("");
		textArea.append(doc.translateTextToGoat(text));
	}

	public void translateToEnglish() {
		String text = textArea.getText();
		textArea.setText("");
		textArea.append(doc.translateTextToEnglish(text));
	}

	public void zoomIn() {
		fontSize += 2;
		textArea.setFont(new Font("Arial", fontSize, fontSize));
	}

	public void zoomOut() {
		if (fontSize > 0) {
			fontSize -= 2;
			textArea.setFont(new Font("Arial", fontSize, fontSize));
		}
	}
}