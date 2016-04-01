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
	private Color foreground, background;
	
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
