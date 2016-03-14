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
	
	private int columns; private int rows;
	private AbstractTile[][] tiles;
	//private Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	//private int STEP = font.getSize();
	
	private int viewX; private int viewY;
	private int viewColumns; private int viewRows;
	
	public static final int STEP = 10;

	public Map(int columns, int rows) {
		super();
		
		Random r = new Random();
		
		setFocusable(true);
		
		viewX = 5; viewY = 5;
		viewColumns = 18; viewRows = 12;
		
		this.rows = rows;
		this.columns = columns;
		tiles = new AbstractTile[this.columns][this.rows]; 

		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = r.nextBoolean() ? new Soil() : new Water();
			}
		}
	}

	public AbstractTile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public int getWidthCenter() {
		return (this.getWidth()/2) - ((viewColumns*STEP)/2);
	}
	
	public int getHeightCenter() {
		return (this.getHeight()/2) - ((viewRows*STEP)/2);
	}
	
	public void shiftView(int xOffset, int yOffset) {
		if(viewX + xOffset > -1 && viewX + xOffset < columns - viewColumns + 1) {
			viewX += xOffset;
		}
		if(viewY + yOffset > -1 && viewY + yOffset < rows - viewRows + 1) {
			viewY += yOffset;
		}
		
		repaint();
	}
	
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
	
	public Dimension getPreferredSize() {
		return new Dimension((viewColumns+1)*STEP, (viewRows+1)*STEP);
	}

}
