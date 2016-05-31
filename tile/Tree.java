/**
 * 
 */
package tile;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A tree tile for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Tree extends AbstractTile {

	private static final Color TREE_BROWN = new Color(84, 55, 8);
	
	/**
	 * Creates an new instance at (x, y) on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public Tree(int x, int y) {
		super(x, y);
		
		character = "|";
		foreground = Color.GREEN;
		background = TREE_BROWN;
		
		passable = false;
		opaque = true;
		
		name = "Tree";
		description = "A living column";
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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tile.AbstractTile#onExit()
	 */
	@Override
	public void onExit() {
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
