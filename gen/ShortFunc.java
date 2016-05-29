package gen;

import tile.*;

public class ShortFunc {
	
	/*
	 * replace function for random walls
	 * replace function for sprinkle
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
	
	public static short[][] booleanArrayToShortArray(boolean[][] array, short trueID, short falseID) {
		short[][] map = new short[array.length][array[0].length];
		
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				map[x][y] = array[x][y] ? trueID : falseID;
			}
		}
		
		return map;
	}
	
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
	
	public static short[][] addCellLayer(short newLayerID, short[][] array, int percentTrue) {
		boolean[][] newLayer = BoolFunc.cellularAutomaton(array.length, array[0].length, percentTrue);
		
		for(int x = 0, y = 0; y < array[0].length; y++) {
			for(x = 0; x < array.length; x++) {
				if(newLayer[x][y] && !(Const.isFilled(array[x][y]))) {
					array[x][y] = newLayerID;
				}
			}
		}
		
		return array;
	}
	
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
