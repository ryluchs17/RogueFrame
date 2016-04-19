/**
 * 
 */
package entity;

import java.awt.Color;
import java.util.Random;

/**
 * @author SJHSStudent
 *
 */
public class Bat extends AbstractEntity {

	private Random r = new Random();
	
	private int seekX;
	private int seekY;
	
	public Bat(int x, int y, int level) {
		super(x, y, level);
		
		this.character = "b";
		this.color = new Color(140, 100, 60);
		
		this.name = "Bat";
		this.description = "A bat! How cute!";
		
		this.hp_base = 60;
		this.atk_base = 75;
		this.def_base = 40;
		this.mag_base = 30;
		
		setStats(true);
		
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
