import java.awt.*;
import javax.swing.*;
import java.util.Random;

/**
 * RougeFrame map format
 * @author Ryan Luchs
 *
 */

public class Map extends JPanel{

	private static final long serialVersionUID = 1L;
	
	// length and height of map
	private int columns; private int rows;
	
	// array to store map tiles
	private AbstractTile[][] tiles;
	
	//private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	//private int STEP = font.getSize();
	
	// (x, y) position of the top left corner of the map display
	private int viewX = 0; private int viewY = 0;
	// length and height of the map display
	private int viewColumns; private int viewRows;
	
	/**
	 * STEP^2 is the area of a tile in pixels
	 */
	public static final int STEP = 10;

	/**
	 * Creates a new Map of size columns * rows, with a display of size viewColumns * viewRows.
	 * @param columns The length of the map in tiles
	 * @param rows The height of the map in tiles
	 * @param viewColumns The length of the map display in tiles
	 * @param viewRows The height of the map display in tiles
	 */
	public Map(int columns, int rows, int viewColumns, int viewRows) {
		super();
		
		Random r = new Random();
		
		setFocusable(true);

		this.rows = rows; this.columns = columns;
		this.viewColumns = 18; this.viewRows = 12;
		
		tiles = new AbstractTile[this.columns][this.rows]; 

		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = r.nextBoolean() ? new Soil() : new Water();
			}
		}
	}

	/**
	 * Gets the tile at (x, y)
	 * @param x The x coordinate of the tile
	 * @param y The y coordinate of the tile
	 * @return The tile at (x, y)
	 */
	public AbstractTile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	/**
	 * Gets the x coordinate that center that map on the x-axis
	 * @return The x coordinate that center that map on the x-axis
	 */
	private int getWidthCenter() {
		return (this.getWidth()/2) - ((viewColumns*STEP)/2);
	}
	
	/**
	 * Gets the y coordinate that center that map on the y-axis
	 * @return The y coordinate that center that map on the y-axis
	 */
	private int getHeightCenter() {
		return (this.getHeight()/2) - ((viewRows*STEP)/2);
	}
	
	/**
	 * Sets the position of the top left corner of the display at (x, y)
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void setView(int x, int y) {
		if(viewX > -1 && viewX < columns - viewColumns + 1) {
			viewX = x;
		}
		if(viewY > -1 && viewY < rows - viewRows + 1) {
			viewY = y;
		}
		
		repaint();
	}
	
	/**
	 * Shifts the position of the display by the given offsets
	 * @param xOffset The offset for the x-axis
	 * @param yOffset The offset for the y-axis
	 */
	public void shiftView(int xOffset, int yOffset) {
		if(viewX + xOffset > -1 && viewX + xOffset < columns - viewColumns + 1) {
			viewX += xOffset;
		}
		if(viewY + yOffset > -1 && viewY + yOffset < rows - viewRows + 1) {
			viewY += yOffset;
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setFont(font);
		
		for(int y = 0; y < viewRows; y++) {
			for(int x = 0; x < viewColumns; x++) {
				g.setColor(tiles[viewX + x][viewY + y].background);
				g.fillRect(getWidthCenter() + x*STEP, getHeightCenter() + y*STEP, STEP, STEP);
				g.setColor(tiles[viewX + x][viewY + y].foreground);
				g.drawString(tiles[viewX + x][viewY + y].character, getWidthCenter() + x*STEP, getHeightCenter() + (y+1)*STEP);
			}
		}
		
//		g.setColor(Color.WHITE);
//		g.drawString("TL: (" + viewX + ", " + viewY
//				+ ") TR: (" + (viewColumns + viewX + -1) + ", " + viewY
//				+ ") BL: (" + viewX + ", " + (viewRows + viewY + -1)
//				+ ") BR: (" + (viewColumns + viewX + -1) + ", " + (viewRows + viewY + -1)
//				+ ")", 0, 10);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((viewColumns+1)*STEP, (viewRows+1)*STEP);
	}

}
