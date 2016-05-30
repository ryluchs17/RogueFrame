package gen;

import tile.AbstractTile;
import tile.DefFloor;
import tile.DefWall;
import tile.Magma;
import tile.Soil;
import tile.Spike;
import tile.Water;

public final class Const {
	
	// Tile constants
	public static final short STONE_FLOOR = 0;
	public static final short STONE_WALL = 1;
	public static final short LAVA/*MAGMA*/ = 2;
	public static final short WEB = 3;
	public static final short TREE = 4;
//	public static final short WATER = 5;
	public static final short GRASS = 6;
//	public static final short SPIKE = 7;
	public static final short TRAP = 8;
	public static final short WEED = 9;
	public static final short OBSIDIAN = 10;
	public static final short SAND = 11;
	
	
	// defaults
	public static final short DEFFLOOR = 0;
	public static final short DEFWALL = 1;
	
	// other tiles
	public static final short SOIL = 2;
	public static final short WATER = 3;
	public static final short SPIKE = 4;
	public static final short MAGMA = 5;
	
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
		
			case DEFFLOOR:
				tile = new StoneFloor(x, y);
				break;
				
			case DEFWALL:
				tile = new StoneWall(x, y);
				break;
				
			case SOIL:
				tile = new Soil(x, y);
				break;
				
			case WATER:
				tile = new Water(x, y);
				break;
			
			case SPIKE:
				tile = new Spike(x, y);
				break;
				
			case MAGMA:
				tile = new Magma(x, y);
				break;
			
			default:
				tile = new DefFloor(x, y);
				break;
		}
		
		return tile;
	}

}
