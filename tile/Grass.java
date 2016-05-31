package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * An outdoors type floor tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Grass extends AbstractTile {

	private static final Color GRASS_FOREGROUND = new Color(100, 50, 0);
	
	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Grass(int x, int y) {
		super(x, y);
		
		character = "-";
		foreground = GRASS_FOREGROUND;
		background = Color.GREEN;
		
		passable = true;
		
		name = "Grass";
		description = "Natural carpeting";
	}

	@Override
	public void onInteraction(AbstractEntity e) {
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

	@Override
	public void onTurn() {
		// TODO Auto-generated method stub

	}

}
