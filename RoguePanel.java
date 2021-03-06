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
 * The main game class for RogueFrame
 * @author Ryan Luchs
 *
 */
public class RoguePanel extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 1;
	
	private int length = 40; private int height = 40;
	//private MapPanel map = new MapPanel(length, height, 18, 12, 420L);
	//private MapPanel map = new MapPanel(20, 20, 15, 15, 420L);
	//private MapPanel map = new MapPanel(100, 100, 40, 40, 420L);
	private MapPanel map;
	//private MapPanel map = new MapPanel(length, height, 35, 35);
	//private MapPanel map = new MapPanel(length, height, 35, 35, 42L);
//	private InfoBar hpBar = new InfoBar("Health", 100, 50, 100);
//	private InfoBar mpBar = new InfoBar("Mana", 100, 50, 100);
	
	private InventoryPanel ip;
	private StatusPanel sp;
	
	private AbstractEntity player;
	private int depth = 0;
	private Random generate = new Random();
	private long[] seeds;
	private TileMap[] levels;
	
	/**
	 * RoguePanel constructor
	 */
	public RoguePanel() {
		
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		generateWorld();
		
		map = new MapPanel(levels[depth], player, 35, 30);
		map.center(player.getX(), player.getY());
		
		add(map, BorderLayout.CENTER);
		
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
	
	/**
	 * Makes a new player
	 */
	public void newGame() {
		player.hp = player.getHealthCap();
		levels[depth].getEntities().add(0, player);
		player.randomTeleport();
		map.rounds(1);
	}
	
	/**
	 * Generates a new set of levels
	 */
	public void generateWorld() {
		seeds = new long[5 + generate.nextInt(5)];
		for(int i = 0; i < seeds.length - 1; i++) {
			seeds[i] = generate.nextLong();
		}
		
		levels = new TileMap[seeds.length];
		levels[depth] = new TileMap(length/* + generate.nextInt(20)*/, height/* + generate.nextInt(20)*/, depth, 1, (short) generate.nextInt(5), seeds[depth]);
		
		player = new Player(0, 0, 1, levels[depth]);
		player.randomTeleport();
		levels[depth].getEntities().add(0, player);
		levels[depth].player = player;
	}
	
	/**
	 * Shifts the player into the next level
	 * Generates a new level if one does not yet exist in that location
	 */
	public void descend() {
		if(depth + 1 < levels.length) {
			if(levels[depth + 1] == null) {
				if(depth + 1 == levels.length - 1) {
					levels[depth + 1] = new TileMap(40, 40, depth, player.getLevel(), (short) 5 , seeds[depth + 1]);
				} else {
					levels[depth + 1] = new TileMap(40, 40, depth, player.getLevel(), (short) generate.nextInt(5), seeds[depth + 1]);
				}
			}
			
			levels[depth].getEntities().remove(player);
			levels[depth].player = null;
			
			++depth;
			
			levels[depth].player = player;
			
			player.setMap(levels[depth]);
			player.randomTeleport();
			levels[depth].getEntities().add(0, player);
			
			map.setMap(levels[depth]);
			map.center(player.getX(), player.getY());
		}
	}
	
	/**
	 * Shifts the player into the previous level
	 */
	public void ascend() {
		if(depth > 0) {
			levels[depth].getEntities().remove(player);
			levels[depth].player = null;
			
			--depth;
			
			levels[depth].player = player;
			
			player.setMap(levels[depth]);
			player.randomTeleport();
			levels[depth].getEntities().add(0, player);
			
			map.setMap(levels[depth]);
			map.center(player.getX(), player.getY());
		}
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
			case KeyEvent.VK_Z:
				ascend();
				break;
			case KeyEvent.VK_X:
				descend();
				break;
			case KeyEvent.VK_PERIOD:
				map.rounds(1);
				break;
			case KeyEvent.VK_SPACE:
				map.rounds(1);
				break;
			case KeyEvent.VK_R:
				repaint();
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
