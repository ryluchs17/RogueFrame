/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * A magical weapon for dragons and fire elementals
 * @author Ryan Luchs
 *
 */
public class Flame extends AbstractItem {

	public Flame() {
		this.character = "*";
		this.color = Color.ORANGE;
		
		this.name = "Flame";
		this.description = "Don't try this at home";
		
		this.stackable = true;
		this.consumable = true;
		
		this.damage = 32;
		this.hit = 40;
		this.crit = 0;
		this.magical = true;
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
		improve();

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onEquipt(entity.AbstractEntity)
	 */
	@Override
	public void onEquipt(AbstractEntity m) {
		// TODO Auto-generated method stub

	}

}
