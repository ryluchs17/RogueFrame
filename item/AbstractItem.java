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
	
	// determines whether the item can be unequipt
	public boolean cursed = false;
	
	// determines whether cursed status is shown on details screen
	public boolean curseIsKnown = false; //TODO
	
	// determines whether the item is locked in its inventory slot
	protected boolean locked = false;
	
	// determines whether the item can be thrown
	protected boolean throwable = true;
	
	// determines whether the item is consumed upon use
	// does not actually cause item to be consumed, merely tells external classes if it is
	protected boolean consumable = false;
	
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
	protected float proficiency = 0.25F;
	
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
	abstract public void onThrown(AbstractEntity e);
	
	/**
	 * What happens to an entity that equips this item
	 */
	abstract public void onEquipt(AbstractEntity m);
	
	/**
	 * Draws this AbstractItem at the given coordinates (x, y)
	 * @param g The graphic object to draw on
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public void draw(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.drawString(character, x, y + (int) (AbstractTile.STEP * 0.75));
		
	}
	
	/**
	 * Draws this AbstractItem at the given coordinates (x, y) unadjusted for fitting on a menu
	 * @param g The graphic object to draw on
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public void drawUnshifted(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.drawString(character, x, y + AbstractTile.STEP);
		
	}
	
	/**
	 * Draws a summery of this AbstractItem (x, y)
	 * @param g The graphic object to draw on
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public void drawText(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.drawString(character, x, y + AbstractTile.STEP);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString( curseIsKnown && cursed ? "Cursed" + name : name, x + AbstractTile.STEP, y + AbstractTile.STEP);
		g2d.drawString(description, x, y + (AbstractTile.STEP + 2) * 2);
		
		g2d.setColor(Color.GRAY);
		g2d.drawString(magical ? "Magical" : "Non-magical", x, y + (AbstractTile.STEP + 2) * 3);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString((int) (damage * proficiency) + " damage" , x, y + (AbstractTile.STEP + 2) * 4);
		g2d.drawString(hit + "%" + " hit", x, y + (AbstractTile.STEP + 2) * 5);
		g2d.drawString(crit + "%" + " crit", x, y + (AbstractTile.STEP + 2) * 6);
		
	}
	
	/**
	 * Gets the name of this AbstractItem
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the description of this AbstractItem
	 * @return The description
	 */
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
	
	/**
	 * Returns whether this AbstractItem can be stacked
	 * @return true if stackable
	 */
	public boolean isStackable() {
		return stackable;
	}
	
	/**
	 * Returns whether this AbstractItem can be unequipped
	 * @return true if not unequippable
	 */
	public boolean isCursed() {
		return cursed;
	}
	
	/**
	 * Returns whether this AbstractItem is locked in an inventory position
	 * @return true if locked
	 */
	public boolean isLocked() {
		return locked;
	}
	
	/**
	 * Returns whether this AbstractItem can be thrown
	 * @return true if throwable
	 */
	public boolean isThrowable() {
		return !cursed && !locked;
	}
	
	/**
	 * Returns whether this AbstractItem is consumed on use
	 * @return true if consumable
	 */
	public boolean isConsumable() {
		return consumable;
	}
	
	/**
	 * Returns the damage dealt by this AbstractItem when used as a weapon
	 * @return the damage
	 */
	public int getDamage() {
		return (int) (damage * proficiency);
	}
	
	/**
	 * Returns the hit rate of this AbtractItem
	 * @return the hit rate
	 */
	public int getHit() {
		return hit;
	}
	
	/**
	 * Returns the critical hit rate of this AbtractItem
	 * @return the critical hit rate
	 */
	public int getCrit() {
		return crit;
	}
	
	/**
	 * Returns whether this AbstractItem is magical for purposes of damage calculation
	 * @return true if magical
	 */
	public boolean isMagical() {
		return magical;
	}
	
	/**
	 * Increases this AstractItem's internal damage multiplier
	 */
	public void improve() {
		if(proficiency < 1) proficiency += 0.0025;
	}
	
	public Object clone() {
		try {
			AbstractItem clone = (AbstractItem) Class.forName(this.getClass().getName()).newInstance();
			clone.damage = damage;
			clone.proficiency = proficiency;
			return clone;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
