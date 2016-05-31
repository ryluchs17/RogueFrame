/**
 * 
 */
package item;

import java.awt.Color;

import entity.AbstractEntity;

/**
 * A cheap consumable ammo item for RogueFrame
 * @author Ryan Luchs
 *
 */
public class ObsidianShard extends AbstractItem {

	private static final Color OBSIDIAN_COLOR = Color.MAGENTA.darker().darker();
	
	public ObsidianShard() {
		
		this.character = "'";
		this.color = OBSIDIAN_COLOR;
		
		this.name = "Obsidian Shard";
		this.description = "Broken lava glass";
		
		this.stackable = true;
		this.consumable = true;
		
		this.damage = 12;
		this.hit = 65;
		this.crit = 15;
		this.magical = false;
	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onUse(entity.AbstractEntity)
	 */
	@Override
	public void onUse(AbstractEntity m) {
		improve();
		uses--;

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onThrown(entity.AbstractEntity)
	 */
	@Override
	public void onThrown(AbstractEntity e) {
		improve();
		uses--;

	}

	/* (non-Javadoc)
	 * @see item.AbstractItem#onEquipt(entity.AbstractEntity)
	 */
	@Override
	public void onEquipt(AbstractEntity m) {
		// TODO Auto-generated method stub

	}

}
