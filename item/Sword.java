/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * @author ryan
 *
 */
public class Sword extends AbstractItem {

	public Sword() {
		
		this.character = AbstractItem.CHAR_WEAPON;
		this.color = Color.GRAY;
		
		this.name = "Sword";
		this.description = "A weapon for cutting and thrusting";
		
		this.stackable = false;
		
		this.damage = 6;
		this.hit = 80;
		this.crit = 10;
		this.magical = false;
	}
	
	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		// TODO Auto-generated method stub
		improve();
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(tile.AbstractTile)
	 */
	@Override
	public void onThrown(AbstractTile t) {
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
