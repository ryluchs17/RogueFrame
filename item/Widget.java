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
public class Widget extends AbstractItem {

	/**
	 * 
	 */
	public Widget() {
		itemID = 0;
		
		this.character = CHAR_MISC;
		this.color = Color.WHITE;
		
		this.name = "Widget";
		this.description = "TEST ITEM!";
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
