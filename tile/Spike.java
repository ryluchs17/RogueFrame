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
		
		passable = true;
		
		name = "Spike";
		description = "Rather pointy.";
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
		// if grounded damage
		if(occupant.grounded) {
			occupant.addHitpoints(-5);
			// if stepped on and not red turn red
			if(foreground != Color.RED) {
				foreground = Color.RED;
				description = "That must've hurt...";
			}
		}
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

}
