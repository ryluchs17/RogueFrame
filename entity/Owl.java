/**
 * 
 */
package entity;

import java.awt.Color;

import item.Claws;
import item.Inventory;
import tile.TileMap;

/**
 * An owl enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Owl extends AbstractEntity {
	
	private static final Inventory OWL_INVENTORY = new Inventory(new Claws());
	
	private static final Color OWL_BROWN = new Color(140, 100, 60);

	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Owl(int x, int y, int level, TileMap map) {
		super(x, y, level, map);

		this.character = "O";
		this.color = OWL_BROWN;
		
		this.name = "Owl";
		this.description = "A mad old owl";
		
		this.inventory = OWL_INVENTORY;
		
		this.expWorth = 10;
		
		grounded = false;
		
		hp_cap = 10;
		str_cap = 8;
		def_cap = 4;
		mag_cap = 2;
		res_cap = 6;
		skl_cap = 8;
		spd_cap = 5;
		
		hp_gro = 40;
		str_gro = 55;
		def_gro = 40;
		mag_gro = 10;
		res_gro = 75;
		skl_gro = 75;
		spd_gro = 40;
		
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
			this.goUntilWallClockwise();
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
