/**
 * 
 */
package entity;

import java.awt.Color;

import item.*;
import tile.TileMap;

/**
 * A bat enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Bat extends AbstractEntity {
	
	private static final Color BAT_BROWN = new Color(140, 100, 60);
	
	private static final Inventory BAT_INVENTORY = new Inventory(new Fangs());
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Bat(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = "b";
		this.color = BAT_BROWN;
		
		this.name = "Bat";
		this.description = "A bat! How cute!";
		
		this.inventory = BAT_INVENTORY;
		
		this.expWorth = 5;
		
		grounded = false;
		
		hp_cap = 6;
		str_cap = 2;
		def_cap = 2;
		mag_cap = 2;
		res_cap = 2;
		skl_cap = 7;
		spd_cap = 6;
		
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
		
		//this.randomWalk();
		//this.attackAdjacent();
		//this.goUntilWallClockwise();
		
		this.randomWalk();
		this.attackAdjacent();
		
		//color = isClearPath(map.getEntity(1)) ? Color.GREEN : Color.RED;
		
//		System.out.println(name + " @ " + x + ", " + y + " hp: " + hitpoints);
		
//		map.updateAt(x, y);
		
	}

	@Override
	public void onAttacked(AbstractEntity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath() {
		// TODO Auto-generated method stub
		
	}

}
