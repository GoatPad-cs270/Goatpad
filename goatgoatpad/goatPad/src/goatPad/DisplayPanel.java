package goatPad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;



public class DisplayPanel extends JPanel implements MouseInputListener, KeyListener{
	private boolean control = false;
	private boolean pushinP = false;
	
	//print window class
	public DisplayPanel(int width, int height, Color c) {
		//attempt to make ctrl p a command to open this window up
		if(control == true && pushinP == true) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(c);
		}
	}

	protected void paintComponent(Graphics g) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) control = true;
		if (e.getKeyCode() == KeyEvent.VK_P) pushinP = true;
		
	}

}
