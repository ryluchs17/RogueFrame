package entity;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import tile.AbstractTile;

public class Hidden extends AbstractEntity{
	private Random r = new Random();
	
	private int seekX;
	private int seekY;

	public Hidden(int x, int y, int level) {
		super(x, y, level);
		
		this.character = "H";
		this.color = Color.BLACK;
		
		this.name = "Hidden";
		this.description = "A large black bird that hunts in darkness.";
		
//		this.hp_base = 100;
//		this.atk_base = 125;
//		this.def_base = 52;
//		this.mag_base = 79;
//		
//		setStats(true);
		
		seekX = r.nextInt(15);
		seekY = r.nextInt(15);
	}
	
	public void onTurn() {
//		map.updateAt(x, y);
		
		map.tileAt(x, y).setOccupant(null);
		goTo(seekX, seekY);
		map.tileAt(x, y).setOccupant(this);
		
		if((x == seekX && y == seekY )|| r.nextInt(16) == 8) {
			seekX = r.nextInt(15);
			seekY = r.nextInt(15);
		}
		
		System.out.println(name + " @ " + x + ", " + y + " hp: " + hitpoints);
		
//		map.updateAt(x, y);
	}
}
