package entity;

import java.awt.Color;
import tile.AbstractTile;

public class Hidden extends AbstractEntity{

	public Hidden(int x, int y, int level) {
		super(x, y, level);
		
		this.character = "H";
		this.foreground = Color.BLACK;
		
		this.name = "Hidden";
		this.description = "A large black bird that hunts in total darkness.";
		
		this.hp_base = 100;
		this.atk_base = 125;
		this.def_base = 52;
		this.mag_base = 79;
		
		setStats(true);
	}
	
	public void onTurn(AbstractTile[][] map) {
		if(x != 0 && y != 0) {
			map[x][y].setOccupant(null);
			goTo(0, 0);
			map[x][y].setOccupant(this);
		}
	}
}
