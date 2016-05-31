/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A trap for stone flooring for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Trap extends AbstractTile {
	
	private boolean tripped = false;

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Trap(int x, int y) {
		super(x, y);
		
		character = ".";
		foreground = Color.WHITE;
		background = Color.BLACK;
		
		passable = true;
		
		name = "Stone Floor";
		description = "Rough on the feet";
	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onInteraction(entity.AbstractEntity)
	 */
	@Override
	public void onInteraction(AbstractEntity e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onEntry()
	 */
	@Override
	public void onEntry() {
		if(!tripped && occupant.grounded) {
			occupant.hp -= occupant.getHealthCap()/3;
			
			character = "X";
			
			name = "Trap";
			description = "That smarts...";
			
			tripped = true;
		}

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onExit()
	 */
	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onTurn()
	 */
	@Override
	public void onTurn() {
		// TODO Auto-generated method stub

	}

}
