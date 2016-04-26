package tile;

import java.awt.Color;
import entity.AbstractEntity;

public class Soil extends AbstractTile {

	public Soil(int x, int y) {
		super(x, y);

		character = "-";
		foreground = new Color(100, 50, 0);
		background = Color.YELLOW;
		
		passable = true;
		
		name = "Soil";
		description = "Soft, loamy soil.";
	}

	public void onInteraction(AbstractEntity m) {

	}

	public void onOccupation() {

	}
	
	public void onTurn() {
		
	}

}