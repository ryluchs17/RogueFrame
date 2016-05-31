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
 * An unhatched dragon enemy for RogueFrame
 * @author Ryan Luchs
 *
 */
public class DragonEgg extends AbstractEntity {
	
	private static final Inventory DRAGON_INVENTORY = new Inventory(new AbstractItem[]{(AbstractItem) new Jaw(), (AbstractItem) new Flame()});
	
	private static final Color[] DRAGON_COLORS = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
	
	private boolean hatched = false;
	
	/**
	 * Creates a new instance at (x, y) at a given level in the given map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the instance
	 * @param map The map the instance exists in
	 */
	public DragonEgg(int x, int y, int level, TileMap map) {
		super(x, y, level, map);

		
		
		this.character = "e";
		this.color = Color.WHITE;
		
		this.name = "Egg";
		this.description = "I wonder what'll hatch?";
		
		this.inventory = DRAGON_INVENTORY;
		
		this.expWorth = 50;
		
		this.regenRate = AbstractEntity.REGEN_RATE_SLOW;
		
		this.grounded = false;
		
		this.ignore = true;
		
		hp_cap = 14;
		str_cap = 14;
		def_cap = 10;
		mag_cap = 14;
		res_cap = 12;
		skl_cap = 8;
		spd_cap = 6;
		
		hp_gro = 65;
		str_gro = 80;
		def_gro = 60;
		mag_gro = 80;
		res_gro = 70;
		skl_gro = 60;
		spd_gro = 60;
		
		levelUp(level);
		
		this.spd = 0;
	}

	/* (non-Javadoc)
	 * @see entity.AbstractEntity#onTurn()
	 */
	@Override
	public void onTurn() {
		
		if(hatched) {
			if(map.player != null && canSee(map.player)) {
				int distance = distance(map.player);
				
				if(distance <= 7 && distance >= 5) {
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
		} else {
			if(map.player != null && isClearPath(map.player) && rng.nextInt(100) <= (10 - distance(map.player))) {
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
				
				this.grounded = false;
				
				this.spd = spd_cap;
				
				hatched = true;
			}
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
