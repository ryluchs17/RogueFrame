package tile;

import java.awt.Color;
import entity.AbstractEntity;

/**
 * A water tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Water extends AbstractTile {

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Water(int x, int y) {
		super(x, y);

		character = "~";
		foreground = Color.WHITE;
		background = Color.BLUE;
		
		passable = true;
		
		name = "Water";
		description = "Water of indeterminate depth.";
	}

	public void onInteraction(AbstractEntity m) {

	}
	
	public void onTurn() {
		
	}

	@Override
	public void onEntry() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean canEnter(AbstractEntity e) {
		return (!this.isOccupied()) && this.isPassable() && (e.ignore ? true : !avoid) && !e.grounded;
	}

}
