package gen;

import tile.*;
import item.*;
import entity.*;

/**
 * A list of constants for all AbstractTile, AbstractEntity, and AbstractItem variants
 * with corresponding generation functions
 * @author Ryan Luchs
 *
 */
public final class Const {
	
	// level constants
	public static final short CAVE = 0;
	public static final short FOREST = 1;
	public static final short MAZE = 2;
	public static final short LAVA_TUBE = 3;
	public static final short ISLAND = 4;
	public static final short BOSS = 5;
	
	// Tile constants
	public static final short STONE_FLOOR = 0;
	public static final short STONE_WALL = 1;
	public static final short MAGMA = 2;
	public static final short WEB = 3;
	public static final short TREE = 4;
	public static final short WATER = 5;
	public static final short GRASS = 6;
	public static final short SPIKE = 7;
	public static final short TRAP = 8;
	public static final short BUSH = 9;
	public static final short OBSIDIAN = 10;
	public static final short SNARE = 11;
	public static final short DEEP_WATER = 12;
	
	
//	// defaults
//	public static final short DEFFLOOR = 0;
//	public static final short DEFWALL = 1;
//	
//	// other tiles
//	public static final short SOIL = 2;
//	public static final short WATER = 3;
//	public static final short SPIKE = 4;
//	public static final short MAGMA = 5;
	
	// Entity Constants
	public static final short BAT = 0;
	public static final short GIANT_BAT = 1;
	public static final short BEAR = 2;
	public static final short DRAGON = 3;
	public static final short SPIDER = 4;
	public static final short MUSHROOM = 5;
	public static final short OWL = 6;
	public static final short SALAMANDER = 7;
	public static final short TICK = 8;
	public static final short FIRE_ELEMENTAL = 9;
	public static final short WATER_ELEMENTAL = 10;
	public static final short EGG = 11;
	
	/**
	 * Instantiates a new AbstractTile variant that matches the given ID number
	 * @param id The ID number of the tile
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return The AbstractTile
	 */
	public static AbstractTile makeTile(short id, int x, int y) {
		AbstractTile tile = null;
		
		switch(id) {
		
			case STONE_FLOOR:
				tile = new StoneFloor(x, y);
				break;
				
			case STONE_WALL:
				tile = new StoneWall(x, y);
				break;
				
			case MAGMA:
				tile = new Magma(x, y);
				break;
				
			case WEB:
				tile = new Web(x, y);
				break;
			
			case TREE:
				tile = new Tree(x, y);
				break;
				
			case WATER:
				tile = new Water(x, y);
				break;
				
			case GRASS:
				tile = new Grass(x, y);
				break;
				
			case SPIKE:
				tile = new Spike(x, y);
				break;
				
			case TRAP:
				tile = new Trap(x, y);
				break;
				
			case BUSH:
				tile = new Bush(x, y);
				break;
				
			case OBSIDIAN:
				tile = new Obsidian(x, y);
				break;
				
			case SNARE:
				tile = new Snare(x, y);
				break;
				
			case DEEP_WATER:
				tile = new DeepWater(x, y);
				break;
			
			default:
				tile = new StoneFloor(x, y);
				break;
		}
		
		return tile;
	}
	
	/**
	 * Instantiates a new AbstractEntity variant that matches the given ID number at a random location in the given TileMap
	 * @param id The ID number of the AbstractEntity
	 * @param level The level of the AbstractEntity
	 * @param map The TileMap for the AbstractEntity to exist in
	 * @return The AbstractEntity
	 */
	public static AbstractEntity makeEntity(short id, int level, TileMap map) {
		AbstractEntity e;
		
		switch(id) {
		
			case BAT:
				e = new Bat(0, 0, level, map);
				break;
			
			case GIANT_BAT:
				e = new GiantBat(0, 0, level, map);
				break;
				
			case BEAR:
				e = new Bear(0, 0, level, map);
				break;
				
			case DRAGON:
				e = new Dragon(0, 0, level, map);
				break;
				
			case SPIDER:
				e = new Spider(0, 0, level, map);
				break;
				
			case OWL:
				e = new Owl(0, 0, level, map);
				break;
				
			case SALAMANDER:
				e = new Salamander(0, 0, level, map);
				break;
				
			case TICK:
				e = new Tick(0, 0, level, map);
				break;
				
			case FIRE_ELEMENTAL:
				e = new FireElemental(0, 0, level, map);
				break;
			
			case WATER_ELEMENTAL:
				e = new WaterElemental(0, 0, level, map);
				break;
				
			case EGG:
				e = new DragonEgg(0, 0, level, map);
				break;
				
			default:
				e = new DragonEgg(0, 0, level, map);
				break;
		}
		
		map.tileAt(0, 0).setOccupant(e);
		e.randomTeleport();
		
		return e;
	}

}
