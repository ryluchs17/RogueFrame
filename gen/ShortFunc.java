package gen;

import tile.*;

/**
 * A class of static functions that operate on the 2D (short) arrays used in world generation
 * @author Ryan Luchs
 *
 */
public class ShortFunc {
	
	/*
	 * replace function for random walls
	 * replace function for sprinkle
	 */
	
	/**
	 * Creates a AbstractTile[][] with tiles corresponding to a given short[][]
	 * @param array The short[][]
	 * @return The AbstractTile[][]
	 */
	public static AbstractTile[][] mapFromShortArray(short[][] array) {
		AbstractTile[][] map = new AbstractTile[array.length][array[0].length];
		
		for(int y = 0; y < array[0].length; y++) {
			for(int x = 0; x < array.length; x++) {
				map[x][y] = Const.makeTile(array[x][y], x, y);
			}
		}
		
		return map;
	}
	
	/**
	 * Converts a boolean[][] to a short[][] which given values substituted for ture and false
	 * @param array The boolean[][]
	 * @param trueID The short to replace occurrences of true with
	 * @param falseID The short to replace occurrences of false with
	 * @return The short[][]
	 */
	public static short[][] booleanArrayToShortArray(boolean[][] array, short trueID, short falseID) {
		short[][] map = new short[array.length][array[0].length];
		
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				map[x][y] = array[x][y] ? trueID : falseID;
			}
		}
		
		return map;
	}
	
	/**
	 * Randomly replaces tiles of a short[][] of replaceID with newLayerID
	 * @param newLayerID The ID number of the new layer
	 * @param replaceID The ID number to replace
	 * @param array The array
	 * @param percentTrue The percent chance to replace the tile
	 * @return The modified array
	 */
	public static short[][] addScatterLayer(short newLayerID, short replaceID, short[][] array, int percentTrue) {
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				if(array[x][y] == replaceID && Cartographer.r.nextInt(100) <= percentTrue) {
					array[x][y] = newLayerID;
				}
			}
		}
		
		return array;
	}
	
	/**
	 * Uses a cellular automation to replace tiles of a short[][] of replaceID with newLayerID
	 * @param newLayerID The ID number of the new layer
	 * @param replaceID The ID number to replace
	 * @param array The array
	 * @param percentTrue The initial true percentage of the automaton
	 * @return The modified array
	 */
	public static short[][] addCellLayer(short newLayerID, short replaceID, short[][] array, int percentTrue) {
		boolean[][] newLayer = BoolFunc.cellularAutomaton(array.length, array[0].length, percentTrue);
		
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				if(newLayer[x][y] && array[x][y] == replaceID) {
					array[x][y] = newLayerID;
				}
			}
		}
		
		return array;
	}
	
	/**
	 * Uses a random walk to replace tiles of a short[][] of replaceID with newLayerID
	 * @param newLayerID The ID number of the new layer
	 * @param replaceID The ID number to replace
	 * @param array The array
	 * @param percentTrue The percent of the random walk that should be true
	 * @return The modified array
	 */
	public static short[][] addWalkLayer(short newLayerID, short replaceID, short[][] array, int step, int percentTrue) {
		boolean[][] newLayer = BoolFunc.invert(BoolFunc.randomWalk(array.length, array[0].length, step, percentTrue));
		
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				if(newLayer[x][y] && array[x][y] == replaceID) {
					array[x][y] = newLayerID;
				}
			}
		}
		
		return array;
	}
	
	/**
	 * Adds a border of tileID around a short[][]
	 * @param tileID The ID number of the border tiles
	 * @param array The array
	 * @return The modified array
	 */
	public static short[][] border(short tileID, short[][] array) {
		
		for(int y = 0; y < array[0].length; y++) {
			array[0][y] = tileID;
			array[array.length - 1][y] = tileID;
		}
		
		for(int x = 0; x < array.length; x++) {
			array[x][0] = tileID;
			array[x][array[0].length - 1] = tileID;
		}
		
		return array;
	}
}
