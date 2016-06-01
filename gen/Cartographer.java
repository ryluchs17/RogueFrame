/**
 * A map generator for RogueFrame. Credit to the people at Rogue Basin for some concepts and code samples
 * @author Ryan Luchs
 */
package gen;

import java.util.ArrayList;
import java.util.Random;

import entity.AbstractEntity;
import tile.*;
//import java.io.*;

/**
 * A map generator for RogueFrame
 * @author Ryan Luchs
 */
public class Cartographer {

	/**
	 * The random object used in all of the classes in the gen package
	 */
	public static Random r = new Random();
	
	// ! SPECIFIC GENERATION FUNCTIONS !
	
	/**
	 * Generates a cave style map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createCave(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 40), Const.STONE_WALL, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.WATER, Const.STONE_FLOOR, shorts, 40);
		
		shorts = ShortFunc.addScatterLayer(Const.WEB, Const.STONE_FLOOR, shorts, 5);
		shorts = ShortFunc.addScatterLayer(Const.TRAP, Const.STONE_FLOOR, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a forest style map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createForest(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 55), Const.TREE, Const.GRASS);

		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 8, 50);
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 4, 20);
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 2, 30);
		
		shorts = ShortFunc.addCellLayer(Const.BUSH, Const.GRASS, shorts, 40);
		
		shorts = ShortFunc.addScatterLayer(Const.SNARE, Const.GRASS, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a maze style map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createMaze(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 55), Const.STONE_WALL, Const.STONE_FLOOR);
//		shorts = ShortFunc.addCellLayer(Const.STONE_FLOOR, Const.STONE_WALL, shorts, 40);
//		shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.STONE_FLOOR, shorts, 40);
		shorts = ShortFunc.addWalkLayer(Const.STONE_FLOOR, Const.STONE_WALL, shorts, 8, 50);
		shorts = ShortFunc.addWalkLayer(Const.STONE_FLOOR, Const.STONE_WALL, shorts, 4, 20);
		shorts = ShortFunc.addWalkLayer(Const.STONE_FLOOR, Const.STONE_WALL, shorts, 2, 30);
		//shorts = ShortFunc.addCellLayer(Const.STONE_FLOOR, Const.STONE_WALL, shorts, 40);
		//shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.STONE_FLOOR, shorts, 20);
		//shorts = ShortFunc.border(Const.MAGMA, shorts);
		
		shorts = ShortFunc.addScatterLayer(Const.SPIKE, Const.STONE_FLOOR, shorts, 5);
		
		shorts = ShortFunc.addScatterLayer(Const.WEB, Const.STONE_FLOOR, shorts, 1);
		shorts = ShortFunc.addScatterLayer(Const.TRAP, Const.STONE_FLOOR, shorts, 1);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a lava tube style map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createLavaTube(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 55), Const.OBSIDIAN, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.OBSIDIAN, shorts, 30);
		shorts = ShortFunc.addCellLayer(Const.MAGMA, Const.OBSIDIAN, shorts, 55);
		
		shorts = ShortFunc.addWalkLayer(Const.STONE_FLOOR, Const.MAGMA, shorts, 8, 50);
		
		shorts = ShortFunc.addScatterLayer(Const.OBSIDIAN, Const.STONE_WALL, shorts, 10);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a island style map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createIsland(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 45), Const.WATER, Const.GRASS);
		shorts = ShortFunc.addCellLayer(Const.BUSH, Const.GRASS, shorts, 40);
		
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.WATER, shorts, 12, 10);
		
		shorts = ShortFunc.addScatterLayer(Const.SNARE, Const.GRASS, shorts, 5);
		
		shorts = ShortFunc.addCellLayer(Const.DEEP_WATER, Const.WATER, shorts, 20);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a boss map
	 * @param columns The length of map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createBoss(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 50), Const.OBSIDIAN, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.OBSIDIAN, shorts, 35);
		
		shorts = ShortFunc.addScatterLayer(Const.SPIKE, Const.STONE_FLOOR, shorts, 5);
		shorts = ShortFunc.addScatterLayer(Const.TRAP, Const.STONE_FLOOR, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	/**
	 * Generates a mostly empty map useful for testing features
	 * @param columns The length of the map
	 * @param rows The height of the map
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] createTest(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 35), Const.STONE_WALL, Const.STONE_FLOOR);
		
		return ShortFunc.mapFromShortArray(shorts);
	}

	/**
	 * Generates a map type matching the given ID number
	 * @param columns The length of the map
	 * @param rows The height of the map
	 * @param levelTypeID The ID number to generate
	 * @return The map as an AbstractTile[][]
	 */
	public static AbstractTile[][] makeMap(int columns, int rows, short levelTypeID) {
		switch(levelTypeID) {
		
			case Const.CAVE:
				return createCave(rows, columns);
				
			case Const.FOREST:
				return createForest(rows, columns);
				
			case Const.MAZE:
				return createMaze(rows, columns);
				
			case Const.LAVA_TUBE:
				return createLavaTube(rows, columns);
				
			case Const.ISLAND:
				return createIsland(rows, columns);
				
			case Const.BOSS:
				return createBoss(rows, columns);
				
			default:
				return createBoss(rows, columns);
		}
	}

	/**
	 * Adds a number of AbstractEntities of a specific ID to a given ArrayList<AbstractEntity>
	 * @param id The ID number of the AbstractEntities to add
	 * @param level The level of theAbstractEntities
	 * @param min The minimum number of AbstractEntities
	 * @param max The maximum number of AbstractEntities
	 * @param map The map these AbstractEntities will exist in
	 * @param ents The ArrayList<AbstractEntity> to add these AbstractEntities to
	 */
	public static void addEntities(short id, int level, int min, int max, TileMap map, ArrayList<AbstractEntity> ents) {
		for(int i = 0; i < min + r.nextInt((max - min) + 1); i++) {
			ents.add(Const.makeEntity(id, level, map));
		}
	}
	
	/**
	 * Population method for a cave map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateCave(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.SPIDER, level, 3, 5, map, population);
		addEntities(Const.GIANT_BAT, level, 0, 3, map, population);
		addEntities(Const.BEAR, level, 0, 2, map, population);
		addEntities(Const.DRAGON, level, 0, 1, map, population);
		
		return population;
	}
	
	/**
	 * Population method for a forest map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateForest(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.TICK, level, 2, 3, map, population);
		addEntities(Const.BEAR, level, 0, 2, map, population);
		addEntities(Const.OWL, level, 0, 2, map, population);
		
		return population;
	}
	
	/**
	 * Population method for a maze map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateMaze(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.GIANT_BAT, level, 2, 3, map, population);
		addEntities(Const.SPIDER, level, 4, 6, map, population);
		
		return population;
	}
	
	/**
	 * Population method for a lava tube map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateLavaTube(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.SALAMANDER, level, 0, 3, map, population);
		addEntities(Const.FIRE_ELEMENTAL, level, 1, 3, map, population);
		addEntities(Const.EGG, level, 0, 3, map, population);
		addEntities(Const.DRAGON, level, 0, 1, map, population);
		
		return population;
	}
	
	/**
	 * Population method for an island map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateIsland(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.WATER_ELEMENTAL, level, 1, 3, map, population);
		addEntities(Const.SALAMANDER, level, 3, 7, map, population);
		
		return population;
	}
	
	/**
	 * Population method for a boss map
	 * @param map The map to populate
	 * @param depth The depth of the level
	 * @param playerLevel the level of the player
	 * @return the ArrayList<AbstractEntity> for the given TileMap
	 */
	public static ArrayList<AbstractEntity> populateBoss(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.EGG, level + 5, 1, 1, map, population);
		
		return population;
	}

	/**
	 * Populates a map type matching the given ID number
	 * @param levelTypeID The ID number to populate
	 * @param depth The depth of the level
	 * @param playerLevel The level of the player
	 * @return The ArrayList<AbstractEntity> for the given TileaMap
	 */
	public static ArrayList<AbstractEntity> populate(TileMap map, short levelTypeID, int depth, int playerLevel) {
		switch(levelTypeID) {
			case Const.CAVE:
				return populateCave(map, depth, playerLevel);
				
			case Const.FOREST:
				return populateForest(map, depth, playerLevel);
				
			case Const.MAZE:
				return populateMaze(map, depth, playerLevel);
				
			case Const.LAVA_TUBE:
				return populateLavaTube(map, depth, playerLevel);
				
			case Const.ISLAND:
				return populateIsland(map, depth, playerLevel);
				
			case Const.BOSS:
				return populateBoss(map, depth, playerLevel);
				
			default:
				return populateCave(map, depth, playerLevel);
		}
	}

	public static void fill(AbstractTile[][] tiles) {
		// TODO Auto-generated method stub
		
	}
		
}
