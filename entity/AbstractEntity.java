package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * RougeFrame entity template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractEntity {

	// Char and Color to display as
	protected String character;
	protected Color foreground;
	
	// Flavor text
	protected String name, description;
	
	// Determines whether the mob interacts with solid objects
	protected boolean tangible = true;
	
	// Determines whether or not to display the tile under the mob
	protected boolean visible = true;
	
	// XY position on the grid
	protected int x, y;
	
	// Health
	protected int hitpoints = 0;
	
	// Character Level stuff
	protected int level;
	
	/**
	 * The maximum level an AbstractEntity can be
	 */
	public static final int MAX_LEVEL = 20;
	
	// Stats stuff
	protected int hp, atk, def, mag;
	
	// Base stats stuff
	protected int baseHitpoints, baseAtk, baseDef, baseMag;
	
	// special unchanging damage stat
	protected int bDamage = 0;
	
	public AbstractEntity(int x, int y, int level) {
		this.x = x;
		this.y = y;
		
		if(level > MAX_LEVEL) {
			this.level = MAX_LEVEL;
		} else if(level < 1) {
			this.level = 1;
		} else {
			this.level = level;
		}
	}
	
	public void draw(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(foreground);
		g2d.drawString(character, x, y);
	}
	
//	public void physicalAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * (atk/e.def) * baseDamage; // TODO fix to use float math
//	}
//	
//	public void magicAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * ((mag + e.mag)/(1.75 * e.def)) * baseDamage; // TODO fix to use float math
//	}
	
	public void setStats(boolean fillHitpoints) {
		hp = ((level * baseHitpoints) / (MAX_LEVEL / 2)) + level;
		atk = (level * baseAtk) / MAX_LEVEL + 1;
		def = (level * baseDef) / MAX_LEVEL + 1;
		mag = (level * baseMag) / MAX_LEVEL + 1;
		
		System.out.println(name + " @ " + "(" + x + ", " + y + ")");
		System.out.println("HP : " + hp);
		System.out.println("ATK : " + atk);
		System.out.println("DEF : " + def);
		System.out.println("MAG : " + mag);
		
		if(fillHitpoints) {
			hitpoints = hp;
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	
}
