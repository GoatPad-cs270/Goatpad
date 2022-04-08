package finalPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class DisplayPanel extends JFrame implements MouseInputListener, KeyListener, DocumentListener, ActionListener {

	Document doc = new Document();
	toolbar toolbar = new toolbar();

	JTextArea textArea = new JTextArea();

	JButton undoButton = new JButton("Undo");
	JButton redoButton = new JButton("Redo");

	JPanel statusBar = new JPanel();
	JLabel status = new JLabel();

	dropdown drop = new dropdown();

	boolean wordWrapOn = true;
	int currentLine, currentCol;

	public DisplayPanel(int width, int height, Color c) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(c);

		// Creates a new text area with the same width and height as the display
		textArea.getDocument().addDocumentListener(this);
		textArea.setBounds(2, 40, width, height);

		undoButton.setBounds(5, 5, 100, 30);
		redoButton.setBounds(110, 5, 100, 30);

		drop.addActionListener(this);

		undoButton.addMouseListener(this);
		redoButton.addMouseListener(this);

		// Creates a status bar which has line and column number of the current caret
		// location
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(4, 4, 4, 4)));
		statusBar.setPreferredSize(new Dimension(width, 40));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		statusBar.add(status);
		statusBar.setBackground(Color.LIGHT_GRAY);
		status.setText("Line:  Col: ");
		this.wordWrapOnOff(wordWrapOn);

		// Adds all panels to the main panel
		this.add(drop);
		this.add(textArea);
		this.add(undoButton);
		this.add(redoButton);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(width, height);
		this.setVisible(true);

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

	// Calls functions to do the indicated features
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drop) {
			if (drop.getSelectedItem() == "Import") {
				System.out.println(drop.getSelectedItem());
			}
			if (drop.getSelectedItem() == "Export") {
				System.out.println(drop.getSelectedItem());
			}
			if (drop.getSelectedItem() == "Print") {
				System.out.println(drop.getSelectedItem());
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
		if (undoButton.contains(input.getPoint())) {
			toolbar.undo(doc);
			textArea.setText(doc.content);
		} else if (redoButton.contains(input.getPoint())) {
			toolbar.redo(doc);
			textArea.setText(doc.content);
		}
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