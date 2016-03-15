import java.awt.Color;

public class Soil extends AbstractTile {

	public Soil() {
		setKeyFields("-", new Color(100, 50, 0) , Color.YELLOW, false);
		name = "Soil";
		description = "Soft, loamy soil.";
	}

	public void onInteraction(AbstractEntity m) {

	}

	public void onOccupation(AbstractEntity m) {

	}
	
	public void onTurn() {
		
	}

}