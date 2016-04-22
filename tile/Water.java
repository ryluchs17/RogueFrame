package tile;

import java.awt.Color;
import entity.AbstractEntity;

public class Water extends AbstractTile {

	public Water(int x, int y) {
		super(x, y);

		character = "~";
		foreground = Color.WHITE;
		background = Color.BLUE;
		
		unpassable = false;
		
		name = "Water";
		description = "Water of indeterminate depth.";
	}

	public void onInteraction(AbstractEntity m) {

	}

	public void onOccupation(AbstractEntity m) {

	}
	
	public void onTurn() {
		
	}

}
