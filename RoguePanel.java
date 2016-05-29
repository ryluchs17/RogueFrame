/**
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import entity.*;
import tile.TileMap;
import item.*;

/**
 * @author ryan
 *
 */
public class RoguePanel extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 1;
	
	private int length = 40; private int height = 40; 
	//private MapPanel map = new MapPanel(length, height, 18, 12, 420L);
	//private MapPanel map = new MapPanel(20, 20, 15, 15, 420L);
	//private MapPanel map = new MapPanel(100, 100, 40, 40, 420L);
	private MapPanel map = new MapPanel(length, height, 35, 30);
	//private MapPanel map = new MapPanel(length, height, 35, 35);
	//private MapPanel map = new MapPanel(length, height, 35, 35, 42L);
	private InfoBar hpBar = new InfoBar("Health", 100, 50, 100);
	private InfoBar mpBar = new InfoBar("Mana", 100, 50, 100);
	
	private AbstractEntity player;
	
	private InventoryPanel ip;
	private StatusPanel sp;
	
	/**
	 * RogueFrame constructor
	 */
	public RoguePanel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		
		map.setBackground(Color.BLACK);
		map.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		map.setView(5, 5);
		add(map, BorderLayout.CENTER);
		
		player = new Player(0, 0, 20, map.getMap());
		player.randomTeleport();
		map.getMap().getEntities().add(0, player);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new FlowLayout());
		
//		hpBar.setBackground(Color.BLACK);
//		hpBar.showDecrease(true);
//		panel.add(hpBar);
//		
//		mpBar.setBackground(Color.BLACK);
//		mpBar.showDecrease(true);
//		mpBar.setColor(Color.BLUE);
//		mpBar.setDecreaseColor(Color.WHITE);
//		panel.add(mpBar);
		ip = new InventoryPanel(player, map);
		panel.add(ip);
		
		add(panel, BorderLayout.SOUTH);
		
		panel = new JPanel(new GridLayout(2, 1));
		sp = new StatusPanel(player);
		panel.add(sp);
		StatusPanel s = new StatusPanel(null);
		map.setStatusPanel(s);
		panel.add(s);
		add(panel , BorderLayout.WEST);
		
		setSize(getPreferredSize());
		
		map.center(player.getX(), player.getY());
	}
	
	public void newGame() {
		if(player.hp <= 0) player.resetStats();
		map.setMap(new TileMap(length, height));
		player.setMap(map.getMap());
		map.getMap().getEntities().add(0, player);
		player.randomTeleport();
		map.center(player.getX(), player.getY());
	}
		

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_K:
				if(player.goNorth()) {
					map.shiftView(0, -1);
				} else {
					player.interact(0, -1);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_H:
				if(player.goWest()) {
					map.shiftView(-1, 0);
				} else {
					player.interact(-1, 0);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_L:
				if(player.goEast()) {
					map.shiftView(1, 0);
				} else {
					player.interact(1, 0);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_J:
				if(player.goSouth()) {
					map.shiftView(0, 1);
				} else {
					player.interact(0, 1);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_Y:
				if(player.goNorthwest()) {
					map.shiftView(-1, -1);
				} else {
					player.interact(-1, -1);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_U:
				if(player.goNortheast()) {
					map.shiftView(1, -1);
				} else {
					player.interact(1, -1);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_B:
				if(player.goSouthwest()) {
					map.shiftView(-1, 1);
				} else {
					player.interact(-1, 1);
				}
				map.rounds(1);
				break;
			case KeyEvent.VK_N:
				if(player.goSoutheast()) {
					map.shiftView(1, 1);
				} else {
					player.interact(1, 1);
				}
				map.rounds(1);
				break;
//			case KeyEvent.VK_Z:
//				hpBar.shiftValue(-10);
//				hpBar.repaint();
//				mpBar.shiftValue(-10);
//				mpBar.repaint();
//				break;
//			case KeyEvent.VK_X:
//				hpBar.shiftValue(10);
//				hpBar.repaint();
//				mpBar.shiftValue(10);
//				mpBar.repaint();
//				break;
//			case KeyEvent.VK_C:
//				hpBar.setValue(0);
//				hpBar.repaint();
//				mpBar.setValue(0);
//				mpBar.repaint();
//				break;
			case KeyEvent.VK_PERIOD:
				map.rounds(1);
				break;
			case KeyEvent.VK_SPACE:
				map.rounds(1);
				break;
			default:
				break;
		}
		
		ip.repaint();
		sp.repaint();
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
