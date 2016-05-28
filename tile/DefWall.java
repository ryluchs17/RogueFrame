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
		background = Color.BLACK; //new Color(128, 0, 0);
		
		passable = false;
		
		opaque = true;
		
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
	 * @see tile.AbstractTile#onTurn()
	 */
	@Override
	public void onTurn() {
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

}
