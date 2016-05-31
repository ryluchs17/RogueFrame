/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A web tile for RogueFrame that slows an occupant down, if not an arachnid
 * @author Ryan Luchs
 *
 */
public class Web extends AbstractTile {

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Web(int x, int y) {
		super(x, y);

		character = "%";
		foreground = Color.WHITE;
		background = Color.BLACK;
		
		passable = true;
		
		name = "Web";
		description = "A spider's sticky lair";
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
		if(occupant.grounded && !occupant.getName().matches("Spider")) {
			occupant.spd -= 10;
			occupant.skl -= 10;
		}

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onExit()
	 */
	@Override
	public void onExit() {
		if(occupant.grounded && !occupant.getName().matches("Spider")) {
			occupant.spd += 10;
			occupant.skl += 10;
		}

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onTurn()
	 */
	@Override
	public void onTurn() {
		// TODO Auto-generated method stub

	}

}
