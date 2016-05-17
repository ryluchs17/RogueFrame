import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
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
	
	public final static short MODE_SELECT = 0;
	public final static short MODE_INFO = 1;
	
	private short mode = 1;
	
	private static Rectangle equiptbutton = new Rectangle(0, 0, AbstractTile.STEP * 2, AbstractTile.STEP);
	private static Rectangle infobutton = new Rectangle(0, AbstractTile.STEP * 2, AbstractTile.STEP * 2, AbstractTile.STEP);
	
	private short selected = 0;
	
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
		//this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		AbstractItem i;
		switch(mode) {
		
			case MODE_SELECT:
				for(int y = 0; y < 5; y++) {
					i = items.get(y);
					
					if(i != null) {
						i.draw(g, AbstractTile.STEP * 2, y * AbstractTile.STEP);
						if(y == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.WHITE); };
						g2d.drawString(i.getName() + (i.isStackable() ? (" (" + i.uses + ") ") : " "), AbstractTile.STEP * 4, (y + 1) * AbstractTile.STEP);
					}
				}
				
				if(items.hasEquipt()) {
					g2d.setColor(Color.YELLOW);
					g2d.drawString("Eq", equiptbutton.x, equiptbutton.y + 10);
				} else {
					g2d.setColor(Color.GRAY);
					g2d.drawString("---", equiptbutton.x, equiptbutton.y + 10);
				}
				
				g2d.setColor(Color.YELLOW);
				g2d.drawString("(?)", infobutton.x, infobutton.y);
				break;
		
			case MODE_INFO:
				items.get(selected).drawText(g, 0, 0);
				
				break;
				
			default:
				break;
				
		}
				
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * length_chars, (AbstractTile.STEP + 2) * 5);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
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
		switch(mode) {
			case MODE_SELECT:
				Point mouse = e.getPoint();
				
				if(equiptbutton.contains(mouse)) {
					items.equip();
					repaint();
				}
				
				if(infobutton.contains(mouse)) {
					mode = MODE_INFO;
					repaint();
				}
				
				for(int y = 0; y < 5; y++) {
					if(mouse.y > y * AbstractTile.STEP && mouse.y < (y + 1) * AbstractTile.STEP && selected != y) {
						selected = (short) y;
						repaint();
					}
				}
				break;
				
			case MODE_INFO:
				mode = MODE_SELECT;
				break;
			
			default:
				break;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mode == this.MODE_SELECT) {
			int mouseY = e.getY();
			
			for(int y = 0; y < 5; y++) {
				if(mouseY > y * AbstractTile.STEP && mouseY < (y + 1) * AbstractTile.STEP && selected != y) {
					items.swap(selected, y);
					selected = (short) y;
					repaint();
				}
				
			}
		}
		
	}

}
