import java.awt.Color;

/**
 * RougeFrame tile object
 * @author Ryan Luchs
 *
 */

public abstract class AbstractTile {
	
	// Char and Color to display as
	protected String character;
	protected Color foreground;
	protected Color background;
	
	// Flavor text
	protected String name;
	protected String description;
	
	// Determines if entities can pass through it
	protected boolean solid;
	
	// Determines whether the tile is full
	protected boolean occupied = false;
	
	// Determines whether or not to display background instead of the tile
	protected boolean covered = false;
	
	// // Determines how much damage a tile can take before destruction
	// private int durability;
	// private int blastResistance;
	// private int Material;

	/**
	 * Fake constructor for Tile
	 */
	protected void setKeyFields(String c, Color fg, Color bg, boolean s) {
		character = c;
		foreground = fg;
		background = bg;
		solid = s;
	}
	
	/**
	 * What the tile does when an mob interacts with it
	 */
	abstract public void onInteraction(AbstractEntity e);
	
	/**
	 * What happens to an mob occupying it each turn
	 */
	abstract public void onOccupation(AbstractEntity e);
	
	/**
	 * What happens to the tile each turn regardless of conditions
	 */
	abstract public void onTurn();

	/**
	 * Returns whether the tile is solid
	 * @return Whether the tile is solid
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Returns whether a tile is occupied
	 * @return Whether the tile is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Sets whether the tile is occupied
	 * @param occupied Whether the tile should be occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Determines whether the tile is covered
	 * @return Whether the tile is covered
	 */
	public boolean isCovered() {
		return covered;
	}

	/**
	 * Set tile visibility
	 * @param iscovered Whether the tile should be covered
	 */
	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	//FIX ME
	/**
	 * Determines whether an mob can enter the tile
	 * @param A the mob to enter the tile
	 * @return Whether the tile is free to move into or not
	 */
	public boolean canEnter(AbstractEntity m) {
		return !(occupied && solid);
	}
}