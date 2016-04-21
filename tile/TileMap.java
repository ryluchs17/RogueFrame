package tile;

import java.util.ArrayList;
import java.util.Random;
import entity.*;

public class TileMap {

	// length and height of map
	private int columns; private int rows;
	
	// random number generator for map gen
	private Random generate;
	
	// array to store map tiles
	private AbstractTile[][] tiles;
	
//	// array to store positions of updated tiles
//	private int[][] update;
//	private int numToUpdate = 0;
	
	// array to store entities
	private ArrayList<AbstractEntity> entities;
	
	// seed for the random number generator
	private long seed = 0;
	
	// the number of rounds executed during the existance of this map
	private int rounds =  0;

	//private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	//private int AbstractTile.STEP = font.getSize();
	
	/**
	 * Creates a new Map of size columns * rows, with a display of size viewColumns * viewRows.
	 * @param columns The length of the map in tiles
	 * @param rows The height of the map in tiles
	 */
	public TileMap(int columns, int rows) {
		generate = new Random();

		this.rows = rows; this.columns = columns;
		
		tiles = new AbstractTile[this.columns][this.rows]; 

		cellularAutomata();
		
		AbstractEntity e;
		entities = new ArrayList<AbstractEntity>();
		for(int i = 0; i < 10; i++) {
			e = new Bat(generate.nextInt(this.length() - 5),generate.nextInt(this.height() - 5), 10);
			tiles[e.getX()][e.getY()].setOccupant(e);
			e.setMap(this);
			entities.add(e);
		}
	}
	
	/**
	 * Creates a new Map of size columns * rows, with a display of size viewColumns * viewRows generated using the given seed.
	 * @param columns The length of the map in tiles
	 * @param rows The height of the map in tiles
	 * @param seed The seed for the random number generator to use
	 */
	public TileMap(int columns, int rows, long seed) {
		
		this.seed = seed;
		generate = new Random(seed);

		this.rows = rows; this.columns = columns;
		
		tiles = new AbstractTile[this.columns][this.rows];
		
		cellularAutomata();

//		for(int y = 0; y < this.rows; y++) {
//			for(int x = 0; x < this.columns; x++) {
//				//tiles[x][y] = generate.nextBoolean() ? (generate.nextBoolean() ? new Water(x, y) : new Soil(x, y)) : (generate.nextBoolean() ? new Magma(x, y) : new Spike(x, y));
//				//tiles[x][y] = generate.nextBoolean() ? new Soil(x, y) : (generate.nextBoolean() ? new Magma(x, y) : new Spike(x, y));
//				tiles[x][y] = generate.nextBoolean() ? new Soil(x, y) : new Spike(x, y);
//			}
//		}
		
//		update = new int[(this.columns*this.rows)/2][2];
		
		AbstractEntity e;
		entities = new ArrayList<AbstractEntity>();
		for(int i = 0; i < 10; i++) {
			e = new Bat(generate.nextInt(this.length() - 5),generate.nextInt(this.height() - 5), 10);
			tiles[e.getX()][e.getY()].setOccupant(e);
			e.setMap(this);
			entities.add(e);
		}
	}
	
	private void cellularAutomata() {
		boolean[][] plane = new boolean[columns][rows];
		boolean[][] update = new boolean[columns][rows];
		
		//int middle = 0;
		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				if(x == 0) {
					plane[x][y] = true;
				} else if(y == 0) {
					plane[x][y] = true;
				} else if(x == length() - 1) {
					plane[x][y] = true;
				} else if(y == height() - 1) {
					plane[x][y] = true;
				} else {
					
					plane[x][y] = generate.nextInt(100) < 40;
					
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
		
		print(plane);
		
		for(int i = 0; i < 4; i++) {
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					update[x][y] = placeWall(plane, x, y);
				}
			}
		
			print(update);
			
			for(int x = 1, y = 1; y < rows - 1; y++) {
				for(x = 1; x < columns - 1; x++) {
					plane[x][y] = update[x][y];
				}
			}
		}
		
		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = plane[x][y] ? new Spike(x, y) : new Soil(x, y);
			}
		}
	}
	
	private int getAdjacentWalls(boolean[][] m, int x, int y) {
		int startX = x - 1;
		int startY = y - 1;
		int endX = x + 1;
		int endY = y + 1;
 
		int iX = startX;
		int iY = startY;
 
		int wallCounter = 0;
 
		for(iY = startY; iY <= endY; iY++) {
			for(iX = startX; iX <= endX; iX++)
			{
				if(!(iX==x && iY==y))
				{
					if(isWall(m, iX, iY))
					{
						wallCounter += 1;
					}
				}
			}
		}
		return wallCounter;
	}
	
	private boolean isWall(boolean[][] m, int x,int y)
	{
		// Consider out-of-bound a wall
		if( IsOutOfBounds(x,y) )
		{
			return true;
		}
 
		if( m[x][y] == true )
		{
			return true;
		}
 
		if( m[x][y] == false )
		{
			return false;
		}
		return false;
	}
 
	private boolean IsOutOfBounds(int x, int y)
	{
		if( x<0 || y<0 )
		{
			return true;
		}
		else if( x>columns - 1 || y>rows - 1 )
		{
			return true;
		}
		return false;
	}
	
	private boolean placeWall(boolean[][] m, int x, int y) {
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
	
	private void print(boolean[][] m) {
		for(int y = 0, x = 0; y < rows; y++) {
			for(x = 0; x < columns; x++) {
				System.out.print(m[x][y] ? "#" : "-");
			}
			
			System.out.print("\n");
		}
		
		System.out.print("\n");
	}
	
	/**
	 * Gets the length of the map in tiles
	 * @return The length of map in tiles
	 */
	public int length() {
		return columns;
	}
	
	/**
	 * Gets the height of the map in tiles
	 * @return The height of the map in tiles
	 */
	public int height() {
		return rows;
	}
	
	/**
	 * Gets the tile at (x, y)
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @return The tile at (x, y)
	 */
	public AbstractTile tileAt(int x, int y) {
		return tiles[x][y];
	}
	
	/**
	 * Returns the AbstractEntity at a given index
	 * @param i The index of the AbstractEntity to get
	 * @return The AbstractEntity at index i
	 */
	public AbstractEntity getEntity(int i) {
		return entities.get(i);
	}
	
	/**
	 * Sets the seed for the map's random number generator
	 * @param The seed the random number generator
	 */
	public void setSeed(long seed) {
		this.seed = seed;
		generate.setSeed(seed);
	}
	
	/**
	 * Gets the seed of this map
	 * Note: Will always return 0 if the map was instantiated with the TileMap(int x, int y) constructor,
	 * and has not yet be regegnerated with a manually set seed
	 * @return The seed of the map's random number generator
	 */
	public long getSeed() {
		return seed;
	}
	
	/**
	 * Advances the state of the map by one round
	 * all tiles and entities run their once per turn actions once per round
	 */
	public void tick() {
		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				if(tiles[x][y].getOccupant() != null) {
					if(tiles[x][y].getOccupant().isDead()) {
						tiles[x][y].setOccupant(null);
					}
				}
				
				tiles[x][y].onTurn();
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).onTurn();
			if(entities.get(i).isDead()) {
				entities.remove(i);
			}
		}
		
		rounds++;
	}
	
	/**
	 * Advances the state of the map by a given number of rounds
	 * all tiles and entities run their once per turn actions once per round
	 */
	public void tick(int rounds) {
		for(int i = 0; i < rounds; i++) {
			tick();
		}
	}
	
	/**
	 * Returns the number of rounds executed during this map's existance
	 * @return The number of rounds
	 */
	public int getRounds() {
		return rounds;
	}

//	public void updateAt(int x, int y) {
//		update[numToUpdate][0] = x;
//		update[numToUpdate][1] = y;
//		numToUpdate++;
//	}
//	
//	public int[][] getUpdateQueue() {
//		return update;
//	}
//	
//	public void clearUpdateQueue() {
//		numToUpdate = 0;
//	}
}
