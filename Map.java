import java.awt.*;
import javax.swing.*;

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
	
	public static final int STEP = 10;

	public Map(int columns, int rows) {
		super();
		
		setFocusable(true);
		
		this.rows = rows;
		this.columns = columns;
		tiles = new AbstractTile[this.columns][this.rows]; 

		for(int y = 0; y < this.rows; y++) {
			for(int x = 0; x < this.columns; x++) {
				tiles[x][y] = new Soil();
			}
		}
	}

	public AbstractTile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public int getWidthCenter() {
		return (this.getWidth()/2) - ((columns*STEP)/2);
	}
	
	public int getHeightCenter() {
		return (this.getHeight()/2) - ((rows*STEP)/2);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setFont(font);
		
		for(int y = 0; y <rows; y++) {
			for(int x = 0; x < columns; x++) {
				g.setColor(tiles[x][y].background);
				g.fillRect(getWidthCenter() + x*STEP, getHeightCenter() + y*STEP, STEP, STEP);
				g.setColor(tiles[x][y].foreground);
				g.drawString(tiles[x][y].character, getWidthCenter() + x*STEP, getHeightCenter() + (y+1)*STEP);
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((columns+1)*STEP, (rows+1)*STEP);
	}

}
