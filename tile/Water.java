package tile;

import java.awt.Color;
import entity.AbstractEntity;

public class Water extends AbstractTile {

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

}
