/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * A slashing weapon for tough clawed enemies
 * @author Ryan Luchs
 *
 */
public class Claws extends AbstractItem {

	public Claws() {
		this.character = "/";
		this.color = Color.WHITE;
		
		this.name = "Claws";
		this.description = "Wicked rending claws";
		
		this.stackable = false;
		
		this.damage = 20;
		this.hit = 85;
		this.crit = 10;
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
