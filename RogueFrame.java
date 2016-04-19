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
	
	private int length = 40; private int height = 40; 
	private MapPanel map = new MapPanel(length, height, 18, 12, 420L);
	//private MapPanel map = new MapPanel(20, 20, 15, 15, 420L);
	//private MapPanel map = new MapPanel(100, 100, 40, 40, 420L);
	//private Map map = new Map(length, height, 35, 35, 420L);
	private InfoBar hpBar = new InfoBar("Health", 100, 50, 100);
	private InfoBar mpBar = new InfoBar("Mana", 100, 50, 100);
	
	/**
	 * RogueFrame constructor
	 */
	public RogueFrame() {
		super("Rogue Fr@me");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		//map.addKeyListener(this);
		
		map.setBackground(Color.BLACK);
		map.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		map.setView(5, 5);
		add(map, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new FlowLayout());
		
		hpBar.setBackground(Color.BLACK);
		hpBar.showDecrease(true);
		panel.add(hpBar);
		
		mpBar.setBackground(Color.BLACK);
		mpBar.showDecrease(true);
		mpBar.setColor(Color.BLUE);
		mpBar.setDecreaseColor(Color.WHITE);
		panel.add(mpBar);
		
		add(panel, BorderLayout.SOUTH);
		
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
				map.shiftView(0, -1);
				break;
			case KeyEvent.VK_LEFT:
				map.shiftView(-1, 0);
				break;
			case KeyEvent.VK_RIGHT:
				map.shiftView(1, 0);
				break;
			case KeyEvent.VK_DOWN:
				map.shiftView(0, 1);
				break;
			case KeyEvent.VK_Z:
				hpBar.shiftValue(-10);
				hpBar.repaint();
				mpBar.shiftValue(-10);
				mpBar.repaint();
				break;
			case KeyEvent.VK_X:
				hpBar.shiftValue(10);
				hpBar.repaint();
				mpBar.shiftValue(10);
				mpBar.repaint();
				break;
			case KeyEvent.VK_C:
				hpBar.setValue(0);
				hpBar.repaint();
				mpBar.setValue(0);
				mpBar.repaint();
				break;
			case KeyEvent.VK_SPACE:
				map.rounds(1);
			default:
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