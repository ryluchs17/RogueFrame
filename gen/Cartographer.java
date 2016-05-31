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
 * @author SJHSStudent
 *
 */
public class Cartographer {

	public static Random r;
	
//	public static void write(String filename, short[][] array) {
//		File file = new File(filename);
//		try {
//			PrintWriter writer = new PrintWriter(file);
//			
//			writer.print(array.length + "x" + array[0].length + ":");
//			
//			for(int x = 0, y = 0; y < array[0].length; y++) {
//				for(x = 0; x < array.length; x++) {
//					writer.print(array[x][y] + ", ");
//				}
//			}
//			
//			writer.close();
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	// ! SPECIFIC GENERATION FUNCTIONS !
	
	public static AbstractTile[][] createCave(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 40), Const.STONE_WALL, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.WATER, Const.STONE_FLOOR, shorts, 40);
		
		shorts = ShortFunc.addScatterLayer(Const.WEB, Const.STONE_FLOOR, shorts, 5);
		shorts = ShortFunc.addScatterLayer(Const.TRAP, Const.STONE_FLOOR, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createForest(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 55), Const.TREE, Const.GRASS);

		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 8, 50);
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 4, 20);
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.TREE, shorts, 2, 30);
		
		shorts = ShortFunc.addCellLayer(Const.BUSH, Const.GRASS, shorts, 40);
		
		shorts = ShortFunc.addScatterLayer(Const.SNARE, Const.GRASS, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
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
	
	public static AbstractTile[][] createLavaTube(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 55), Const.OBSIDIAN, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.OBSIDIAN, shorts, 30);
		shorts = ShortFunc.addCellLayer(Const.MAGMA, Const.OBSIDIAN, shorts, 55);
		
		shorts = ShortFunc.addWalkLayer(Const.STONE_FLOOR, Const.MAGMA, shorts, 8, 50);
		
		shorts = ShortFunc.addScatterLayer(Const.OBSIDIAN, Const.STONE_WALL, shorts, 10);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createIsland(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 45), Const.WATER, Const.GRASS);
		shorts = ShortFunc.addCellLayer(Const.BUSH, Const.GRASS, shorts, 40);
		
		shorts = ShortFunc.addWalkLayer(Const.GRASS, Const.WATER, shorts, 12, 10);
		
		shorts = ShortFunc.addScatterLayer(Const.SNARE, Const.GRASS, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createBoss(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 50), Const.OBSIDIAN, Const.STONE_FLOOR);
		shorts = ShortFunc.addCellLayer(Const.STONE_WALL, Const.OBSIDIAN, shorts, 35);
		
		shorts = ShortFunc.addScatterLayer(Const.SPIKE, Const.STONE_FLOOR, shorts, 5);
		shorts = ShortFunc.addScatterLayer(Const.TRAP, Const.STONE_FLOOR, shorts, 5);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createTest(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 35), Const.STONE_WALL, Const.STONE_FLOOR);
		
		return ShortFunc.mapFromShortArray(shorts);
	}

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

	public static void addEntities(short id, int level, int min, int max, TileMap map, ArrayList<AbstractEntity> ents) {
		for(int i = 0; i < min + r.nextInt((max - min) + 1); i++) {
			ents.add(Const.makeEntity(id, 0, 0, level, map));
		}
	}
	
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
	
	public static ArrayList<AbstractEntity> populateForest(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.TICK, level, 2, 3, map, population);
		addEntities(Const.BEAR, level, 0, 2, map, population);
		addEntities(Const.OWL, level, 0, 2, map, population);
		
		return population;
	}
	
	public static ArrayList<AbstractEntity> populateMaze(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.BAT, level, 4, 6, map, population);
		addEntities(Const.GIANT_BAT, level, 2, 3, map, population);
		addEntities(Const.SPIDER, level, 4, 6, map, population);
		
		return population;
	}
	
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
	
	public static ArrayList<AbstractEntity> populateIsland(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.WATER_ELEMENTAL, level, 1, 3, map, population);
		addEntities(Const.SALAMANDER, level, 3, 7, map, population);
		
		return population;
	}
	
	public static ArrayList<AbstractEntity> populateBoss(TileMap map, int depth, int playerLevel) {
		ArrayList<AbstractEntity> population = new ArrayList<AbstractEntity>();
		int level = (depth + playerLevel)/2 + 1;
		
		addEntities(Const.EGG, level + 5, 1, 1, map, population);
		
		return population;
	}

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
