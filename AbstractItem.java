import java.awt.Color;

/**
 * RogueFrame item template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractItem {
	
	// Char and Color to display as
	protected String character;
	protected Color foreground;
	protected Color background;
	
	// Flavor text
	protected String name;
	protected String description;
	
	// Can be stacked
	protected boolean isStackable;
	
	// Number of used for an item
	protected int uses;
	
	// XY position on the grid
	protected int x; protected int y;
	
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
