package gen;

import tile.*;
import item.*;
import entity.*;

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
	public static final short SAND = 12;
	
	
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
	
	public static boolean isFilled(short id) {
		return (id % 2) == 1;
	}
	
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
			
			default:
				tile = new StoneFloor(x, y);
				break;
		}
		
		return tile;
	}
	
	public static AbstractEntity makeEntity(short id, int x, int y, int level, TileMap map) {
		AbstractEntity e;
		
		switch(id) {
		
			case BAT:
				e = new Bat(x, y, level, map);
				break;
			
			case GIANT_BAT:
				e = new GiantBat(x, y, level, map);
				break;
				
			case BEAR:
				e = new Bear(x, y, level, map);
				break;
				
			case DRAGON:
				e = new Dragon(x, y, level, map);
				break;
				
			case SPIDER:
				e = new Spider(x, y, level, map);
				break;
				
			case OWL:
				e = new Owl(x, y, level, map);
				break;
				
			case SALAMANDER:
				e = new Salamander(x, y, level, map);
				break;
				
			case TICK:
				e = new Tick(x, y, level, map);
				break;
				
			case FIRE_ELEMENTAL:
				e = new FireElemental(x, y, level, map);
				break;
			
			case WATER_ELEMENTAL:
				e = new WaterElemental(x, y, level, map);
				break;
				
			case EGG:
				e = new DragonEgg(x, y, level, map);
				break;
				
			default:
				e = new DragonEgg(x, y, level, map);
				break;
		}
		
		map.tileAt(x, y).setOccupant(e);
		e.randomTeleport();
		
		return e;
	}

}
