package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import item.Inventory;
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
	
	// The inventory that this AbstractEntity possesses
	protected Inventory inventory;
	
	// The rng used by this AbstractEntity
	protected Random rng;
	
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
	
	// ! TYPE STUFF !
	
//	/*
//	 * Type is the product of several primes,
//	 * if faction % (number) is 0 then the creature is
//	 * part of that faction 
//	 */
	
	public static boolean FACTION_HOSTILE = false;
	public static boolean FACTION_PLAYER_OR_ALLY = true;
	
	protected boolean faction = FACTION_HOSTILE;

	// ! STATS STUFF !
	
	// Character Level stuff
	protected int level;
	
	/**
	 * The maximum level an AbstractEntity can be
	 */
	public static final int MAX_LEVEL = 20;
	
	// ! MORE STATS STUFF BEGINS !

//	protected int hp, atk, def, mag;
//	
//	// Base stats stuff
//	protected int hp_base, atk_base, def_base, mag_base;
//	
//	// Stat multipliers
//	protected short hp_stage = 0, atk_stage = 0, def_stage = 0, mag_stage = 0;
//	
//	// base damage with physical and magical attacks
//	protected int base_physical_damage = 0, base_magic_damage = 0;
	
	protected int hp_cap, str_cap, def_cap, mag_cap, res_cap, skl_cap, spd_cap;
	
	protected int hp_gro, str_gro, def_gro, mag_gro, res_gro, skl_gro, spd_gro;
	
	public int hp, str, def, mag, res, skl, spd;
	
	public int dam_str = 0, dam_mag = 0;
	
	// ! STATS STUFF ENDS !
	
	/**
	 * Creates a new AbstractEntity at (x, y) at the given level
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the AbstractEntity
	 */
	public AbstractEntity(int x, int y, int level, TileMap map) {
		this.x = x;
		this.y = y;
		
		this.map = map;
		
		this.rng = map.getRNG();
		
//		if(level > MAX_LEVEL) {
//			this.level = MAX_LEVEL;
//		} else if(level < 1) {
//			this.level = 1;
//		} else {
//			this.level = level;
//		}
	}
	
	// ! ABSTRACT METHODS BEGIN !
	
	/**
	 * The AbstractEntity's action each turn
	 */
	abstract public void onTurn();
	
//	/**
//	 * What to do when attacking
//	 */
//	abstract public void onAttacks(AbstractEntity e);
	
	/**
	 * What to do when attacked
	 */
	abstract public void onAttacked(AttackEvent e);
	
	/**
	 * What to do when killed
	 */
	abstract public void onDeath();
	
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
		g2d.drawString(character, x, y + (int) (AbstractTile.STEP * 0.75));
		
		if(hp < hp_cap && hp > 0) {
			g2d.setColor(Color.GREEN);
			g2d.drawLine(x, y + AbstractTile.STEP - 1, x  + (AbstractTile.STEP * hp)/hp_cap, y + AbstractTile.STEP - 1);
		}
		
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
	 * Sets the TileMap object in which this AbstractEntity exists
	 * Must be set before an AbstractEntity can exist on-screen
	 * @param map
	 */
	public void setMap(TileMap map) {
		this.map = map;
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
//	/**
//	 * Adds the given number to the hitpoints of this AbstractEntity
//	 * The number will not go over the max, if it goes under zero the AbstractEntity is tagged as dead
//	 * @param hitpoints
//	 */
//	public void addHitpoints(int hitpoints) {
//		if(this.hitpoints + hitpoints > hp) {
//			hitpoints = hp;
//		} else {
//			this.hitpoints += hitpoints;
//		}
//		
//		if(this.hitpoints <= 0) {
//			dead = true;
//		}
//	}
	
//	
//	public void physicalAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * (atk/e.def) * baseDamage; // TODO fix to use float math
//	}
//	
//	public void magicAttack(AbstractEntity e, int baseDamage) {
//		e.hitpoints -= (level/MAX_LEVEL) * ((mag + e.mag)/(1.75 * e.def)) * baseDamage; // TODO fix to use float math
//	}
	
//	/**
//	 * Recalculated the stats of this AbstractEntity
//	 * @param fillHitpoints True if current hitpoints should be set to max after stats are calculated
//	 */
//	public void setStats(boolean fillHitpoints) {
//		hp = (int) ((((level * hp_base) / (MAX_LEVEL / 2)) + level) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
//		
//		atk = (int) (((level * atk_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
//		def = (int) (((level * def_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
//		mag = (int) (((level * mag_base) / MAX_LEVEL + 1) * (1 + (hp_stage > 0 ? 0.125 * hp_stage : 0.0625 * hp_stage)));
//		
////		System.out.println(name + " @ " + "(" + x + ", " + y + ")");
////		System.out.println("HP : " + hp);
////		System.out.println("ATK : " + atk);
////		System.out.println("DEF : " + def);
////		System.out.println("MAG : " + mag);
//		
//		if(fillHitpoints) {
//			hitpoints = hp;
//		}
//		
//	}
	
	public boolean getFaction() {
		return faction;
	}
	
	public void setFaction(boolean faction) {
		this.faction = faction;
	}
	
	public void switchFaction() {
		faction = !faction;
	}
	
	public void levelUp() {
		hp_cap  += rng.nextInt(100) <= hp_gro  ? 2 : 1;
		str_cap += rng.nextInt(100) <= str_gro ? 1 : 0;
		def_cap += rng.nextInt(100) <= def_gro ? 1 : 0;
		mag_cap += rng.nextInt(100) <= mag_gro ? 1 : 0;
		res_cap += rng.nextInt(100) <= res_gro ? 1 : 0;
		skl_cap += rng.nextInt(100) <= skl_gro ? 1 : 0;
		spd_cap += rng.nextInt(100) <= spd_gro ? 1 : 0;
		
		level += 1;
		
		resetStats();
	}
	
	public void levelUp(int levels) {
		for(int i = 0; i < levels; i++) {
			levelUp();
		}
		
		System.out.println("HP: " + hp_cap);
		System.out.println("STR: " + str_cap);
		System.out.println("DEF: " + def_cap);
		System.out.println("MAG: " + mag_cap);
		System.out.println("RES: " + res_cap);
		System.out.println("SKL: " + skl_cap);
		System.out.println("SPD: " + spd_cap);
		System.out.println();
	}
	
	public void resetStats() {
		hp = hp_cap;
		str = str_cap;
		def = def_cap;
		mag = mag_cap;
		res = res_cap;
		skl = skl_cap;
		spd = spd_cap;
	}
	
	public boolean hit_str(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getHit() : 0 + str + skl) - (e.def + e.spd);
	}
	
	public boolean hit_mag(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getHit() : 0 + mag + skl) - (e.res + e.spd);
	}
	
	public boolean crit_str(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getCrit() : 0 + str) - e.def;
	}
	
	public boolean crit_mag(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getCrit() : 0 + mag) - e.res;
	}
	
	public int getHealthCap() {
		return hp_cap;
	}
	
	public void regen() {
		if(hp < hp_cap && map.getRounds() % 2 == 0) {
			if(hp + level/20 + 1 < hp_cap) {
				hp += level/20 + 1;
			} else {
				hp = hp_cap;
			}
		}
	}
	
	public void regenFast() {
		if(hp < hp_cap) {
			if(hp + level/20 + 1 < hp_cap) {
				hp += level/20 + 1;
			} else {
				hp = hp_cap;
			}
		}
	}
	
	public void regenSlow() {
		if(hp < hp_cap && map.getRounds() % 3 == 0) {
			if(hp + level/20 + 1 < hp_cap) {
				hp += level/20 + 1;
			} else {
				hp = hp_cap;
			}
		}
	}
	
	public void attack(AbstractEntity e) {
		if(faction != e.faction) {
			if(inventory != null && inventory.hasEquipt()) {
				inventory.get(0).onUse(this);
				
				System.out.print(e.hp + " -> ");
				
				if(inventory.get(0).isMagical()) {
					e.hp -= hit_mag(e) ? crit_mag(e) ? inventory.get(0).getDamage() + mag/2 : inventory.get(0).getDamage() : 0;
				} else {
					e.hp -= hit_str(e) ? crit_str(e) ? inventory.get(0).getDamage() + str/2 : inventory.get(0).getDamage() : 0;
				}
				
				System.out.println(e.hp);
				
			} else {
				e.hp -= hit_str(e) ? crit_str(e) ? dam_str + str/2 : dam_str : 0;
			}
		}
	}
	
	public void interact(AbstractTile t) {
		if(t.isOccupied()) {
			attack(t.getOccupant());
		} else {
			t.onInteraction(this);
		}
	}
	
	public boolean attackAdjacent() {
		for(int x = this.x - 1, y = this.y - 1; y <= this.y + 1; y++) {
			for(x = this.x - 1; x <= this.x + 1; x++) {
				if(map.contains(x, y) && map.tileAt(x, y).isOccupied()) {
					attack(map.tileAt(x, y).getOccupant());
					return true;
				}
			}
		}
		return false;
	}
	
	public void interact(int xOffset, int yOffset) {
		interact(map.tileAt(x + xOffset, y + yOffset));
	}
	
	/**
	 * Kills the AbstractEntity
	 */
	public void kill() {
		hp = 0;
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
	 * Finds the distance between the current position and (x, y)
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return The distance
	 */
	public int distance(int x, int y) {
		return (int) Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2));
	}
	
	/**
	 * Finds the distance between the current position and the given AbstractEntity
	 * @param e The AbstractEntity
	 * @return The distance
	 */
	public int distance(AbstractEntity e) {
		return distance(e.getX(), e.getY());
	}
	
	/**
	 * Finds the distance between the current position and the given AbstractTile
	 * @param t The AbstractTile
	 * @return The distance
	 */
	public int distance(AbstractTile t) {
		return distance(t.getX(), t.getY());
	}
	
	public AbstractTile getTileFacing() {
		switch(facing) {
			case NORTH:
				return map.tileAt(x, y - 1);
			case SOUTH:
				return map.tileAt(x, y + 1);
			case EAST:
				return map.tileAt(x + 1, y);
			case WEST:
				return map.tileAt(x - 1, y);
			case NORTHEAST:
				return map.tileAt(x + 1, y - 1);
			case NORTHWEST:
				return map.tileAt(x - 1, y - 1);
			case SOUTHEAST:
				return map.tileAt(x + 1, y + 1);
			case SOUTHWEST:
				return map.tileAt(x - 1, y + 1);
			default:
				return map.tileAt(x, y);
		}

	}
	
	public boolean canSee(int x, int y) {
		
		// allow seeing on layer of opaque object
		boolean allow = true;
		
		// THANK GOD FOR POLAR COORDINATES!
		int radius = (int) Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
		double theta = Math.atan2(y - this.y, x - this.x);

		int xOffset;
		int yOffset;
		
		//System.out.println("line to (" + radius + ", " + theta + ") (pol)");
		
		for(int i = 0; i <= radius; i++) {
			xOffset = (int) (i*Math.cos(theta));
			yOffset = (int) (i*Math.sin(theta));
			
			if(!map.contains(this.x + xOffset, this.y + yOffset)) {
				return false;
			}
			
			if(map.tileAt(this.x + xOffset, this.y + yOffset).isOpaque()) {
				//System.out.println("returning FALSE at i = " + i);
				if(allow) {
					allow = false;
				} else {
					return false;
				}
			}
			
			map.tileAt(this.x + xOffset, this.y + yOffset).setCovered(true);
		}
		
		return true;
	}
	
	/**
	 * Determines whether there is an uninterupted straight path to the given point (x, y) from the current position
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if there is an uninterupted straight to (x, y) from the current position
	 */
	public boolean isClearPath(int x, double y) {
		// THANK GOD FOR POLAR COORDINATES!
		int radius = (int) Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
		double theta = Math.atan2(y - this.y, x - this.x);
		
		int xOffset;
		int yOffset;
		
		System.out.println("line to (" + radius + ", " + theta + ") (pol)");
		
		for(int i = 2; i <= radius; i++) {
			xOffset = (int) (i*Math.cos(theta));
			yOffset = (int) (i*Math.sin(theta));
			
			if(!map.tileAt(this.x + xOffset, this.y + yOffset).canEnter(this)) {
				System.out.println("returning FALSE at i = " + i);
				return false;
			}
			
			//map.tileAt(this.x + xOffset, this.y + yOffset).setCovered(true);
		}
		
		return true;
	}
	
	/**
	 * Determines whether there is an uninterupted straight path to the given AbstractEntity from the current position
	 * @param e The AbstractEntity
	 * @return true if there is an uninterupted straight the AbstractEntity from the current position
	 */
	public boolean isClearPath(AbstractEntity e) {
		return isClearPath(e.getX(), e.getY());
	}
	
	/**
	 * Determines whether there is an uninterupted straight path to the given AbstractTile from the current position
	 * @param t The AbstractTile
	 * @return true if there is an uninterupted straight the AbstractTile from the current position
	 */
	public boolean isClearPath(AbstractTile t) {
		return isClearPath(t.getX(), t.getY());
	}
	
	/**
	 * Moves one tile north
	 * @return true if moved
	 */
	public boolean goNorth() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = NORTH;
		
		if(y - 1 > 0) {
			if(map.tileAt(x, y - 1).canEnter(this)) {
				y -= 1;		
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}	
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile south
	 * @return true if moved
	 */
	public boolean goSouth() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = SOUTH;
		
		if(y + 1 < map.height()) {
			if(map.tileAt(x, y + 1).canEnter(this)) {
				y += 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}	
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile east
	 * @return true if moved
	 */
	public boolean goEast() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = EAST;
		
		if(x + 1 > 0) {
			if(map.tileAt(x + 1, y).canEnter(this)) {
				x += 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}	
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile west
	 * @return true if moved
	 */
	public boolean goWest() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = WEST;
		
		if(x - 1 > 0) {
			if(map.tileAt(x - 1, y).canEnter(this)) {
				x -= 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}	
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile northeast
	 * @return true if moved
	 */
	public boolean goNortheast() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = NORTHEAST;
		
		if(map.contains(x + 1, y - 1)) {
			if(map.tileAt(x + 1, y - 1).canEnter(this)) {
				x += 1;
				y -= 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile northwest
	 * @return true if moved
	 */
	public boolean goNorthwest() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = NORTHWEST;
		
		if(map.contains(x - 1, y - 1)) {
			if(map.tileAt(x - 1, y - 1).canEnter(this)) {
				this.x -= 1;
				this.y -= 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile southeast
	 * @return true if moved
	 */
	public boolean goSoutheast() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = SOUTHEAST;
		
		if(map.contains(x + 1, y + 1)) {
			if(map.tileAt(x + 1, y + 1).canEnter(this)) {
				x += 1;
				y += 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	/**
	 * Moves one tile southwest
	 * @return true if moved
	 */
	public boolean goSouthwest() {
		map.tileAt(this.x, this.y).setOccupant(null);
		
		facing = SOUTHWEST;
		
		if(map.contains(x - 1, y + 1)) {
			if(map.tileAt(x - 1, y + 1).canEnter(this)) {
				x -= 1;
				y += 1;
				
				map.tileAt(this.x, this.y).setOccupant(this);
				return true;
			}
		}
		
		map.tileAt(this.x, this.y).setOccupant(this);
		return false;
	}
	
	
	/**
	 * Moves one space toward the given point on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if moved
	 */
	public boolean goTo(int x, int y) {
		boolean moved = false;
		
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
		
		return moved;
	}
	
	public boolean goTo(AbstractEntity e) {
		return goTo(e.getX(), e.getY());
	}
	
	/**
	 * Moves one space away from the given point on the map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param true if moved
	 */
	public boolean avoid(int x, int y) {
//		if(this.x < x && this.x - 1 > 0) this.x -= 1;
//		if(this.x > x && this.x + 1 < map.length()) this.x += 1;
//		if(this.y < y && this.y - 1 > 0) this.y -= 1;
//		if(this.y > y && this.y + 1 < map.height()) this.y += 1;
		
		boolean moved = false;
		
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
		
		return moved;
	}
	
	public boolean avoid(AbstractEntity e) {
		return avoid(e.getX(), e.getY());
	}
	
	/**
	 * The AbstractEntity moves forward until it hits a wall, at which point it turns 45 degrees clockwise
	 */
	public void goUntilWallClockwise() {
		
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
		
	}
	
	/**
	 * The AbstractEntity moves forward until it hits a wall, at which point it turns 45 degrees counterclockwise
	 */
	public void goUntilWallCounterclockwise() {
		
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
		
	}
	
	/**
	 * The AbstractEntity moves forward until it hits a wall, at which point it turns 90 degrees clockwise
	 */
	public void goUntilWallRight() {
		
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
		
	}
	
	/**
	 * The AbstractEntity moves forward until it hits a wall, at which point it turns 90 degrees counterclockwise
	 */
	public void goUntilWallLeft() {
		
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
		
	}
	
	/**
	 * The AbstractEntity walks one step in a random direction
	 * @returns True if the AbstractEntity moved
	 */
	public boolean randomWalk() {
		
		facing = (short) rng.nextInt(8);
		
		switch(facing) {
			case NORTH:
				return goNorth();
			case SOUTH:
				return goSouth();
			case EAST:
				return goEast();
			case WEST:
				return goWest();
			case NORTHEAST:
				return goNortheast();
			case NORTHWEST:
				return goNorthwest();
			case SOUTHEAST:
				return goSoutheast();
			case SOUTHWEST:
				return goSouthwest();
			default:
				return false;
		}
	}
	
	public boolean teleport(int x, int y) {
		if(x > 0 && x < map.length() && y > 0 && y < map.height() && map.tileAt(x, y).canEnter(this)) {
			map.tileAt(this.x, this.y).setOccupant(null);
			map.tileAt(x, y).setOccupant(this);
			return true;
		}
		return false;
	}
	
	public void randomTeleport() {
		map.tileAt(x, y).setOccupant(null);
		
		while(!map.tileAt(x, y).canEnter(this)) {
			x = rng.nextInt(map.length());
			y = rng.nextInt(map.height());
		}
		
		map.tileAt(x, y).setOccupant(this);
	}
	
}
