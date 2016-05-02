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
	
	// Determines whether the entity actually makes contact with the tile below it
	public boolean grounded = true;
	
	// Determines whether or not the entity ignores "avoid" tiles
	public boolean ignore = false;

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
	
//	protected int str, def, mag, res, skl, spd, dp, dm;
//	protected int m_str, m_def, m_mag, m_res, m_skl, m_spd;
	
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
	 * The AbtractEntity's action each turn
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
		
//		g2d.setColor(Color.CYAN);
//		switch(facing) {
//			case NORTH:
//				g2d.drawLine(x + AbstractTile.STEP/2, y, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case SOUTH:
//				g2d.drawLine(x + AbstractTile.STEP/2, y + AbstractTile.STEP, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case EAST:
//				g2d.drawLine(x + AbstractTile.STEP, y + AbstractTile.STEP/2, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case WEST:
//				g2d.drawLine(x, y + AbstractTile.STEP/2, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case NORTHEAST:
//				g2d.drawLine(x + AbstractTile.STEP, y, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case NORTHWEST:
//				g2d.drawLine(x, y, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case SOUTHEAST:
//				g2d.drawLine(x + AbstractTile.STEP, y + AbstractTile.STEP, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			case SOUTHWEST:
//				g2d.drawLine(x, y + AbstractTile.STEP, x + AbstractTile.STEP/2, y + AbstractTile.STEP/2);
//				break;
//			default:
//				break;
//		}
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
	
	public int distance(int x, int y) {
		return (int) Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
	}
	
	public int distance(AbstractEntity e) {
		return distance(e.getX(), e.getY());
	}
	
	public int distance(AbstractTile t) {
		return distance(t.getX(), t.getY());
	}
	
	public boolean isClearPath(int x, int y) {
		//System.out.println("()");
		
		if(x == this.x) {
			// whether this is above the target
			if(y < this.y) {
				for(int i = this.y - 1; i > y; i--) {
					map.tileAt(this.x, i).setCovered(true);
					if(!map.tileAt(this.x, i).canEnter(this)) return false;
				}
			} else {
				for(int i = this.y + 1; i < y; i++) {
					map.tileAt(this.x, i).setCovered(true);
					if(!map.tileAt(this.x, i).canEnter(this)) return false;
				}
			} 
		} else if(y == this.y) {
			// whether this right of the target
			if(x < this.x) {
				for(int i = this.x - 1; i > y; i--) {
					map.tileAt(i, this.y).setCovered(true);
					if(!map.tileAt(i, this.y).canEnter(this)) return false;
				}
			} else {
				for(int i = this.x + 1; i < y; i++) {
					map.tileAt(i, this.y).setCovered(true);
					if(!map.tileAt(i, this.y).canEnter(this)) return false;
				}
			}
		} else if(Math.abs(this.x - x) >= Math.abs(this.y - y)) {
			float m = (this.y - (float) y)/(this.x - (float) x);
			
			float b = y - m*x;
			
			//System.out.println("y = " + m + "x + " + b);
			
			// whether this is left of the target
			if(x < this.x) {
				for(int i = this.x - 1; i > x; i--) {
					//System.out.println("(" + i + " ," + (int) (m*i + b) + ")");
					map.tileAt(i, (int) (m*i + b)).setCovered(true);
					if(!map.tileAt(i, (int) (m*i + b)).canEnter(this)) return false;
				}
			} else {
				for(int i = this.x + 1; i < x; i++) {
					//System.out.println("(" + i + " ," + (int) (m*i + b) + ")");
					map.tileAt(i, (int) (m*i + b)).setCovered(true);
					if(!map.tileAt(i, (int) (m*i + b)).canEnter(this)) return false;
				}
			}
		} else {
			float m = (this.x - (float) x)/(this.y - (float) y);
			
			float b = x - m*y;
			
			//System.out.println("x = " + m + "y + " + b);
			
			// whether this is above the target
			if(y < this.y) {
				for(int i = this.y - 1; i > y; i--) {
					//System.out.println("(" + (int) (m*i + b) + " ," + i + ")");
					map.tileAt((int) (m*i + b), i).setCovered(true);
					if(!map.tileAt((int) (m*i + b), i).canEnter(this)) return false;
				}
			} else {
				for(int i = this.y + 1; i < y; i++) {
					//System.out.println("(" + (int) (m*i + b) + " ," + i + ")");
					map.tileAt((int) (m*i + b), i).setCovered(true);
					if(!map.tileAt((int) (m*i + b), i).canEnter(this)) return false;
				}
			}
		}
		return true;
	}
	
	public boolean isClearPath(AbstractEntity e) {
		return isClearPath(e.getX(), e.getY());
	}
	
	public boolean isClearPath(AbstractTile t) {
		return isClearPath(t.getX(), t.getY());
	}
	
	public boolean goNorth() {
		if(y - 1 > 0) {
			if(map.tileAt(x, y - 1).canEnter(this)) {
				y -= 1;
				
				facing = NORTH;
				return true;
			}	
		}
		return false;
	}
	
	public boolean goSouth() {
		if(y + 1 < map.height()) {
			if(map.tileAt(x, y + 1).canEnter(this)) {
				y += 1;
				
				facing = SOUTH;
				return true;
			}	
		}
		return false;
	}
	
	public boolean goEast() {
		if(x + 1 > 0) {
			if(map.tileAt(x + 1, y).canEnter(this)) {
				x += 1;
				
				facing = EAST;
				return true;
			}	
		}
		return false;
	}
	
	public boolean goWest() {
		if(x - 1 > 0) {
			if(map.tileAt(x - 1, y).canEnter(this)) {
				x -= 1;
				
				facing = WEST;
				return true;
			}	
		}
		return false;
	}
	
	public boolean goNortheast() {
		if(map.contains(x + 1, y - 1)) {
			if(map.tileAt(x + 1, y - 1).canEnter(this)) {
				x += 1;
				y -= 1;
				
				facing = NORTHEAST;
				return true;
			}
		}
		return false;
	}
	
	public boolean goNorthwest() {
		if(map.contains(x - 1, y - 1)) {
			if(map.tileAt(x - 1, y - 1).canEnter(this)) {
				this.x -= 1;
				this.y -= 1;
				
				facing = NORTHWEST;
				return true;
			}
		}
		return false;
	}
	
	public boolean goSoutheast() {
		if(map.contains(x + 1, y + 1)) {
			if(map.tileAt(x + 1, y + 1).canEnter(this)) {
				x += 1;
				y += 1;
				
				facing = SOUTHEAST;
				return true;
			}
		}
		return false;
	}
	
	public boolean goSouthwest() {
		if(map.contains(x - 1, y + 1)) {
			if(map.tileAt(x - 1, y + 1).canEnter(this)) {
				x -= 1;
				y += 1;
				
				facing = SOUTHWEST;
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Moves this AbstractEntity one space toward the given point on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if the AbstractEntity has moved
	 */
	public boolean goTo(int x, int y) {
		boolean moved = false;
		
		map.tileAt(this.x, this.y).setOccupant(null);
		
		if(this.x < x && this.y > y) {
			if(goNortheast()) moved = true;
		} else if(this.x > x && this.y > y) {
			if(goNorthwest()) moved = true;
		} else if(this.x < x && this.y < y) {
			if(goSoutheast()) moved = true;
		} else if(this.x > x && this.y < y) {
			if(goSouthwest()) moved = true;
		} else if(this.y > y){
			if(goNorth()) moved = true;
		} else if(this.y < y){
			if(goSouth()) moved = true;
		} else if(this.x < x) {
			if(goEast()) moved = true;
		} else if(this.x > x){
			if(goWest()) moved = true;
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
		
		if(this.x < x && this.y > y) {
			if(goSouthwest()) moved = true;
		} else if(this.x > x && this.y > y) {
			if(goSoutheast()) moved = true;
		} else if(this.x < x && this.y < y) {
			if(goNorthwest()) moved = true;
		} else if(this.x > x && this.y < y) {
			if(goNortheast()) moved = true;
		} else if(this.y > y){
			if(goSouth()) moved = true;
		} else if(this.y < y){
			if(goNorth()) moved = true;
		} else if(this.x < x) {
			if(goWest()) moved = true;
		} else if(this.x > x){
			if(goEast()) moved = true;
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		
		return moved;
	}
	
	public void goUntilWallClockwise() {
		map.tileAt(x, y).setOccupant(null);
		
		switch(facing) {
			case NORTH:
				if(!goNorth()) facing = NORTHEAST;
				break;
			case SOUTH:
				if(!goSouth()) facing = SOUTHWEST;
				break;
			case EAST:
				if(!goEast()) facing = SOUTHEAST;
				break;
			case WEST:
				if(!goWest()) facing = NORTHWEST;
				break;
			case NORTHEAST:
				if(!goNortheast()) facing = EAST;
				break;
			case NORTHWEST:
				if(!goNorthwest()) facing = NORTH;
				break;
			case SOUTHEAST:
				if(!goSoutheast()) facing = SOUTH;
				break;
			case SOUTHWEST:
				if(!goSouthwest()) facing = WEST;
				break;
			default:
				break;
		}
		
		map.tileAt(x, y).setOccupant(this);
	}
	
	public void goUntilWallCounterclockwise() {
		map.tileAt(x, y).setOccupant(null);
		
		switch(facing) {
			case NORTH:
				if(!goNorth()) facing = NORTHWEST;
				break;
			case SOUTH:
				if(!goSouth()) facing = SOUTHEAST;
				break;
			case EAST:
				if(!goEast()) facing = NORTHEAST;
				break;
			case WEST:
				if(!goWest()) facing = SOUTHWEST;
				break;
			case NORTHEAST:
				if(!goNortheast()) facing = NORTH;
				break;
			case NORTHWEST:
				if(!goNorthwest()) facing = WEST;
				break;
			case SOUTHEAST:
				if(!goSoutheast()) facing = EAST;
				break;
			case SOUTHWEST:
				if(!goSouthwest()) facing = SOUTH;
				break;
			default:
				break;
		}
		
		map.tileAt(x, y).setOccupant(this);
	}
	
	public void goUntilWallRight() {
		map.tileAt(x, y).setOccupant(null);
		
		switch(facing) {
			case NORTH:
				if(!goNorth()) facing = EAST;
				break;
			case SOUTH:
				if(!goSouth()) facing = WEST;
				break;
			case EAST:
				if(!goEast()) facing = SOUTH;
				break;
			case WEST:
				if(!goWest()) facing = NORTH;
				break;
			default:
				break;
		}
		
		map.tileAt(x, y).setOccupant(this);
	}
	
	public void goUntilWallLeft() {
		map.tileAt(x, y).setOccupant(null);
		
		switch(facing) {
			case NORTH:
				if(!goNorth()) facing = WEST;
				break;
			case SOUTH:
				if(!goSouth()) facing = EAST;
				break;
			case EAST:
				if(!goEast()) facing = NORTH;
				break;
			case WEST:
				if(!goWest()) facing = SOUTH;
				break;
			default:
				break;
		}
		
		map.tileAt(x, y).setOccupant(this);
	}
	
}
