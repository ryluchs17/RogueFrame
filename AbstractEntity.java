import java.awt.Color;

/**
 * RougeFrame entity template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractEntity {

	// Char and Color to display as
	protected String character;
	protected Color foreground;
	protected Color background;
	
	// Flavor text
	protected String name;
	protected String description;
	
	// Determines whether the mob interacts with solid objects
	protected boolean tangible = true;
	
	// Determines whether or not to display the tile under the mob
	protected boolean visible = true;
	
	// XY position on the grid
	protected int x; protected int y;
	
	/**
	 * Fake constructor for Tile
	 */
	protected void setKeyFields(String c, Color fg, Color bg) {
		character = c;
		foreground = fg;
		background = bg;
	}
	
}
