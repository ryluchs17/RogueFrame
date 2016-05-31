package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import item.AbstractItem;
import item.Inventory;
import tile.AbstractTile;
import tile.TileMap;

/**
 * RougeFrame entity template
 * @author Ryan Luchs
 *
 */

public abstract class AbstractEntity {

	// ! POSITION STUFF !
	
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
	
	// ! MISC STUFF !
	
	// The inventory that this AbstractEntity possesses
	protected Inventory inventory;
	
	// The rng used by this AbstractEntity
	protected Random rng;
	
	// ! GRAPHICS/FLAVOR TEXT !
	
	// Char and Color to display as
	protected String character;
	protected Color color;
	
	// Flavor text
	protected String name, description;
	
	// Determines whether or not to display the tile under the mob (TODO not actually used)
	public boolean visible = true;	
	
	// Determines whether the entity actually makes contact with the tile below it
	public boolean grounded = true;
	
	// Determines whether or not the entity ignores "avoid" tiles
	public boolean ignore = false;
	
	// ! TYPE STUFF !
	
	public static boolean FACTION_HOSTILE = false;
	public static boolean FACTION_PLAYER_OR_ALLY = true;
	
	protected boolean faction = FACTION_HOSTILE;

	// ! STATS STUFF !
	
	// Character Level stuff
	protected int level;
	
	// experience points
	public short experience = 0;
	
	// the base amount of earned upon defeat
	public short expWorth;
	
	// ! MORE STATS STUFF BEGINS !

	protected int hp_cap, str_cap, def_cap, mag_cap, res_cap, skl_cap, spd_cap;
	
	protected int hp_gro, str_gro, def_gro, mag_gro, res_gro, skl_gro, spd_gro;
	
	public int hp, str, def, mag, res, skl, spd;
	
	public int dam_str = 0, dam_mag = 0;
	
	// ! REGEN STUFF !
	
	public static final short REGEN_RATE_NONE = 0;
	
	public static final short REGEN_RATE_FAST = 1;
	
	public static final short REGEN_RATE_NORM = 2;
	
	public static final short REGEN_RATE_SLOW = 3;
	
	// A regenerating AbstractEntity regains health at a rate of 1/20 max HP per #regenRate turns
	protected short regenRate = REGEN_RATE_NORM;
	
	// ! CONSTRUCTORS !
	
	/**
	 * Creates a new AbstractEntity at (x, y) at the given level
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param level The level of the AbstractEntity
	 * @param map The TileMap for the AbstractEntity to exist in
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
	abstract public void onAttacked(AbstractEntity e);
	
	/**
	 * What to do when killed
	 */
	abstract public void onDeath();
	
	// ! ABSTRACT METHODS END !
	
	/**
	 * Calls onTurn() custom functionality before regenerating and checking for level-ups
	 */
	public void onBasicTurn() {
		onTurn();
		
		regen();
		if(experience >= 100) {
			levelUp();
			experience -= 100;
		}
	}
	
	/**
	 * Calls onAttacked() custom functionality before checking whether to award experience to an opponent
	 * @param e
	 */
	public void onBasicAttacked(AbstractEntity e) {
		onAttacked(e);
		
		if(hp <= 0) {
			e.experience += (int) (expWorth * (level/ (float) e.level));
			//System.out.println("gave " + (expWorth * (level/e.level)) + " exp");
		}
	}
	
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
	
	/**
	 * Gets the map this AbstractEntity currently exists in
	 * @return The map containing this AbstractEntity
	 */
	public TileMap getMap() {
		return map;
	}
	
	/**
	 * Gets this AbtractEntity's inventory
	 * @return This AbstractEntity's inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Gets the faction of this AbstractEntity (true = player aligned)
	 * @return The faction
	 */
	public boolean getFaction() {
		return faction;
	}
	
	/**
	 * Sets the faction of this AbstractEntity
	 * @param faction The faction
	 */
	public void setFaction(boolean faction) {
		this.faction = faction;
	}
	
	/**
	 * Inverts the faction of this AbstractEntity
	 */
	public void switchFaction() {
		faction = !faction;
	}
	
	/**
	 * Gets the level of this AbstractEntity
	 * @return The level of this AbstractEntity
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Increases the level of this AbstractEntity by 1
	 * including a corresponding increase in stats
	 */
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
	
	/**
	 * Increases the level of this AbstractEntity by a given number of levels
	 * @param levels The number of levels
	 */
	public void levelUp(int levels) {
		for(int i = 0; i < levels; i++) {
			levelUp();
		}
		
//		System.out.println("HP: " + hp_cap);
//		System.out.println("STR: " + str_cap);
//		System.out.println("DEF: " + def_cap);
//		System.out.println("MAG: " + mag_cap);
//		System.out.println("RES: " + res_cap);
//		System.out.println("SKL: " + skl_cap);
//		System.out.println("SPD: " + spd_cap);
//		System.out.println();
	}
	
	/**
	 * Resets all stats to their maximum
	 */
	public void resetStats() {
		hp = hp_cap;
		str = str_cap;
		def = def_cap;
		mag = mag_cap;
		res = res_cap;
		skl = skl_cap;
		spd = spd_cap;
	}
	
	/**
	 * Determines whether this AbstractEntity will hit another with a physical attack
	 * @param e The entity to attack
	 * @return true if hit
	 */
	public boolean hit_str(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getHit() : 0 + str + skl) - (e.def + e.spd);
	}
	
	/**
	 * Determines whether this AbstractEntity will hit another with a magical attack
	 * @param e The entity to attack
	 * @return true if hit
	 */
	public boolean hit_mag(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getHit() : 0 + mag + skl) - (e.res + e.spd);
	}
	
	/**
	 * Determines whether an attack wil score a critical hit against an AbstractEntity
	 * @param e The entity to attack
	 * @return true if critical hit
	 */
	public boolean crit_str(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getCrit() : 0 + str) - e.def;
	}
	
	/**
	 * Determines whether an attack wil score a critical hit against an AbstractEntity
	 * @param e The entity to attack
	 * @return true if critical hit
	 */
	public boolean crit_mag(AbstractEntity e) {
		return rng.nextInt(100) <= (inventory.hasEquipt() ? inventory.get(0).getCrit() : 0 + mag) - e.res;
	}
	
	/**
	 * Gets the maximum health of this AbstractEntity
	 * @return The maximum health og this AbstractEntity
	 */
	public int getHealthCap() {
		return hp_cap;
	}
	
	/**
	 * Causes this AbstractEntity to regain a small amount of hp
	 */
	public void regen() {
		if(regenRate != 0 && hp < hp_cap && map.getRounds() % regenRate == 0) {
			if(hp + level/20 + 1 < hp_cap) {
				hp += level/20 + 1;
			} else {
				hp = hp_cap;
			}
		}
	}
	
	/**
	 * Attacks a specified AbstractEntity
	 * AbstractEntities of the same faction will not be harmed
	 * @param e The target
	 */
	public void attack(AbstractEntity e) {
		if(faction != e.faction) {
			if(inventory != null && inventory.hasEquipt()) {
				inventory.get(0).onUse(this);
				
				//System.out.print(e.hp + " -> ");
				
				if(inventory.get(0).isMagical()) {
					e.hp -= hit_mag(e) ? crit_mag(e) ? inventory.get(0).getDamage() + mag/2 : inventory.get(0).getDamage() : 0;
				} else {
					e.hp -= hit_str(e) ? crit_str(e) ? inventory.get(0).getDamage() + str/2 : inventory.get(0).getDamage() : 0;
				}
				
				//System.out.println(e.hp);
				
			} else {
				e.hp -= hit_str(e) ? crit_str(e) ? dam_str + str/2 : dam_str : 0;
			}
			e.onBasicAttacked(this);
		}
	}
	
	/**
	 * Performs a throwing attack on a given AbstractEntity with a given AbstractItem
	 * @param i The item
	 * @param e The target
	 */
	public void throwAttack(AbstractItem i, AbstractEntity e) {
		if(i.isMagical()) {
			e.hp -= hit_mag(e) ? crit_mag(e) ? i.getDamage() * 2 + mag/2 : i.getDamage() * 2 : 0;
		} else {
			e.hp -= hit_str(e) ? crit_str(e) ? i.getDamage() * 2 + str/2 : i.getDamage() * 2 : 0;
		}
		i.onThrown(e);
		e.onBasicAttacked(this);
	}
	
	/**
	 * Causes this AbstractEntity to interact with a specified tile
	 * if it is occupied the occupant will be attacked instead
	 * @param t The AbstractTile
	 */
	public void interact(AbstractTile t) {
		if(t.isOccupied()) {
			attack(t.getOccupant());
		} else {
			t.onInteraction(this);
		}
	}
	
	/**
	 * Attacks the first AbstractEntity found in an adjacent space
	 * AbstractEntities of the same faction will not be harmed
	 * @return
	 */
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
	
	/**
	 * Causes the AbtractEntity to interact with the AbstractTile at the given offset
	 * if the tile is occupied its occupant will be attacked
	 * @param xOffset The offset on the x-axis
	 * @param yOffset The offset on the y-axis
	 */
	public void interact(int xOffset, int yOffset) {
		interact(map.tileAt(x + xOffset, y + yOffset));
	}
	
	/**
	 * Throws the item on the AbstractTile occuied by this AbstractEntity at a given AbstractTile
	 * if the target tile is occupied the occupant will be attacked
	 * @param t The target tile
	 */
	public void throwItem(AbstractTile t) {
		if(map.tileAt(x, y).hasItem() && map.tileAt(x, y).getItem().isThrowable()) {
			AbstractItem item = map.tileAt(x, y).getItem();
			
			if(item.isStackable()) {
				
				if(t.isOccupied()) {
					throwAttack(item, t.getOccupant());
				}
				
				if(!item.isConsumable()) {
					if(t.hasItem() && t.getItem().getName().equals(item.getName())) {
						t.getItem().uses++;
						item.uses--;
					} else {
						AbstractItem clone = (AbstractItem) item.clone();
						t.setItem(clone);
						item.uses--;
					}
				}
				
				if(item.uses <= 0) {
					map.tileAt(x, y).setItem(null);
				}
				
			} else {
				
				if(t.isOccupied()) {
					throwAttack(item, t.getOccupant());
				}
				
				t.setItem(item);
				map.tileAt(x, y).setItem(null);
			}
			
			item.curseIsKnown = true;
		}
	}
	
	/**
	 * Throws the item at the given index in this AbstractEntity's inventory at the given AbstractTile
	 * if the target tile is occupied the occupant will be attacked
	 * @param index The index of the AbstractItem to throw
	 * @param t The target tile
	 */
	public void throwItem(int index, AbstractTile t) {
		if(inventory.has(index) && inventory.get(index).isThrowable()) {
			
			AbstractItem item = inventory.get(index);
			
			if(item.isStackable()) {
				
				if(t.isOccupied()) {
					throwAttack(item, t.getOccupant());
				}
				
				if(!item.isConsumable()) {
					if(t.hasItem() && t.getItem().getName().equals(item.getName())) {
						t.getItem().uses++;
						item.uses--;
					} else {
						AbstractItem clone = (AbstractItem) item.clone();
						t.setItem(clone);
						item.uses--;
					}
				}
					
				inventory.clean();
				
			} else {
				
				if(t.isOccupied()) {
					throwAttack(item, t.getOccupant());
				}
				
				t.setItem(item);
				inventory.remove(index);
			}
			
			item.curseIsKnown = true;
		}
	}
	
	/**
	 * Kills the AbstractEntity
	 */
	public void kill() {
		hp = -256;
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
	
	/**
	 * Determines whether this AbstractEntity can see the AbstractTile at (x, y)
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if can see
	 */
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
			
			//map.tileAt(this.x + xOffset, this.y + yOffset).setCovered(true);
		}
		
		return true;
	}
	
	/**
	 * Determines whether this AbstractEntity can see the given AbstractTile
	 * @param t The AbstractTile
	 * @return true if can see
	 */
	public boolean canSee(AbstractTile t) {
		return canSee(t.getX(), t.getY());
	}
	
	/**
	 * Determines whether this AbstractEntity can see the given AbstractEntity
	 * @param e The AbstractEntity
	 * @return true if can see
	 */
	public boolean canSee(AbstractEntity e) {
		return canSee(e.getX(), e.getY());
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
		
//		System.out.println("line to (" + radius + ", " + theta + ") (pol)");
		
		for(int i = 2; i <= radius; i++) {
			xOffset = (int) (i*Math.cos(theta));
			yOffset = (int) (i*Math.sin(theta));
			
			if(!map.tileAt(this.x + xOffset, this.y + yOffset).canApproach(this)) {
				//System.out.println("returning FALSE at i = " + i);
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
	
	/**
	 * Attempts to teleport this AbstractEntity to the given coordinate (x, y)
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return true if successful
	 */
	public boolean teleport(int x, int y) {
		if(x > 0 && x < map.length() && y > 0 && y < map.height() && map.tileAt(x, y).canEnter(this)) {
			map.tileAt(this.x, this.y).setOccupant(null);
			map.tileAt(x, y).setOccupant(this);
			return true;
		}
		return false;
	}
	
	/**
	 * Teleports this AbstractEntity to a random save space within the confines of the map
	 */
	public void randomTeleport() {
		map.tileAt(x, y).setOccupant(null);
		
		boolean temp = ignore;
		
		ignore = false;
		
		while(!map.tileAt(x, y).canEnter(this)) {
			x = rng.nextInt(map.length());
			y = rng.nextInt(map.height());
		}
		
		ignore = temp;
		
		map.tileAt(x, y).setOccupant(this);
	}
	
}
