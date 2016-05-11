import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import item.Inventory;
import tile.AbstractTile;

/**
 * 
 */

/**
 * @author Ryan Luchs
 *
 */
public class InventoryPanel extends JPanel implements MouseListener{
	
	short selected = 0;
	
	Inventory items;
	
	int length_chars = 20;

	/**
	 * 
	 */
	public InventoryPanel(Inventory i) {
		super();
		
		items = i;
		
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(int y = 0; y < 5; y++) {
			if(items.get(y) != null) {
				
				items.get(y).draw(g, AbstractTile.STEP * 2, y * AbstractTile.STEP);
				g2d.setColor(AbstractTile.TOOLTIP_ITEM);
				g2d.drawString(items.get(y).getName() + ": " + items.get(y).getDescription(), AbstractTile.STEP * 4, (y + 1) * AbstractTile.STEP);
				
				if(y == selected) {
					g2d.setColor(Color.YELLOW);
					g2d.drawString(">", 10, 10);
					g2d.drawString("<", (length_chars - 2) * AbstractTile.STEP, y * AbstractTile.STEP);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * length_chars, AbstractTile.STEP * 5);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
