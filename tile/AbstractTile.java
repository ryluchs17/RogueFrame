package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.AbstractEntity;
import item.AbstractItem;

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
	protected boolean passable;
	
	// Determines if this tile should be avoided by ai
	protected boolean avoid = false; 
	
//	// Determines whether or not to display background instead of the tile
//	protected boolean covered = false;
	
	// An item stored on the tile
	protected AbstractItem item = null;
	
	// the abstract entity occupying this tile
	protected AbstractEntity occupant = null;
	
	// whether an object is see-through or not
	protected boolean opaque = false;
	
	// // Determines how much damage a tile can take before destruction
	// private int durability;
	// private int blastResistance;
	// private int Material;
	
	/**
	 * STEP^2 is the area of a tile in pixels
	 */
	public static final int STEP = 12;
	
	/**
	 * The ratio of the height of the tooltip to STEP
	 */
	public static final double TOOLTIP_BOX_RATIO = 1.2;
	
	/**
	 * The text color that tooltips for an item filled Tile displays as
	 */
	public static final Color TOOLTIP_ITEM = new Color(97, 247, 255);
	
	/**
	 * The text color that tooltips for an occupied Tile displays as
	 */
	public static final Color TOOLTIP_ENTITY = new Color(255, 105, 97);

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
	 * What happens to an entity initially occupies this tile
	 */
	abstract public void onEntry();
	
	/**
	 * What happens to an entity that leaves this tile
	 */
	abstract public void onExit();
	
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
//		g2d.setColor(Color.YELLOW);
//		g2d.drawRect(x, y, STEP, STEP);
		
		if(occupant != null) {
			occupant.draw(g, x, y);
		} else if(item != null) {
			item.draw(g, x, y);
		} else {
			g2d.setColor(foreground);
			g2d.drawString(character, x, y + (int) (STEP*0.75));
		}
		
//		if(covered) {
//			g2d.setColor(Color.CYAN);
//			g2d.drawString("X", x, y + STEP);
//		}
	}
	
	/**
	 * Draws the a text box containing the tile name, description and a same image at the given coordinates
	 * @param g the graphics object to draw on
	 * @param x x the x-coordinate
	 * @param y y the y-coordinate
	 */
	public void drawTooltip(Graphics g, int x, int y) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(occupant != null) {
			// make an empty black square to put text in
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, occupant.getDescription().length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
			
			draw(g, (int) (x + (STEP*TOOLTIP_BOX_RATIO)/2), (int) (y + (STEP*TOOLTIP_BOX_RATIO)/2));
			
			// prepare to draw text and border
			g2d.setColor(this.TOOLTIP_ENTITY);
			
			// draw in text in white		
			g2d.drawString(occupant.getName() + ":", x + STEP * 2, y + STEP + 2);
			g2d.drawString(occupant.getDescription(), x + STEP * 2, y + STEP * 2 + 2);
			
			// draw white rectangle as border
			g2d.drawRect(x, y, occupant.getDescription().length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
		} else if(item != null) {
			// make an empty black square to put text in
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, item.getDescription().length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
			
			draw(g, (int) (x + (STEP*TOOLTIP_BOX_RATIO)/2), (int) (y + (STEP*TOOLTIP_BOX_RATIO)/2));
			
			// prepare to draw text and border
			g2d.setColor(this.TOOLTIP_ITEM);
			
			// draw in text		
			g2d.drawString(item.getName() + ":", x + STEP * 2, y + STEP + 2);
			g2d.drawString(item.getDescription(), x + STEP * 2, y + STEP * 2 + 2);
			
			// draw white rectangle as border
			g2d.drawRect(x, y, item.getDescription().length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
		} else {
			// make an empty black square to put text in
			g2d.setColor(Color.BLACK);
			g2d.fillRect(x, y, description.length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
			
			draw(g, (int) (x + (STEP*TOOLTIP_BOX_RATIO)/2), (int) (y + (STEP*TOOLTIP_BOX_RATIO)/2));
			
			// prepare to draw text and border
			g2d.setColor(Color.WHITE);
			
			// draw in text in white		
			g2d.drawString(name + ":", x + STEP * 2, y + STEP + 2);
			g2d.drawString(description, x + STEP * 2, y + STEP * 2 + 2);
			
			// draw white rectangle as border
			g2d.drawRect(x, y, description.length() * STEP, (int) (TOOLTIP_BOX_RATIO * STEP * 2));
		}
		
	}
	
	/**
	 * Gets the length of the tooltip for this tile
	 * @return The tooltip length
	 */
	public int getTooltipLength() {
		return description.length() * STEP;
	}
	
	/**
	 * Gets the height of the tooltip for this tile
	 * @return The tooltip height
	 */
	public int getTooltipHeight() {
		return (int) (TOOLTIP_BOX_RATIO * STEP * 2) + 1;
	}
	
	/**
	 * Sets the AbstractItem contained by this AbstractTile
	 * @param item The AbstractItem
	 */
	public void setItem(AbstractItem item) {
		this.item = item;
	}
	
	/**
	 * Gets the AbstractItem contained by this AbstractTile
	 * @return The AbstractItem
	 */
	public AbstractItem getItem() {
		return item;
	}
	
	/**
	 * Gets whether this AbstractTile contains an AbstactItem
	 * @return true if has item
	 */
	public boolean hasItem() {
		return item != null;
	}
	
	/**
	 * Sets a AbstractEntity to occupy this AbstractTile
	 * @param e The entity
	 */
	public void setOccupant(AbstractEntity e) {
		if(occupant != null) {
			onExit();
		}
		occupant = e;
		if(occupant != null) {
			onEntry();
		}
	}
	
	/**
	 * Gets the occupant of this AbstractTile
	 * @return The occupant
	 */
	public AbstractEntity getOccupant() {
		return occupant;
	}
	
	/**
	 * Gets the x-coordinate of this AbstractTile
	 * @return the x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y-coordinate of this AbstractTile
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns whether the tile is solid
	 * @return Whether the tile is solid
	 */
	public boolean isPassable() {
		return passable;
	}

	/**
	 * Returns whether a tile is occupied
	 * @return Whether the tile is occupied
	 */
	public boolean isOccupied() {
		return occupant != null;
	}

//	/**
//	 * Determines whether the tile is covered
//	 * @return Whether the tile is covered
//	 */
//	public boolean isCovered() {
//		return covered;
//	}

//	/**
//	 * Set tile visibility
//	 * @param covered Whether the tile should be covered
//	 */
//	public void setCovered(boolean covered) {
//		this.covered = covered;
//	}
	
	/**
	 * Returns whether an AbtractEntity can see through this AbstractTile
	 * @return true if can not see
	 */
	public boolean isOpaque() {
		return opaque;
	}

	/**
	 * Returns whether an AbstractEntity can enter this AbstractTile ignoring any occupants
	 * @param e The AbstractEntity
	 * @return true if can enter
	 */
	public boolean canApproach(AbstractEntity e) {
		return this.isPassable() && (e.ignore ? true : !avoid);
	}
	
	/**
	 * Determines whether an mob can enter the tile
	 * @param A the mob to enter the tile
	 * @return Whether the tile is free to move into or not
	 */
	public boolean canEnter(AbstractEntity e) {
		
		/*
		 * is hazard true and ignore hazard true == true
		 * is hazard false and ignore hazard true == true
		 * is hazard true and ignore hazard false == false
		 * is hazard false and ignore hazard false == true
		 */
		
		return (!this.isOccupied()) && this.isPassable() && (e.ignore ? true : !avoid);
	}

	/**
	 * Returns the name of this AbstractTile
	 * @return The name
	 */
	public String getName() {
		return name;
	}
}