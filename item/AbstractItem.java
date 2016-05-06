package item;

import java.awt.Color;
import tile.AbstractTile;
import entity.AbstractEntity;

/**
 * RogueFrame item template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractItem {
	
	// the ID number of the item
	protected short itemID;
	
	// Char and Color to display as
	protected String character;
	protected Color color;
	
	// Flavor text
	protected String name, description;
	
	// Can be stacked
	protected boolean stackable;
	
	// Number of uses for an item
	public int uses;
	
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
	
	public short getItemID() {
		return itemID;
	}
	
	public boolean isStackable() {
		return stackable;
	}
}
