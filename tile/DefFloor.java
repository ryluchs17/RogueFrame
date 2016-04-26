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
public class DefFloor extends AbstractTile {

	public DefFloor(int x, int y) {
		super(x, y);
		
		character = ".";
		foreground = new Color(255, 255, 255);
		background = Color.BLACK;
		
		passable = true;
		
		name = "Floor";
		description = "Unremarkable";
	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onInteraction(entity.AbstractEntity)
	 */
	@Override
	public void onInteraction(AbstractEntity e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onOccupation(entity.AbstractEntity)
	 */
	@Override
	public void onOccupation() {
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
