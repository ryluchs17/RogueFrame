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
	
	public static final short NORTH = 0;
	public static final short SOUTH = 1;
	public static final short EAST  = 2;
	public static final short WEST  = 3;
	
	public static final short NORTHEAST = 4;
	public static final short NORTHWEST = 5;
	public static final short SOUTHEAST = 6;
	public static final short SOUTHWEST = 7;
	
	// Direction the entity is facing
	protected short facing = NORTH;
	
	// The map within which this AbstractEntity exists
	protected TileMap map;
	
	// Char and Color to display as
	protected String character;
	protected Color color;
	
	// Flavor text
	protected String name, description;
	
	// Determines whether or not to display the tile under the mob
	public boolean visible = true;	
	
	// Determines whether the enitity actually makes contact with the tile below it
	public boolean grounded = true;

	// ! STATS STUFF BEGINS !
	
	// Health
	protected int hitpoints = 0;
	protected boolean dead = false;
	
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
	
	/**
	 * Creates a new AbstractEntity at (x, y) at the given level
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the AbstractEntity
	 */
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
	
	/**
	 * Draw the AbstractEntity to the screen
	 * @param g The graphics object to draw to
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public void draw(Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(color);
		g2d.drawString(character, x, y + AbstractTile.STEP);
	}
	
	/**
	 * Gets the name of this AbstractEnity
	 * @return The name of this AbstractEntity
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the description of this AbstractEntity
	 * @return The name of this AbstractEnity
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Adds the given number to the hitpoints of this AbstractEntity
	 * The number will not go over the max, if it goes under zero the AbstractEntity is tagged as dead
	 * @param hitpoints
	 */
	public void addHitpoints(int hitpoints) {
		if(this.hitpoints + hitpoints > hp) {
			hitpoints = hp;
		} else {
			this.hitpoints += hitpoints;
		}
		
		if(this.hitpoints <= 0) {
			dead = true;
		}
	}
	
	/**
	 * Returns whether this AbstractEntity is dead
	 * @return True if dead
	 */
	public boolean isDead() {
		return dead;
	}
	
//	public void physicalAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * (atk/e.def) * baseDamage; // TODO fix to use float math
//	}
//	
//	public void magicAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * ((mag + e.mag)/(1.75 * e.def)) * baseDamage; // TODO fix to use float math
//	}
	
	/**
	 * Recalculated the stats of this AbstractEntity
	 * @param fillHitpoints True if current hitpoints should be set to max after stats are calculated
	 */
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
	
	/**
	 * Kills the AbstractEntity
	 */
	public void kill() {
		dead = true;
	}
	
	/**
	 * Get the x-coordinate of this AbstractEntity
	 * @return The x-coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the y-coordinate of this AbstractEntity
	 * @return The y-coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the TileMap object in which this AbstractEntity exists
	 * Must be set before an AbstractEntity can exist on-screen
	 * @param map
	 */
	public void setMap(TileMap map) {
		this.map = map;
	}
	
//	public boolean goLeft() {
//		if(x - 1 > 0) {
//			if(map.tileAt(x, y).canEnter()) {
//				x -= 1;
//				return true;
//			}	
//		}
//		return false;
//	}
//	
//	public boolean goRight() {
//		if(x + 1 > 0) {
//			if(map.tileAt(x, y).canEnter()) {
//				x += 1;
//				return true;
//			}	
//		}
//		return false;
//	}
//	
//	public boolean goUp() {
//		if(y - 1 > 0) {
//			if(map.tileAt(x, y).canEnter()) {
//				x -= 1;
//				return true;
//			}	
//		}
//		return false;
//	}
//	
//	public boolean goDown() {
//		if(y + 1 < map.height()) {
//			if(map.tileAt(x, y).canEnter()) {
//				y += 1;
//				return true;
//			}	
//		}
//		return false;
//	}
	
	/**
	 * Moves this AbstractEntity one space toward the given point on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if the AbstractEntity has moved
	 */
	public boolean goTo(int x, int y) {
		boolean moved = false;
		
		map.tileAt(this.x, this.y).setOccupant(null);
		
		if(this.x > x && this.x - 1 > 0){
			if(map.tileAt(this.x - 1, this.y).canEnter()) {
				this.x -= 1;
				moved = true;
				
				facing = NORTH;
			}
		}
		if(this.x < x && this.x + 1 < map.length()) {
			if(map.tileAt(this.x + 1, this.y).canEnter()) {
				this.x += 1;
				moved = true;
				
				facing = SOUTH;
			}
		}
		if(this.y < y && this.y + 1 < map.height()){
			if(map.tileAt(this.x, this.y + 1).canEnter()) {
				this.y += 1;
				moved = true;
				
				if(moved) {
					if(facing == NORTH) {
						facing = NORTHEAST;
					}
					if(facing == SOUTH) {
						facing = SOUTHEAST;
					}
				} else {
					facing = EAST;
				}
			}
		}
		if(this.y > y && this.y - 1 > 0){
			if(map.tileAt(this.x, this.y - 1).canEnter()) {
				this.y -= 1;
				moved = true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		
		return moved;
	}
	
	/**
	 * Moves this AbstractEntity one space away from the given point on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public boolean avoid(int x, int y) {
//		if(this.x < x && this.x - 1 > 0) this.x -= 1;
//		if(this.x > x && this.x + 1 < map.length()) this.x += 1;
//		if(this.y < y && this.y - 1 > 0) this.y -= 1;
//		if(this.y > y && this.y + 1 < map.height()) this.y += 1;
		
		boolean moved = false;
		
		map.tileAt(this.x, this.y).setOccupant(null);
		
		if(this.x > x && this.x + 1 < map.length()) {
			if(map.tileAt(this.x + 1, this.y).canEnter()) {
				this.x += 1;
				moved = true;
			}
		}
		if(this.x < x && this.x - 1 > 0){
			if(map.tileAt(this.x - 1, this.y).canEnter()) {
				this.x -= 1;
				moved = true;
			}
		}
		if(this.y > y && this.y + 1 < map.height()){
			if(map.tileAt(this.x, this.y + 1).canEnter()) {
				this.y += 1;
				moved = true;
			}
		}
		if(this.y < y && this.y - 1 > 0){
			if(map.tileAt(this.x, this.y - 1).canEnter()) {
				this.y -= 1;
				moved = true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		
		return moved;
	}
	
	
	
}
