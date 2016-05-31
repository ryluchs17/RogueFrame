/**
 * 
 */
package entity;

import java.awt.Color;

import item.AbstractItem;
import item.Claws;
import item.Inventory;
import tile.TileMap;

/**
 * A Bear enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Bear extends AbstractEntity {
	
	private static final Color BEAR_BROWN = new Color(140, 100, 60);
	
	private static final Inventory BEAR_INVENTORY = new Inventory(new Claws());
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Bear(int x, int y, int level, TileMap map) {
		super(x, y, level, map);
		
		this.character = "B";
		this.color = BEAR_BROWN;
		
		this.name = "Bear";
		this.description = "A surly old bear";
		
		this.inventory = BEAR_INVENTORY;
		
		this.expWorth = 20;
		
		hp_cap = 14;
		str_cap = 12;
		def_cap = 10;
		mag_cap = 0;
		res_cap = 5;
		skl_cap = 5;
		spd_cap = 5;
		
		hp_gro = 60;
		str_gro = 65;
		def_gro = 65;
		mag_gro = 0;
		res_gro = 20;
		skl_gro = 40;
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
			this.goUntilWallCounterclockwise();
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
