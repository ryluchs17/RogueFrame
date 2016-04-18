package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tile.AbstractTile;
import tile.TileMap;

/**
 * RougeFrame entity template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractEntity {

	// XY position on the grid
	protected int x, y;
	
	// The map within which this AbstractEntity exists
	protected TileMap map;
	
	// Char and Color to display as
	protected String character;
	protected Color foreground;
	
	// Flavor text
	protected String name, description;
	
	// Determines whether the mob interacts with solid objects
	protected boolean tangible = true;
	
	// Determines whether or not to display the tile under the mob
	protected boolean visible = true;	

	// ! STATS STUFF BEGINS !
	
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
	protected int hp_base, atk_base, def_base, mag_base;
	
	// Stat multipliers
	protected short hp_stage = 0, atk_stage = 0, def_stage = 0, mag_stage = 0;
	
	// base damage with physical and magical attacks
	protected int base_physical_damage = 0, base_magic_damage = 0;
	
	// ! STATS STUFF ENDS !
	
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
	
	// ! ABSTRACT METHODS BEGIN !
	
	/**
	 * The AbtractEntitie's action each turn
	 */
	abstract public void onTurn();
	
	// ! ABSTRACT METHODS END !
	
	public void draw(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(foreground);
		g2d.drawString(character, x, y + AbstractTile.STEP);
	}
	
//	public void physicalAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * (atk/e.def) * baseDamage; // TODO fix to use float math
//	}
//	
//	public void magicAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * ((mag + e.mag)/(1.75 * e.def)) * baseDamage; // TODO fix to use float math
//	}
	
	public void setStats(boolean fillHitpoints) {
		hp = (int) ((((level * hp_base) / (MAX_LEVEL / 2)) + level) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
		
		atk = (int) (((level * atk_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
		def = (int) (((level * def_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
		mag = (int) (((level * mag_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
		
//		System.out.println(name + " @ " + "(" + x + ", " + y + ")");
//		System.out.println("HP : " + hp);
//		System.out.println("ATK : " + atk);
//		System.out.println("DEF : " + def);
//		System.out.println("MAG : " + mag);
		
		if(fillHitpoints) {
			hitpoints = hp;
		}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setMap(TileMap map) {
		this.map = map;
	}
	
	public void goTo(int x, int y) {
		if(this.x < x && this.x + 1 < map.length()) this.x += 1;
		if(this.x > x && this.x - 1 > 0) this.x -= 1;
		if(this.y < y && this.y + 1 < map.height()) this.y += 1;
		if(this.y > y && this.y - 1 > 0) this.y -= 1;
	}
	
	public void goFrom(int x, int y) {
		if(this.x < x && this.x - 1 > 0) this.x -= 1;
		if(this.x > x && this.x + 1 < map.length()) this.x += 1;
		if(this.y < y && this.y - 1 > 0) this.y -= 1;
		if(this.y > y && this.y + 1 < map.height()) this.y += 1;
	}
	
	
	
}
