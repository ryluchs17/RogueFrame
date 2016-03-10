import java.awt.Color;

public class Soil extends AbstractTile {

	public Soil() {
		setKeyFields("M", new Color(100, 50, 0) , Color.YELLOW, false);
	}

	public void onInteraction(Mob m) {

	}

	public void onOccupation(Mob m) {

	}
	
	public void onTurn() {
		
	}

}