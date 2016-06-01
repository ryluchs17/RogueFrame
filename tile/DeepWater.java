package tile;

import java.awt.Color;
import entity.AbstractEntity;

/**
 * An impassable water tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class DeepWater extends AbstractTile {
	
	private static final Color DEEP_WATER_BLUE = Color.BLUE.darker();

	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public DeepWater(int x, int y) {
		super(x, y);

		character = "~";
		foreground = Color.WHITE;
		background = DEEP_WATER_BLUE;
		
		passable = false;
		
		name = "Water";
		description = "Extremely deep";
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

}
