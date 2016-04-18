package tile;

import java.util.ArrayList;
import java.util.Random;
import entity.AbstractEntity;
import entity.Hidden;

public class TileMap {

	// length and height of map
	private int columns; private int rows;
	
	// random number generator for map gen
	private Random generate;
	
	// array to store map tiles
	private AbstractTile[][] tiles;
	
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

		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = generate.nextBoolean() ? new Soil(x, y) : new Water(x, y);
			}
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

		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = generate.nextBoolean() ? new Soil(x, y) : new Water(x, y);
			}
		}
		
		AbstractEntity e = new Hidden(5, 5, 20);
		tiles[e.getX()][e.getY()].setOccupant(e);
		entities = new ArrayList<AbstractEntity>();
		entities.add(e);
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
	 * @param y Hhe y-coordinate
	 * @return The tile at (x, y)
	 */
	public AbstractTile tileAt(int x, int y) {
		return tiles[x][y];
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
				tiles[x][y].onTurn();
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).onTurn(tiles);
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
}
