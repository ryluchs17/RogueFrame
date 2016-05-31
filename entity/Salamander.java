/**
 * 
 */
package entity;

import java.awt.Color;

import item.Jaw;
import item.Inventory;
import tile.TileMap;

/**
 * A mix between real and mythical salamanders as an enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Salamander extends AbstractEntity {
	
	private static final Inventory SALAMANDER_INVENTORY = new Inventory(new Jaw());

	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Salamander(int x, int y, int level, TileMap map) {
		super(x, y, level, map);

		this.character = "s";
		this.color = Color.ORANGE;
		
		this.name = "Salamander";
		this.description = "A small fiery beast";
		
		this.inventory = SALAMANDER_INVENTORY;
		
		this.expWorth = 20;
		
		this.regenRate = AbstractEntity.REGEN_RATE_SLOW;
		
		hp_cap = 12;
		str_cap = 10;
		def_cap = 6;
		mag_cap = 2;
		res_cap = 8;
		skl_cap = 4;
		spd_cap = 4;
		
		hp_gro = 40;
		str_gro = 55;
		def_gro = 55;
		mag_gro = 40;
		res_gro = 65;
		skl_gro = 40;
		spd_gro = 40;
		
		levelUp(level);
	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onTurn()
	 */
	@Override
	public void onTurn() {
		if(map.player != null && distance(map.player) <= 6 && isClearPath(map.player)) {
			goTo(map.player);
			this.interact(getTileFacing());
		} else {
			if(map.getRounds() % 2 == 0) this.randomWalk();
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
