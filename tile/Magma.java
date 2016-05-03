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
public class Magma extends AbstractTile {
	
	private boolean colorState = false;
	
	public Magma(int x, int y) {
		super(x, y);
		
		character = "~";
		foreground = Color.ORANGE;
		background = Color.RED;
		
		passable = true;
		avoid = true;
		
		name = "Magma";
		description = "Don't touch the floor.";
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
		colorState = !colorState;
		foreground = colorState ? Color.RED : Color.ORANGE;
		background = colorState ? Color.ORANGE : Color.RED;
		
		if(occupant != null) {
			//occupant.addHitpoints(-10);
		}

	}

	@Override
	public void onEntry() {
		if(occupant.grounded) {
			occupant.kill();
		} else {
			//occupant.addHitpoints(-10);
		}
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}

}
