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
	private JLabel[] tiles = new JLabel[length*height];
	private Map map = new Map(height,length);
	
	/**
	 * RogueFrame constructor
	 */
	public RogueFrame() {
		super("Rogue Fr@me");
		
		JPanel panel;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));
		setBackground(Color.BLACK);
		
		// Setup Grid
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		panel.setLayout(new GridLayout(height,length));
		for(int i = 0; i < length*height; i++) {
			tiles[i] =  map.getTileAt(i).label;
			panel.add(tiles[i]);
		}
		add(panel);
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				for(int i = 0; i < length; i++) {
					tiles[i].setText(" ^ ");
				}
				break;
			case KeyEvent.VK_LEFT:
				for(int i = 0; i < length; i++) {
					tiles[i].setText(" < ");
				}
				break;
			case KeyEvent.VK_RIGHT:
				for(int i = 0; i < length; i++) {
					tiles[i].setText(" > ");
				}
				break;
			case KeyEvent.VK_DOWN:
				for(int i = 0; i < length; i++) {
					tiles[i].setText(" v ");
				}
				break;
			default:
				for(int i = 0; i < length*height; i++) {
					tiles[i].setText(" ? ");
				}
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