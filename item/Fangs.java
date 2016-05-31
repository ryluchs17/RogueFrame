/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * A weak piercing weapon for enemies
 * @author Ryan Luchs
 *
 */
public class Fangs extends AbstractItem {
	
	/**
	 * 
	 */
	public Fangs() {
		
		this.character = "\"";
		this.color = Color.WHITE;
		
		this.name = "Fangs";
		this.description = "Razor sharp piercing teeth";
		
		this.stackable = false;
		
		this.damage = 12;
		this.hit = 70;
		this.crit = 5;
		this.magical = false;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		improve();
		
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(tile.AbstractTile)
	 */
	@Override
	public void onThrown(AbstractEntity e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onEquipt(entity.AbstractEntity)
	 */
	@Override
	public void onEquipt(AbstractEntity m) {
		// TODO Auto-generated method stub

	}

}
