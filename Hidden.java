import java.awt.Color;

public class Hidden extends AbstractEntity{

	public Hidden(int x, int y) {
		setKeyFields("H", Color.BLACK, Color.BLACK);
		name = "Hidden";
		description = "A large black bird that hunts its prey in total darkness.";
	}
}
