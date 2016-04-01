package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.AbstractEntity;

/**
 * RougeFrame tile object
 * @author Ryan Luchs
 *
 */

public abstract class AbstractTile {
	
	// The coordinates (x, y) of the tile on the map
	protected int x, y;
	
	// Char and Color to display as
	protected String character;
	protected Color foreground, background;
	
	// Flavor text
	protected String name, description;
	
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
	 * STEP^2 is the area of a tile in pixels
	 */
	public static final int STEP = 10;

	/**
	 * Creates an AbstractTile with the coordinates (x, y)
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 */
	public AbstractTile(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// ! ABSTRACT METHODS BEGIN !
	
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
	
	// ! ABSTRACT METHODS END !
	
	/**
	 * Draws the tile at the given coordinates
	 * @param g the graphics object to draw on
	 * @param x x-coordinate
	 * @param y y-coordinate
	 */
	public void draw(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(background);
		g2d.fillRect(x, y, STEP, STEP);
		g2d.setColor(foreground);
		g2d.drawString(character, x, y + STEP);
		
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