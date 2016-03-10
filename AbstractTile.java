import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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

	// all of the above compiled into a JLabel
	public JLabel label;
	
	// Determines if entities can pass through it
	protected boolean solid;
	
	// Determines whether the tile is full
	protected boolean occupied = false;
	
	// Determines whether or not to display backround instead of the tile
	protected boolean visible = true;
	
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

		label = new JLabel(c);
		label.setFont(new Font("Monospaced", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(fg);

		if(bg != Color.BLACK) {
			label.setOpaque(true);
			label.setBackground(bg);
		}

		solid = s;
	}
	
	/**
	 * This method specifies what the tile does when an mob interacts with it
	 */
	abstract public void onInteraction(Mob e);
	
	/**
	 * This method specifies what happens to an mob occupying it each turn
	 */
	abstract public void onOccupation(Mob e);

	public void setLabelDefaults() {
		label.setText(character);
		label.setForeground(foreground);
		label.setBackground(background);
	}

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
	 * Determines whether the tile is visible
	 * @return Whether the tile is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Set tile visibility
	 * @param isVisible Whether the tile should be visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	//FIX ME
	/**
	 * Determines whether an mob can enter the tile
	 * @param A the mob to enter the tile
	 * @return Whether the tile is free to move into or not
	 */
	public boolean canEnter(Mob m) {
		return !(occupied && solid);
	}
}