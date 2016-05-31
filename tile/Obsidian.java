/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A black lava glass destructible wall tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Obsidian extends AbstractTile {

	private static final Color OBSIDIAN_COLOR = Color.MAGENTA.darker().darker();
	
	private short damage = 0;
	
	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Obsidian(int x, int y) {
		super(x, y);

		character = "#";
		foreground = OBSIDIAN_COLOR;
		background = Color.BLACK; //new Color(128, 0, 0);
		
		passable = false;
		
		opaque = true;
		
		name = "Obsidian";
		description = "Black lava glass";
	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onInteraction(entity.AbstractEntity)
	 */
	@Override
	public void onInteraction(AbstractEntity e) {
		if(damage < 3) {
			
			damage++;
			if(damage == 3) {
				passable = true;
				
				opaque = false;
				
				character = ",";
				
				name = "Obsidian rubble";
				description = "There was a wall here...";
			}
			
		}

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onTurn()
	 */
	@Override
	public void onTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEntry() {
		if(damage == 3 && occupant.grounded) {
			occupant.hp -= 2;
		}
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

}
