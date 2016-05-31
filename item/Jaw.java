/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * A crushing weapon for strong jawed enemies
 * @author Ryan Luchs
 *
 */
public class Jaw extends AbstractItem {

	public Jaw() {
		
		this.character = "}";
		this.color = Color.WHITE;
		
		this.name = "Jaw";
		this.description = "Powerful crushing jaws";
		
		this.stackable = false;
		
		this.damage = 24;
		this.hit = 60;
		this.crit = 15;
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
