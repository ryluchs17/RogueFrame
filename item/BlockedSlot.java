/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;
import tile.AbstractTile;

/**
 * @author SJHSStudent
 *
 */
public class BlockedSlot extends AbstractItem {

	/**
	 * 
	 */
	public BlockedSlot() {
		
		this.character = "X";
		this.color = Color.RED;
		
		this.name = "Blocked";
		this.description = "You can't store anything here.";
		
		this.stackable = false;
		this.cursed = true;
		this.locked = true;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		// TODO Auto-generated method stub

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

