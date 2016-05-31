/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * An annoying exp draining weapon for annoying enemies
 * @author Ryan Luchs
 *
 */
public class Proboscis extends AbstractItem {

	public Proboscis() {
		this.character = "/";
		this.color = Color.RED;
		
		this.name = "Proboscis";
		this.description = "You won't even feel it";
		
		this.stackable = false;
		
		this.damage = 8;
		this.hit = 80;
		this.crit = 0;
		this.magical = false;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		if(m.experience > 0) {
			m.experience -= damage/2;
		}

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(entity.AbstractEntity)
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
