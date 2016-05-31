import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import entity.*;
import tile.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * RougeFrame Map display panel
 * @author Ryan Luchs
 *
 */

public class MapPanel extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	// the map displayed by this MapPanel
	TileMap map;
	
	// the player
	private AbstractEntity player;
	
	// point to draw look info at
	private Point mouse = new Point(0, 0);
	
	// selected tile
	private AbstractTile select;
	
	// for mouse detection in tiles
	Rectangle bounds = new Rectangle();
	
	//private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	//private int AbstractTile.STEP = font.getSize();
	
	private StatusPanel selectDisplay;
	
	// (x, y) position of the top left corner of the map display
	private int viewX = 0; private int viewY = 0;
	// length and height of the map display
	private int viewColumns; private int viewRows;
	
	// the font to display as
	public static final Font font = new Font("Monospaced", Font.PLAIN, 12);

	/**
	 * Creates a new MapPanel of the TileMap map with a window size of viewColumns * viewRows with a designated player
	 * @param map
	 * @param player
	 * @param viewColumns
	 * @param viewRows
	 */
	public MapPanel(TileMap map, AbstractEntity player, int viewColumns, int viewRows) {
		super();
		
		setFocusable(true);
		
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		this.viewColumns = viewColumns;
		this.viewRows = viewRows;

		this.map = map;
		this.player = player;
		
		this.viewColumns = viewColumns; this.viewRows = viewRows;
		
		select = map.tileAt(0, 0);
		
		this.addMouseMotionListener(this);
	}
	
	/**
	 * Sets a status panel that displays AbstractEntity into on mouseover
	 * @param s the status panel to link to
	 */
	public void setStatusPanel(StatusPanel s) {
		selectDisplay = s;
	}
	
	/**
	 * Sets the TileMap for this pane to display
	 * @param map The TileMap to display
	 */
	public void setMap(TileMap map) {
		this.map = map;
		repaint();
	}
	
	/**
	 * Gets the TileMap this pane is displaying
	 * @return The TileMap being displayed
	 */
	public TileMap getMap() {
		return map;
	}
	
	/**
	 * Gets the AbstractTile at the mouse's current position
	 * @return The AbstractTile at the mouse's current position
	 */
	public AbstractTile getSelect() {
		return select;
	}
	
	/**
	 * Advances the state of the TileMap r number of rounds
	 * @param r The number of rounds
	 */
	public void rounds(int r) {
		
//		for(int x = 0, y = 0; y < map.height(); y++) {
//			for(x = 0; x < map.length(); x++) {
//				map.tileAt(x, y).setCovered(false);
//			}
//		}
		
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
		repaint(this.getWidthCenter() - 10, this.getHeightCenter() - 10, viewX + ((viewColumns+1)*AbstractTile.STEP), viewY + ((viewRows+1)*AbstractTile.STEP));
	}
	
	/**
	 * Centers the viewing window on the given point as best as possible without going out of range
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 */
	public void center(int x, int y) {
		Rectangle view = new Rectangle(viewY, viewY, viewColumns, viewRows);
		
		while(!view.contains(x, y)) {
			if(viewX - 1 > 0 && x < viewX) viewX--;
			if(viewX + 1 < map.length() && x > viewX) viewX++;
			if(viewY - 1 < 0 && y < viewY) viewY--;
			if(viewY + 1 < map.height() && y > viewY)viewY++;
		}
		
		repaint();
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
				
				//map.getEntities().get(0).canSee(viewX + x, viewY + y);
				
				//if(map.tileAt(viewX + x, viewY + y).isCovered()) {
				if(player.canSee(viewX + x, viewY + y)) {
					map.tileAt(viewX + x, viewY + y).draw(g, this.getWidthCenter() + x*AbstractTile.STEP - 2, getHeightCenter() + y*AbstractTile.STEP - 2);
				}
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
					if(selectDisplay != null) { //TODO remove if needed
						selectDisplay.setOwner(select.getOccupant());
						selectDisplay.repaint();
					}
					
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
