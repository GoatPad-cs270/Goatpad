package goatPad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class DisplayPanel extends JPanel implements MouseInputListener, KeyListener, DocumentListener {

	Document doc = new Document();
	toolbar toolbar = new toolbar();

	JTextArea textArea = new JTextArea();

	JButton undoButton = new JButton("Undo");
	JButton redoButton = new JButton("Redo");
	JButton wordWrap = new JButton("Word Wrap On/Off");

	JPanel statusBar = new JPanel();
	JLabel status = new JLabel();

	boolean wordWrapOn = false;

	public DisplayPanel(int width, int height, Color c) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(c);

		// Creates a new text area with the same width and height as the display
		textArea.getDocument().addDocumentListener(this);
		textArea.setBounds(2, 40, width, height);

		undoButton.setBounds(5, 5, 100, 30);
		redoButton.setBounds(110, 5, 100, 30);
		wordWrap.setBounds(215, 5, 150, 30);

		undoButton.addMouseListener(this);
		redoButton.addMouseListener(this);
		wordWrap.addMouseListener(this);

		// Creates a status bar which has line and column number of the current caret
		// location
		statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(4, 4, 4, 4)));
		statusBar.setPreferredSize(new Dimension(width, 40));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		statusBar.add(status);
		statusBar.setBackground(Color.LIGHT_GRAY);
		status.setText("Line:  Col: ");

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
		}

		/*
		 * If the wordWrap button is pressed and wordWrapOn variable was true, it will
		 * turn word wrap function on, and off otherwise. Each time the wordWrap button
		 * is pressed the wordWrapOn variable will change from true to false and back to
		 * true. This will make the word Wrap button to turn on and off the
		 * wordWrapping.
		 * 
		 */
		if (wordWrap.contains(input.getPoint())) {
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
	}
}
