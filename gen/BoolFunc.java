package gen;

/**
 * A class of static methods used in word generation that operate on 2D boolean arrays
 * Credit to the people at Rogue Basin for some concepts and code samples
 * @author Ryan Luchs
 *
 */
public class BoolFunc {
	
	/**
	 * A percentage that produces fairly open levels with no cut off rooms when using cellular automata
	 */
	public static final int DEFAULT_PERCENT_TRUE = 40;
	
	/*
	 * add sprinkle
	 * add random walk
	 * add random walk with rooms
	 */
	
	/**
	 * Fills a boolean[][] with the specified value
	 * @param array The modify
	 * @param fillState The value to fill with
	 * @return The modified array
	 */
	public static boolean[][] fill(boolean[][] array, boolean fillState) {
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				array[x][y] = fillState;
			}
		}
		
		return array;
	}
	
	/**
	 * Inverts every value of a boolean[][] (value = !value)
	 * @param array The array
	 * @return The modified array
	 */
	public static boolean[][] invert(boolean[][] array) {
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				array[x][y] = !array[x][y];
			}
		}
		
		return array;
	}
	
	/**
	 * Generates a patterned boolean[][] by use of a random walk algorithm
	 * Random produce maps with long interconnected grid like corridors
	 * This method uses Cartogrpaher's random object
	 * @param columns The map length
	 * @param rows The map height
	 * @param step The length of each "step" of the walk
	 * @param percentTrue The percentage of tiles that should be "true" at the end if the alk does not cross over its own path
	 * @return The map as a boolean[][]
	 */
	public static boolean[][] randomWalk(int columns, int rows, int step, int percentTrue) {
		boolean[][] map = fill(new boolean[columns][rows], true);
		
		// calculate number of iterations
		int iterations = (int) (columns * rows * ( (float) percentTrue/100) / step);
		
		int x = columns/2 + 1, y = rows/2 + 1;
		
		// make startpoint clear
		map[x][y] = false;
		
		// the actual walking
		int d = Cartographer.r.nextInt(4);
		for(int f = 0; f < iterations; f++) {
			
			// walk right 
			if(d == 0 && (!isOutOfBounds(x + step, y, columns, rows))) {
				for(int i = 0; i < step; i++) {
					map[x + i][y] = false;
				}
				
				x += step;
				
			// walk left
			} else if(d == 1 && (!isOutOfBounds(x - step, y, columns, rows))) {
				for(int i = 0; i > step; i++) {
					map[x - i][y] = false;
				}
				
				x -= step;
				
			// walk down
			} else if(d == 2 && (!isOutOfBounds(x, y + step, columns, rows))) {
				for(int i = 0; i < step; i++) {
					map[x][y + i] = false;
				}
				
				y += step;
				
			// walk up
			} else if(!isOutOfBounds(x, y - step, columns, rows)){
				for(int i = 0; i < step; i++) {
					map[x][y - i] = false;
				}
				
				y -= step;
			}
			
			d = Cartographer.r.nextInt(4);
			
//			System.out.println("step length: " + step + " | iteration #: " + f);
		}
		
		// clear top and bottom borders
		for(y = 0; y < map[0].length; y++) {
			map[0][y] = true;
			map[columns - 1][y] = true;
		}
		
		// clear left and right borders
		for(x = 0; x < map.length; x++) {
			map[x][0] = true;
			map[x][rows - 1] = true;
		}
		
		return map;
	}
	
	/**
	 * Generateds a patterned boolean[][] by use of a cellular automaton algorithm
	 * Cellular Automata are used to produce more organic looking distributions
	 * This algorithm runs for four iterations
	 * This method uses Cartographer's random object
	 * @param columns The map length
	 * @param rows The map height
	 * @param percentTrue Percentage of map covered by "true" cells at initialization
	 * @return The map as a boolean[][]
	 */
	public static boolean[][] cellularAutomaton(int columns, int rows, int percentTrue) {
		
		// the final map
		boolean[][] map = new boolean[columns][rows];
		
		// map used for updates
		boolean[][] update = new boolean[columns][rows];
		
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
					
					map[x][y] = Cartographer.r.nextInt(100) < percentTrue;
				}
			}
		}
	
		//print(map);
		
		for(int i = 0; i < 4; i++) {
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					update[x][y] = rules(map, x, y);
				}
			}
		
			//print(update);
			
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					map[x][y] = update[x][y];
				}
			}
		}

		return map;
	}
	
	/**
	 * Gets the number of adjacent cells that are true
	 * @param m The map as a boolean[][]
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return The number of adjacent true cells
	 */
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
	
	/**
	 * Returns whether a given cell is a wall
	 * @param m The map as a boolean[][]
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return True if the cell is true or out of bounds
	 */
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
	
	/**
	 * Returns whether a point is of the bounds of a map
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param columns The map length
	 * @param rows The map height
	 * @return True if (x, y) out of bounds
	 */
	private static boolean isOutOfBounds(int x, int y, int columns, int rows) {
		if(x < 0 || y < 0) {
			return true;
		}
		else if(x > columns - 1 || y > rows - 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines the state of a cell
	 * @param m The map as a boolean[][]
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return The state of the cell, true or false
	 */
	private static boolean rules(boolean[][] m, int x, int y) {
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
	
	/**
	 * Prints out a text representation of a boolean map
	 * @param m The map as a boolean[][]
	 */
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
