/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A trap for grass flooring for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Snare extends AbstractTile {
	
	private static final Color GRASS_FOREGROUND = new Color(100, 50, 0);
	
	private boolean tripped = false;

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Snare(int x, int y) {
		super(x, y);
		
		character = "-";
		foreground = GRASS_FOREGROUND;
		background = Color.GREEN;
		
		passable = true;
		
		name = "Grass";
		description = "Natural carpeting";
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
			occupant.hp -= occupant.getHealthCap()/4;
			
			character = "~";
			
			name = "Snare";
			description = "Someone took a nasty fall...";
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
