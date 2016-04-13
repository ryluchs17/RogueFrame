package entity;

import java.awt.Color;

public class Hidden extends AbstractEntity{

	public Hidden(int x, int y, int level) {
		super(x, y, level);
		
		this.character = "H";
		this.foreground = Color.BLACK;
		
		this.name = "Hidden";
		this.description = "A large black bird that hunts in total darkness.";
		
		this.baseHitpoints = 100;
		this.baseAtk = 125;
		this.baseDef = 52;
		this.baseMag = 79;
		
		setStats(true);
	}
}
