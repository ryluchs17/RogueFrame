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
public class Fangs extends AbstractItem {

	private static final String CHAR_FANGS = "\"";
	
	/**
	 * 
	 */
	public Fangs() {
		
		this.character = CHAR_FANGS;
		this.color = Color.WHITE;
		
		this.name = "Fangs";
		this.description = "Razor sharp piercing teeth";
		
		this.stackable = false;
		
		this.damage = 4;
		this.hit = 70;
		this.crit = 5;
		this.magical = false;
		
		this.max_proficiency = 2;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		this.improve();
		
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
