package item;

import java.awt.Color;

/**
 * Class for on-map items
 * @author Ryan Luchs
 *
 */
public class ItemPoint {
	
	// The coordinates (x, y) of the item point on the map
	private int x, y;
	
	// Char and Color to display as
	private String Character;
	private Color color;
	
	public static final String CHAR_POTION = "!";
	public static final String CHAR_WEAPON = "/";
	public static final String CHAR_SCROLL = "$";
	public static final String CHAR_MISC = "i";
	
	public ItemPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
