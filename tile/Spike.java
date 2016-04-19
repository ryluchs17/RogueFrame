/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * @author SJHSStudent
 *
 */
public class Spike extends AbstractTile {

	public Spike(int x, int y) {
		super(x, y);

		character = "^";
		foreground = Color.GRAY;
		background = Color.BLACK;
		
		solid = false;
		
		name = "Spike";
		description = "Rather pointy...";
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
		occupant.addHitpoints(-5);
		if(foreground != Color.RED) foreground = Color.RED;

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onTurn()
	 */
	@Override
	public void onTurn() {
		// TODO Auto-generated method stub

	}

}
