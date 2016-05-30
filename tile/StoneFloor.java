/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * @author ryan
 *
 */
public class StoneFloor extends AbstractTile {

	public StoneFloor(int x, int y) {
		super(x, y);
		
		character = ".";
		foreground = new Color(255, 255, 255);
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
