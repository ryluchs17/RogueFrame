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
	
	// Min and max damage
	protected int min_dam, max_dam;
	
	// weapon proficiency
	protected float proficiency = 0.5F;
	
	// whether the item does physical or magical damage when equipt
	protected boolean magical;
	
//	// XY position on the grid
//	protected int x, y;
	
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
	
	public int getUses() {
		return uses;
	}
}
