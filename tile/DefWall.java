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
public class DefWall extends AbstractTile {

	public DefWall(int x, int y) {
		super(x, y);

		character = "#";
		foreground = Color.WHITE;
		background = Color.BLACK;
		
		unpassable = true;
		
		name = "Wall";
		description = "A sturdy wall.";
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
	public void onOccupation(AbstractEntity e) {
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
