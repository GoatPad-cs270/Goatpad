package goatPad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputListener;

public class DisplayPanel extends JPanel implements MouseInputListener, KeyListener, DocumentListener {

	Document doc = new Document();
	toolbar toolbar = new toolbar();
	JTextArea textArea = new JTextArea();
	JButton undoButton = new JButton("Undo");
	JButton redoButton = new JButton("Redo");
	JButton wordWrap = new JButton("Word Wrap On/Off");
	boolean wordWrapOn = false;

	public DisplayPanel(int width, int height, Color c) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(c);

		textArea.getDocument().addDocumentListener(this);
		textArea.setBounds(2, 40, width, height);

		undoButton.setBounds(5, 5, 100, 30);
		redoButton.setBounds(110, 5, 100, 30);
		wordWrap.setBounds(215, 5, 150, 30);

		undoButton.addMouseListener(this);
		redoButton.addMouseListener(this);
		wordWrap.addMouseListener(this);
	}

	protected void paintComponent(Graphics g) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (undoButton.contains(e.getPoint())) {
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
		if (wordWrap.contains(e.getPoint())) {
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
		// Updates the content of the document to the text that was typed on the display
		doc.clear();
		doc.setContents(textArea.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		doc.clear();
		doc.setContents(textArea.getText());
		System.out.println(doc.content);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		doc.clear();
		doc.setContents(textArea.getText());
		System.out.println(doc.content);
	}

}
