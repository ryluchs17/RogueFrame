import java.awt.Color;

/**
 * RougeFrame entity template
 * @author Ryan Luchs
 *
 */

public abstract class Mob {

	// Char and Color to display as
	private char character;
	private Color foreground;
	private Color background;
	
	// Determines whether the mob interacts with solid objects
	private boolean tangible;
	
	// Determines whether or not to display the tile under the mob
	private boolean visible;
	
	// XY position on the grid
	private int x; private int y;
	
}
