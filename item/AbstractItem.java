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
	
	// Char and Color to display as
	protected String character;
	protected Color foreground, background;
	
	// Flavor text
	protected String name, description;
	
	// Can be stacked
	protected boolean isStackable;
	
	// Number of used for an item
	protected int uses;
	
	// XY position on the grid
	protected int x, y;
	
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
	
}
