import java.awt.*;
import javax.swing.*;

/**
 * RougeFrame map format
 * @author Ryan Luchs
 *
 */

public class Map extends JPanel{

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
		
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	public AbstractTile getTileAt(int x, int y) {
		return tiles[x][y];
	}
	
	public void paintComponent(Graphics g) {
		//g.setFont(font);
		
		for(int y = 0; y <rows; y++) {
			for(int x = 0; x < columns; x++) {
				g.setColor(tiles[x][y].background);
				g.fillRect(x*STEP, y*STEP, STEP, STEP);
				g.setColor(tiles[x][y].foreground);
				g.drawString(tiles[x][y].character, x*STEP, (y+1)*STEP);
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((columns+1)*STEP, (rows+1)*STEP);
	}

}
