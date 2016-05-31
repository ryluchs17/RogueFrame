/**
 * 
 */
package entity;

import java.awt.Color;

import item.*;
import tile.TileMap;

/**
 * A giant bat enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class GiantBat extends AbstractEntity {
	
	private static final Color BAT_BROWN = new Color(140, 100, 60);
	
	private static final Inventory BAT_INVENTORY = new Inventory(new Fangs());
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public GiantBat(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = "B";
		this.color = BAT_BROWN;
		
		this.name = "Giant Bat";
		this.description = "It's still cute...";
		
		this.inventory = BAT_INVENTORY;
		
		this.expWorth = 10;
		
		grounded = false;
		
		hp_cap = 6;
		str_cap = 4;
		def_cap = 4;
		mag_cap = 2;
		res_cap = 4;
		skl_cap = 14;
		spd_cap = 12;
		
		hp_gro = 20;
		str_gro = 45;
		def_gro = 45;
		mag_gro = 30;
		res_gro = 45;
		skl_gro = 75;
		spd_gro = 85;
		
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
