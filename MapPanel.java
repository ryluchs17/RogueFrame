import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import entity.*;
import tile.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * RougeFrame map template
 * @author Ryan Luchs
 *
 */

public class MapPanel extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	// the map displayed by this MapPanel
	TileMap map;
	
	// point to draw look info at
	private Point mouse = new Point(0, 0);
	
	// selected tile
	private AbstractTile select;
	
	// for mouse detection in tiles
	Rectangle bounds = new Rectangle();
	
	//private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	//private int AbstractTile.STEP = font.getSize();
	
	// (x, y) position of the top left corner of the map display
	private int viewX = 0; private int viewY = 0;
	// length and height of the map display
	private int viewColumns; private int viewRows;
	
	// the font to display as
	private static final Font font = new Font("Monospaced", Font.PLAIN, 12);

	/**
	 * Creates a new Map of size columns * rows, with a display of size viewColumns * viewRows.
	 * @param columns The length of the map in tiles
	 * @param rows The height of the map in tiles
	 * @param viewColumns The length of the map display in tiles
	 * @param viewRows The height of the map display in tiles
	 */
	public MapPanel(int columns, int rows, int viewColumns, int viewRows) {
		super();
		
		setFocusable(true);
		
		this.viewColumns = viewColumns;
		this.viewRows = viewRows;

		map = new TileMap(columns, rows);
		
		this.viewColumns = viewColumns; this.viewRows = viewRows;
		
		select = map.tileAt(0, 0);
		
		this.addMouseMotionListener(this);
	}
	
	/**
	 * Creates a new Map of size columns * rows, with a display of size viewColumns * viewRows generated using the given seed.
	 * @param columns The length of the map in tiles
	 * @param rows The height of the map in tiles
	 * @param viewColumns The length of the map display in tiles
	 * @param viewRows The height of the map display in tiles
	 */
	public MapPanel(int columns, int rows, int viewColumns, int viewRows, long seed) {
		super();

		setFocusable(true);
		
		this.viewColumns = viewColumns;
		this.viewRows = viewRows;

		map = new TileMap(columns, rows, seed);
		
		select = map.tileAt(0, 0);
		
		this.addMouseMotionListener(this);
	}
	
	public void setMap(TileMap map) {
		this.map = map;
		repaint();
	}
	
	public TileMap getMap() {
		return map;
	}
	
	public void rounds(int r) {
		
		for(int x = 0, y = 0; y < map.height(); y++) {
			for(x = 0; x < map.length(); x++) {
				map.tileAt(x, y).setCovered(false);
			}
		}
		
		map.tick(r);
		
//		int x;
//		int y;
//		for(int i = 0; i < map.getUpdateQueue().length; i++) {
//			x = map.getUpdateQueue()[i][0];
//			y = map.getUpdateQueue()[i][1];
//			repaint(this.getWidthCenter() + x*AbstractTile.STEP, this.getHeightCenter() + y*AbstractTile.STEP, AbstractTile.STEP, AbstractTile.STEP);
//		}
//		
//		map.clearUpdateQueue();
		
		// repaint tooltip to make sure that the box is the correct size
		repaint(mouse.x, mouse.y, mouse.x + select.getTooltipLength(), select.getTooltipHeight());
				
		// repaint tiles
		repaint(this.getWidthCenter(), this.getHeightCenter(), viewX + ((viewColumns+1)*AbstractTile.STEP), viewY + ((viewRows+1)*AbstractTile.STEP));
	}

	/**
	 * Gets the tile at (x, y)
	 * @param x The x coordinate of the tile
	 * @param y The y coordinate of the tile
	 * @return The tile at (x, y)
	 */
	public AbstractTile getTileAt(int x, int y) {
		return map.tileAt(x, y);
	}
	
	/**
	 * Gets the x coordinate that center that map on the x-axis
	 * @return The x coordinate that center that map on the x-axis
	 */
	private int getWidthCenter() {
		return (this.getWidth()/2) - ((viewColumns*AbstractTile.STEP)/2);
	}
	
	/**
	 * Gets the y coordinate that center that map on the y-axis
	 * @return The y coordinate that center that map on the y-axis
	 */
	private int getHeightCenter() {
		return (this.getHeight()/2) - ((viewRows*AbstractTile.STEP)/2);
	}
	
	/**
	 * Sets the position of the top left corner of the display at (x, y)
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void setView(int x, int y) {
		if(viewX > -1 && viewX < map.length() - viewColumns + 1) {
			viewX = x;
		}
		if(viewY > -1 && viewY < map.height() - viewRows + 1) {
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
		
		
		if(viewX + xOffset > -1 && viewX + xOffset < map.length() - viewColumns + 1) {
			viewX += xOffset;
		}
		if(viewY + yOffset > -1 && viewY + yOffset < map.height() - viewRows + 1) {
			viewY += yOffset;
		}
		
		// repaint tooltip to make sure that the box is the correct size
		repaint(mouse.x, mouse.y, mouse.x + select.getTooltipLength(), select.getTooltipHeight());
		
		// repaint tiles
		repaint(this.getWidthCenter(), this.getHeightCenter(), viewX + ((viewColumns+1)*AbstractTile.STEP), viewY + ((viewRows+1)*AbstractTile.STEP));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		
		for(int x = 0, y = 0; y < viewRows; y++) {
			for(x = 0; x < viewColumns; x++) {
//				g.setColor(tiles[viewX + x][viewY + y].background);
//				g.fillRect(getWidthCenter() + x*AbstractTile.STEP, getHeightCenter() + y*AbstractTile.STEP, AbstractTile.STEP, AbstractTile.STEP);
//				g.setColor(tiles[viewX + x][viewY + y].foreground);
//				g.drawString(tiles[viewX + x][viewY + y].character, getWidthCenter() + x*AbstractTile.STEP, getHeightCenter() + (y+1)*AbstractTile.STEP);
				
				map.tileAt(viewX + x, viewY + y).draw(g, this.getWidthCenter() + x*AbstractTile.STEP, getHeightCenter() + y*AbstractTile.STEP);
			}
		}
		
//		g.setColor(Color.WHITE);
//		g.drawString("TL: (" + viewX + ", " + viewY
//				+ ") TR: (" + (viewColumns + viewX + -1) + ", " + viewY
//				+ ") BL: (" + viewX + ", " + (viewRows + viewY + -1)
//				+ ") BR: (" + (viewColumns + viewX + -1) + ", " + (viewRows + viewY + -1)
//				+ ")", 0, 10);
		
		for(int y = 0; y < viewRows; y++) {
			for(int x = 0; x < viewColumns; x++) {
				bounds.setBounds(getWidthCenter() + x*AbstractTile.STEP, getHeightCenter() + y*AbstractTile.STEP, AbstractTile.STEP, AbstractTile.STEP);
				
				if(mouse != null && bounds.contains(mouse)) {
					map.tileAt(viewX + x, viewY + y).drawTooltip(g, mouse.x, mouse.y);
					select = map.tileAt(viewX + x, viewY + y);
					
//					g.setColor(Color.WHITE);
//					g.drawString("mouse @ (" + (viewX + x) + ", " + (viewY + y) + ")", 0, 20);
					
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((viewColumns+1)*AbstractTile.STEP, (viewRows+1)*AbstractTile.STEP);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.repaint(mouse.x, mouse.y, mouse.x + select.getTooltipLength(), select.getTooltipHeight());
		mouse = e.getPoint();
		this.repaint(mouse.x, mouse.y, mouse.x + select.getTooltipLength(), select.getTooltipHeight());
		//repaint();
		
//		repaint(0, 0, this.getWidth(), 20);
	}

}
