/**
 * 
 */
package entity;

import java.awt.Color;

import item.AbstractItem;
import item.Flame;
import item.Inventory;
import tile.TileMap;

/**
 * A fiery enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class FireElemental extends AbstractEntity {

	private static final Inventory FIRE_INVENTORY = new Inventory(new AbstractItem[] {null, (AbstractItem) new Flame()});
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public FireElemental(int x, int y, int level, TileMap map) {
		super(x, y, level, map);

		this.character = "E";
		this.color = Color.RED;
		
		this.name = "Fire Elemental";
		this.description = "Immolation incarnate";
		
		this.inventory = FIRE_INVENTORY;
		
		this.expWorth = 35;
		
		grounded = false;
		
		ignore = true;
		
		hp_cap = 8;
		str_cap = 0;
		def_cap = 0;
		mag_cap = 14;
		res_cap = 18;
		skl_cap = 8;
		spd_cap = 6;
		
		hp_gro = 20;
		str_gro = 0;
		def_gro = 0;
		mag_gro = 65;
		res_gro = 75;
		skl_gro = 55;
		spd_gro = 45;
		
		levelUp(level);
	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onTurn()
	 */
	@Override
	public void onTurn() {
		if(map.player != null && canSee(map.player)) {
			int distance = distance(map.player);
			
			if(distance <= 8 && distance >= 5) {
				throwItem(1, map.tileAt(map.player.getX(), map.player.getY()));
			}
			if(distance < 5) {
				avoid(map.player);
				throwItem(1, map.tileAt(map.player.getX(), map.player.getY()));
			}
			
		} else {
			if(rng.nextInt(100) <= 80) {
				this.randomWalk();
			} else {
				this.randomTeleport();
			}
		}
		
		if(map.tileAt(x, y).getName().equals("Magma")) {
			hp += 10;
			experience += 5;
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
