/**
 * A map generator for RogueFrame. Credit to the people at Rogue Basin for some concepts and code samples
 * @author Ryan Luchs
 */
package tile;

import java.util.Random;

/**
 * @author SJHSStudent
 *
 */
public class Cartographer {

	public static long seed = 0;
	
	// defaults
	public static final short TILE_DEFAULT_SPACE = 0;
	public static final short TILE_DEFAULT_WALL = 1;
	
	// other tiles
	public static final short TILE_SOIL = 2;
	public static final short TILE_WATER = 3;
	public static final short TILE_SPIKE = 4;
	public static final short TILE_MAGMA = 5;
	
	public static boolean isFilled(short id) {
		return (id % 2) == 1;
	}
	
	public static AbstractTile makeTile(short id, int x, int y) {
		AbstractTile tile = null;
		
		switch(id) {
		
			case TILE_DEFAULT_SPACE:
				break;
				
			case TILE_DEFAULT_WALL:
				break;
				
			case TILE_SOIL:
				tile = new Soil(x, y);
				break;
				
			case TILE_WATER:
				tile = new Water(x, y);
				break;
			
			case TILE_SPIKE:
				tile = new Spike(x, y);
				break;
				
			case TILE_MAGMA:
				tile = new Magma(x, y);
				break;
			
			default:
				tile = new Soil(x, y);
				break;
		}
		
		return tile;
	}
	
	public static AbstractTile[][] createMapFromBooleanArray(int columns, int rows) {
		boolean[][] array = cellularAutomata(columns, rows);
		
		AbstractTile[][] map = new AbstractTile[array.length][array[0].length];
		
		for(int y = 0; y < array[0].length; y++) {
			for(int x = 0; x < array.length; x++) {
				map[x][y] = array[x][y] ? new DefWall(x, y) : new Soil(x, y);
			}
		}
		
		return map;
	}
	
	public static boolean[][] cellularAutomata(int columns, int rows) {
		
		// the final map
		boolean[][] map = new boolean[columns][rows];
		
		// map used for updates
		boolean[][] update = new boolean[columns][rows];
		
		// random number generator
		Random r = new Random(seed);
		
		for(int x = 0, y = 0; y < rows; y++) {
			for(x = 0; x < columns; x++) {
				if(x == 0) {
					map[x][y] = true;
				} else if(y == 0) {
					map[x][y] = true;
				} else if(x == columns - 1) {
					map[x][y] = true;
				} else if(y == rows - 1) {
					map[x][y] = true;
				} else {
					
					map[x][y] = r.nextInt(100) < 40;
					
//					middle = columns / 2;
//					
//					if(y == middle) {
//						plane[x][y] = false;
//					} else {
//						plane[x][y] = generate.nextInt(100) < 40;
//					}
				}
			}
		}
	
		print(map);
		
		for(int i = 0; i < 4; i++) {
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					update[x][y] = placeWall(map, x, y);
				}
			}
		
			print(update);
			
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					map[x][y] = update[x][y];
				}
			}
		}

		return map;
	}
	
	private static int getAdjacentWalls(boolean[][] m, int x, int y) {
		int iX = x - 1;
		int iY = y - 1;
	
		int counter = 0;
	
		for(iY = y - 1; iY <= y + 1; iY++) {
			for(iX = x - 1; iX <= x + 1; iX++) {
				if(!(iX == x && iY == y)) {
					if(isWall(m, iX, iY)) {
						counter += 1;
					}
				}
			}
		}
		return counter;
	}
	
	private static boolean isWall(boolean[][] m, int x,int y) {
		// Consider out-of-bound a wall
		if(isOutOfBounds(x, y, m.length, m[1].length)) {
			return true;
		}
	
		if(m[x][y] == true) {
			return true;
		}
	
		if(m[x][y] == false) {
			return false;
		}
		return false;
	}
	
	private static boolean isOutOfBounds(int x, int y, int columns, int rows) {
		if(x < 0 || y < 0) {
			return true;
		}
		else if(x > columns - 1 || y > rows - 1) {
			return true;
		}
		return false;
	}
	
	private static boolean placeWall(boolean[][] m, int x, int y) {
		int neighbors = getAdjacentWalls(m, x, y);
		
		if(m[x][y] == true) {
			if(neighbors >= 4) {
				return true;
			}
			if(neighbors < 2) {
				return false;
			}
		} else {
			if(neighbors >= 5) {
				return true;
			}
		}
		return false;	
	}
	
	private static void print(boolean[][] m) {
		for(int y = 0, x = 0; y < m[0].length; y++) {
			for(x = 0; x < m.length; x++) {
				System.out.print(m[x][y] ? "#" : "-");
			}
			
			System.out.print("\n");
		}
		
		System.out.print("\n");
	}
		
}
