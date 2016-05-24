/**
 * 
 */
package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import tile.TileMap;

/**
 * @author SJHSStudent
 *
 */
public class Bat extends AbstractEntity {
	
	private static final String BAT_CHAR = "b";
	
	private static final Color BAT_BROWN = new Color(140, 100, 60);
	
	private static final String BAT_NAME = "Bat";
	
	private static final String BAT_DESCRIPTION = "A bat! How cute!";
	
	public Bat(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = BAT_CHAR;
		this.color = BAT_BROWN;
		
		this.name = BAT_NAME;
		this.description = BAT_DESCRIPTION;
		
		grounded = false;
		
//		this.hp_base = 60;
//		this.atk_base = 75;
//		this.def_base = 40;
//		this.mag_base = 30;
//		
//		setStats(true);
		
		hp_gro = 10;
		str_gro = 30;
		def_gro = 30;
		mag_gro = 30;
		res_gro = 30;
		skl_gro = 65;
		spd_gro = 80;
		
		levelUp(level);
		
	}
	
	public void onTurn() {
//		map.updateAt(x, y);
		
		//this.goUntilWallClockwise();
		
		this.randomWalk();
		
		//color = isClearPath(map.getEntity(1)) ? Color.GREEN : Color.RED;
		
//		System.out.println(name + " @ " + x + ", " + y + " hp: " + hitpoints);
		
//		map.updateAt(x, y);
		
	}

	@Override
	public void onAttacked(AttackEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
