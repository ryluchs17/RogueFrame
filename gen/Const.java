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
	
	// defaults
	public static final short DEFFLOOR = 0;
	public static final short DEFWALL = 1;
	
	// other tiles
	public static final short SOIL = 2;
	public static final short WATER = 3;
	public static final short SPIKE = 4;
	public static final short MAGMA = 5;
	
	public static boolean isFilled(short id) {
		return (id % 2) == 1;
	}
	
	public static AbstractTile makeTile(short id, int x, int y) {
		AbstractTile tile = null;
		
		switch(id) {
		
			case DEFFLOOR:
				tile = new DefFloor(x, y);
				break;
				
			case DEFWALL:
				tile = new DefWall(x, y);
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
