/**
 * 
 */
package entity;

import java.awt.Color;

import item.Fangs;
import item.Inventory;
import tile.TileMap;

/**
 * A spider enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Spider extends AbstractEntity {

	private static Inventory SPIDER_INVENTORY = new Inventory(new Fangs());
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Spider(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = "s";
		this.color = Color.GRAY;
		
		this.name = "Spider";
		this.description = "Not so itsy-bitsy...";
		
		this.inventory = SPIDER_INVENTORY;
		
		this.expWorth = 5;
		
		hp_cap = 2;
		str_cap = 16;
		def_cap = 4;
		mag_cap = 2;
		res_cap = 4;
		skl_cap = 5;
		spd_cap = 4;
		
		hp_gro = 10;
		str_gro = 65;
		def_gro = 20;
		mag_gro = 10;
		res_gro = 20;
		skl_gro = 60;
		spd_gro = 55;
		
		levelUp(level);
	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onTurn()
	 */
	@Override
	public void onTurn() {
		if(map.player != null && isClearPath(map.player)) {
			goTo(map.player);
			this.interact(getTileFacing());
		} else {
			this.randomWalk();
		}

	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onAttacked(entity.AbstractEntity)
	 */
	@Override
	public void onAttacked(AbstractEntity e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onDeath()
	 */
	@Override
	public void onDeath() {
		// TODO Auto-generated method stub

	}

}
