/**
 * 
 */
package entity;

import java.awt.Color;

import item.Inventory;
import item.Proboscis;
import tile.TileMap;

/**
 * A little joke enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Tick extends AbstractEntity {
	
	private static Inventory TICK_INVENTORY = new Inventory(new Proboscis());

	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Tick(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = "t";
		this.color = Color.RED;
		
		this.name = "Tick";
		this.description = "Nigh-invulnerable";
		
		this.inventory = TICK_INVENTORY;
		
		this.expWorth = 5;
		
		hp_cap = 2;
		str_cap = 6;
		def_cap = 20;
		mag_cap = 2;
		res_cap = 4;
		skl_cap = 4;
		spd_cap = 4;
		
		hp_gro = 20;
		str_gro = 40;
		def_gro = 60;
		mag_gro = 10;
		res_gro = 20;
		skl_gro = 30;
		spd_gro = 30;
		
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
