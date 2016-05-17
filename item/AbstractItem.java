package item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import tile.AbstractTile;
import entity.AbstractEntity;

/**
 * RogueFrame item template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractItem {
	
//	// The coordinates (x, y) of the item point on the map
//	private int x = 0, y = 0;
	
	// Char and Color to display as
	protected String character;
	protected Color color;
	
	// Flavor text
	protected String name, description;
	
	// What to display when not yet identified
	public static String name_if_unidentitied;
	
	// ! STORAGE STUFF !
	
	// Can be stacked
	protected boolean stackable;
	
	// determines whether the item can be removed from the top spot of the inventory
	protected boolean cursed = false;
	
	// determines whether the item can be removed from inventory
	protected boolean locked = false;
	
	// Number of uses for an item
	public int uses = 1;
	
	// ! COMBAT STUFF !
	
	// Damage when wielded as weapon
	protected int damage;
	
	// Hit chance (%) when wielded as weapon
	protected int hit;
	
	// Crit chance (%) when wielded as weapon
	protected int crit;
	
	// weapon proficiency
	protected float proficiency = 0.5F;
	
	// whether the item does physical or magical damage when wielded as weapon
	protected boolean magical;
	
	// ! DISPLAY STUFF !
	
	public static final String CHAR_POTION = "!";
	public static final String CHAR_WEAPON = "/";
	public static final String CHAR_SCROLL = "$";
	public static final String CHAR_MISC = "i";
	
	public AbstractItem() {
		
	}
	
	/**
	 * What happens when the item is used
	 */
	abstract public void onUse(AbstractEntity m);
	
	/**
	 * What happens when the item is throw
	 */
	abstract public void onThrown(AbstractTile t);
	
	/**
	 * What happens to an entity that equips this item
	 */
	abstract public void onEquipt(AbstractEntity m);
	
	public void draw(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.drawString(character, x, y + AbstractTile.STEP);
		
	}
	
	public void drawText(Graphics g, int x, int y) {
		
		draw(g, x, y);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.drawString(name, x + AbstractTile.STEP, y + AbstractTile.STEP);
		g2d.drawString(description, x, y + (AbstractTile.STEP + 2) * 2);
		g2d.drawString(damage + " x " + proficiency + " damage" , x, y + (AbstractTile.STEP + 2) * 3);
		g2d.drawString(hit + "%" + " hit", x, y + (AbstractTile.STEP + 2) * 4);
		g2d.drawString(crit + "%" + " crit", x, y + (AbstractTile.STEP + 2) * 5);
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

//	public void setPosition(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//
//	/**
//	 * @return the x
//	 */
//	public int getX() {
//		return x;
//	}
//
//	/**
//	 * @return the y
//	 */
//	public int getY() {
//		return y;
//	}
	
	public boolean isStackable() {
		return stackable;
	}
}
