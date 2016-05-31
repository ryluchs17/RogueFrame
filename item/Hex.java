/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A powerful magical weapon for spell casters
 * @author Ryan Luchs
 *
 */
public class Hex extends AbstractItem {

	public Hex() {
		this.character = "*";
		this.color = Color.MAGENTA;
		
		this.name = "Hex";
		this.description = "Wicked magick";
		
		this.stackable = true;
		this.consumable = true;
		
		this.damage = 4;
		this.hit = 60;
		this.crit = 60;
		this.magical = true;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		improve();
		if(m.getInventory().hasEquipt()) {
			m.getInventory().get(0).cursed = true;
		}

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(entity.AbstractEntity)
	 */
	@Override
	public void onThrown(AbstractEntity e) {
		improve();
		if(e.getInventory().hasEquipt()) {
			e.getInventory().get(0).cursed = true;
		}
		
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onEquipt(entity.AbstractEntity)
	 */
	@Override
	public void onEquipt(AbstractEntity m) {
		// TODO Auto-generated method stub

	}

}
