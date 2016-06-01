/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * An item to toggle flying
 * @author Ryan Luchs
 *
 */
public class OrbOfFlight extends AbstractItem {

	public OrbOfFlight() {
		this.character = "o";
		this.color = Color.GRAY;
		
		this.name = "Orb of Flight";
		this.description = "A magical gem that allows flight!";
		
		this.stackable = false;
		
		this.damage = 0;
		this.hit = 0;
		this.crit = 0;
		this.magical = true;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		m.grounded = !m.grounded;
		if(m.grounded) {
			color = Color.GRAY;
		} else {
			color = Color.CYAN;
		}

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(entity.AbstractEntity)
	 */
	@Override
	public void onThrown(AbstractEntity e) {
		e.grounded = !e.grounded;
		if(e.grounded) {
			color = Color.GRAY;
		} else {
			color = Color.CYAN;
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
