import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import item.AbstractItem;
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
		this.addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		AbstractItem i;
		for(int y = 0; y < 5; y++) {
			i = items.get(y);
			
			if(i != null) {
				
				i.draw(g, AbstractTile.STEP * 2, y * AbstractTile.STEP);
				if(y == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.WHITE); };
				g2d.drawString(i.getName() + (i.isStackable() ? " (" + i.uses + ") " + i.getProficiency() : i.getProficiency()), AbstractTile.STEP * 4, (y + 1) * AbstractTile.STEP);
				
//				if(y == selected) {
//					g2d.setColor(Color.YELLOW);
//					g2d.drawString(">", AbstractTile.STEP, (y + 1) * AbstractTile.STEP);
//					g2d.drawString("<", (length_chars - 2) * AbstractTile.STEP, (y + 1) * AbstractTile.STEP);
//				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * length_chars, (AbstractTile.STEP + 2) * 5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
	public void mousePressed(MouseEvent e) {
		int mouseY = e.getY();
		
		for(int y = 0; y < 5; y++) {
			if(mouseY > y * AbstractTile.STEP && mouseY < (y + 1) * AbstractTile.STEP) {
				selected = (short) y;
				repaint();
			}
			
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int mouseY = e.getY();
		
		for(int y = 0; y < 5; y++) {
			if(mouseY > y * AbstractTile.STEP && mouseY < (y + 1) * AbstractTile.STEP) {
				items.swap(selected, y);
				repaint();
			}
			
		}
		
	}

}
