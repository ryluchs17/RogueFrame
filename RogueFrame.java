/**
 * A little fake rogue-like in a JFrame!
 * @author Ryan Luchs
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * RogueFrame main class
 * @author Ryan Luchs
 *
 */
public class RogueFrame extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1;
	
	private int length = 18; private int height = 12; 
	private Map map = new Map(length,height);
	
	/**
	 * RogueFrame constructor
	 */
	public RogueFrame() {
		super("Rogue Fr@me");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		map.addKeyListener(this);
		add(map, BorderLayout.CENTER);
		
		add(new InfoBar("title", 0, 0, 32), BorderLayout.SOUTH);
		
		addKeyListener(this);
		
		setSize(getPreferredSize());
		pack();
		//setResizable(false);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	/**
	 * RogueFrame main method
	 * @param args
	 */
	public static void main(String[] args) {
		new RogueFrame();
	}
	
	public void replaceMap(String s) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < length; x++) {
				map.getTileAt(x, y).setChar(s);
			}
		}
		map.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				replaceMap("^");
				break;
			case KeyEvent.VK_LEFT:
				replaceMap("<");
				break;
			case KeyEvent.VK_RIGHT:
				replaceMap(">");
				break;
			case KeyEvent.VK_DOWN:
				replaceMap("v");
				break;
			default:
				replaceMap("?");
				break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}