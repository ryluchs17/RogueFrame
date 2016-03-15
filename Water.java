import java.awt.Color;

public class Water extends AbstractTile {

	public Water() {
		setKeyFields("~", Color.WHITE , Color.BLUE, false);
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
