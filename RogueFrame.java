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
public class RogueFrame extends JFrame implements KeyListener, ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	RoguePanel game;
	HelpFrame help;
	
	/**
	 * RogueFrame constructor
	 */
	public RogueFrame() {
		super("Rogue Fr@me");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		game = new RoguePanel();
		add(game);
		
		help = new HelpFrame();
		
		this.addKeyListener(this);
		
		JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenu subMenu;
        
        menuBar = new JMenuBar();
        //menuBar.setForeground(Color.WHITE);
        menuBar.setBackground(Color.BLACK);
        
        menu = new JMenu("Game");
        menu.setForeground(Color.WHITE);
        
        menuItem = new JMenuItem("New");
        menuItem.setActionCommand("new game");
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.BLACK);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
       // menu.addSeparator();
        
        menuItem = new JMenuItem("Exit");
        menuItem.setActionCommand("exit");
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.BLACK);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuBar.add(menu);
        
        menuBar.add( Box.createHorizontalGlue() );
        
        menu = new JMenu("Help");
        menu.setForeground(Color.WHITE);
        
        menuItem = new JMenuItem("About");
        menuItem.setActionCommand("help");
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.BLACK);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuBar.add(menu);
        
        setJMenuBar(menuBar);
		
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
		game.keyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case "new game":
				game.newGame();
				//JOptionPane.showMessageDialog(this, "This feature is currently unsupported. Sorry.");
				break;
			case "exit":
				System.exit(0);
				break;
			case "help":
				help.setVisible(true);
				break;
			default:
				break;
		}
		
	}
}