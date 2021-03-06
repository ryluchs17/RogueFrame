/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A basic wall tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class StoneWall extends AbstractTile {

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public StoneWall(int x, int y) {
		super(x, y);

		character = "#";
		foreground = Color.WHITE;
		background = Color.BLACK; //new Color(128, 0, 0);
		
		passable = false;
		
		opaque = true;
		
		name = "Stone Wall";
		description = "Rough hewn stone";
	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onInteraction(entity.AbstractEntity)
	 */
	@Override
	public void onInteraction(AbstractEntity e) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

}
