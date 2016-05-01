/**
 * 
 */
package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author SJHSStudent
 *
 */
public class Bat extends AbstractEntity {

	private Random r = new Random();
	
	private int seekX;
	private int seekY;
	
	private int sedentaryFor = 0;
	
	public Bat(int x, int y, int level) {
		super(x, y, level);
		
		this.character = "b";
		this.color = new Color(140, 100, 60);
		
		this.name = "Bat";
		this.description = "A bat! How cute!";
		
		grounded = false;
		
		this.hp_base = 60;
		this.atk_base = 75;
		this.def_base = 40;
		this.mag_base = 30;
		
		setStats(true);
		
		seekX = x;
		seekY = y;
	}
	
	public void onTurn() {
//		map.updateAt(x, y);
		
//		if(!goTo(seekX, seekY)) sedentaryFor++;
//		
//		if((x == seekX && y == seekY )|| sedentaryFor >= 5 || !map.tileAt(seekX, seekY).isPassable()) {
//			seekX = r.nextInt(map.length() - 1);
//			seekY = r.nextInt(map.height() - 1);
//			sedentaryFor = 0;
//		}
		
		this.goUntilWallClockwise();
		
		color = isClearPath(map.getEntity(1)) ? Color.GREEN : Color.RED;
		
//		System.out.println(name + " @ " + x + ", " + y + " hp: " + hitpoints);
		
//		map.updateAt(x, y);
	}

}
