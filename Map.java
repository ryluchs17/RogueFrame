/**
 * RougeFrame map format
 * @author Ryan Luchs
 *
 */

import java.util.ArrayList;

public class Map {

	private AbstractTile[] tiles;
	private int length; private int height;

	public Map(int h, int l) {
		height = h;
		length = l;
		tiles = new AbstractTile[l*h]; 

		for(int i = 0; i < tiles.length; i++) {
			tiles[i] = new Soil();
		}
	}

	public AbstractTile getTileAt(int i) {
		return tiles[i];
	}

}
