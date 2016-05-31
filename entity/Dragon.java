/**
 * 
 */
package entity;

import java.awt.Color;

import item.AbstractItem;
import item.Flame;
import item.Inventory;
import item.Jaw;
import tile.TileMap;

/**
 * A dragon enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class Dragon extends AbstractEntity {
	
	private static final Inventory DRAGON_INVENTORY = new Inventory(new AbstractItem[]{(AbstractItem) new Jaw(), (AbstractItem) new Flame()});
	
	private static final Color[] DRAGON_COLORS = new Color[]{Color.RED, Color.GREEN, Color.BLUE};

	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public Dragon(int x, int y, int level, TileMap map) {
		super(x, y, level, map);

		
		this.color = DRAGON_COLORS[rng.nextInt(3)];
		
		if(color == Color.GREEN && rng.nextBoolean()) {
			this.character = "T";
			this.name = "Trogdor";
			this.description = "The Burninator";
		} else {
			this.character = "D";
			this.name = "Dragon";
			this.description = "Have a nice day! ;)";
		}
		
		this.inventory = DRAGON_INVENTORY;
		
		this.expWorth = 50;
		
		this.regenRate = AbstractEntity.REGEN_RATE_SLOW;
		
		this.grounded = true;
		
		this.ignore = true;
		
		hp_cap = 14;
		str_cap = 14;
		def_cap = 10;
		mag_cap = 14;
		res_cap = 12;
		skl_cap = 8;
		spd_cap = 6;
		
		hp_gro = 60;
		str_gro = 75;
		def_gro = 55;
		mag_gro = 75;
		res_gro = 65;
		skl_gro = 55;
		spd_gro = 55;
		
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
				goTo(map.player);
				this.interact(getTileFacing());
			}
			
		} else {
			if(map.getRounds() % 2 == 0) this.randomWalk();
		}
		
		if(map.tileAt(x, y).getName().equals("Magma")) {
			hp += 10;
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
