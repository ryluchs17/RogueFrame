/**
 * A map generator for RogueFrame. Credit to the people at Rogue Basin for some concepts and code samples
 * @author Ryan Luchs
 */
package gen;

import java.util.Random;
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
	
	public static AbstractTile[][] createClassicMap(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 40), Const.DEFWALL, Const.DEFFLOOR);
		shorts = ShortFunc.addCellLayer(Const.SOIL, Const.DEFFLOOR, shorts, 50);
		shorts = ShortFunc.addCellLayer(Const.MAGMA, Const.DEFFLOOR, shorts, 40);
		shorts = ShortFunc.addCellLayer(Const.SPIKE, Const.DEFFLOOR, shorts, 35);
		//shorts = addLayerOf(T_DEFAULT_FLOOR, T_MAGMA, shorts, );
		
		//write("testname.txt", shorts);
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createSafeMap(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 40), Const.DEFWALL, Const.DEFFLOOR);
		shorts = ShortFunc.addCellLayer(Const.SOIL, Const.DEFFLOOR, shorts, 50);
		shorts = ShortFunc.addCellLayer(Const.WATER, Const.DEFFLOOR, shorts, 40);
		shorts = ShortFunc.addWalkLayer(Const.DEFFLOOR, Const.SOIL, shorts, 35);
		//shorts = addLayerOf(T_DEFAULT_FLOOR, T_MAGMA, shorts, );
		
		//write("testname.txt", shorts);
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createFort(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 50), Const.DEFWALL, Const.DEFFLOOR);
//		shorts = ShortFunc.addCellLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 40);
//		shorts = ShortFunc.addCellLayer(Const.DEFWALL, Const.DEFFLOOR, shorts, 40);
		shorts = ShortFunc.addCellLayer(Const.MAGMA, Const.DEFFLOOR, shorts, 40);
		shorts = ShortFunc.addWalkLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 250);
		//shorts = ShortFunc.addCellLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 40);
		//shorts = ShortFunc.addCellLayer(Const.DEFWALL, Const.DEFFLOOR, shorts, 20);
		//shorts = ShortFunc.border(Const.MAGMA, shorts);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createMaze(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 60), Const.DEFWALL, Const.DEFFLOOR);
//		shorts = ShortFunc.addCellLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 40);
//		shorts = ShortFunc.addCellLayer(Const.DEFWALL, Const.DEFFLOOR, shorts, 40);
		shorts = ShortFunc.addWalkLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 250);
		shorts = ShortFunc.addWalkLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 250);
		shorts = ShortFunc.addWalkLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 250);
		//shorts = ShortFunc.addCellLayer(Const.DEFFLOOR, Const.DEFWALL, shorts, 40);
		//shorts = ShortFunc.addCellLayer(Const.DEFWALL, Const.DEFFLOOR, shorts, 20);
		//shorts = ShortFunc.border(Const.MAGMA, shorts);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
	
	public static AbstractTile[][] createTest(int columns, int rows) {
		short[][] shorts = ShortFunc.booleanArrayToShortArray(BoolFunc.cellularAutomaton(columns, rows, 35), Const.DEFWALL, Const.DEFFLOOR);
		
		return ShortFunc.mapFromShortArray(shorts);
	}
		
}
