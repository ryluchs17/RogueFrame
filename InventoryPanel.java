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
	
	/* 
	 * Buttons I need:
	 * - information
	 * - drop/swap
	 * - use
	 * - throw
	 * 
	 */
	
	
	private short selected = 0;
	
	private Inventory items;
	
	public boolean editable = false;
	
	public final static short MODE_SELECT = 0;
	public final static short MODE_INFO = 1;
	
	private short mode = 1;
	
	private static final int LENGTH_CHARS = 20;
	
	private static Rectangle equiptbutton = new Rectangle(0, 0, AbstractTile.STEP * 2, AbstractTile.STEP);
	private static Rectangle details = new Rectangle(0, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
	private static Rectangle swap = new Rectangle((LENGTH_CHARS/4) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
	private static Rectangle use = new Rectangle((LENGTH_CHARS/2) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
	private static Rectangle throwr = new Rectangle(((3 * LENGTH_CHARS)/4) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);

	/**
	 * 
	 */
	public InventoryPanel(Inventory i) {
		super();
		
		items = i;
		
		this.setBackground(Color.BLACK);
		this.addMouseListener(this);
//		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(MapPanel.font);
		
		AbstractItem i;
		switch(mode) {
		
			case MODE_SELECT:
				for(int y = 0; y < 5; y++) {
					i = items.get(y);
					
					if(i != null) {
						i.draw(g, (this.getWidth()/2) - AbstractTile.STEP * 4, y * AbstractTile.STEP);
						if(y == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.WHITE); };
						g2d.drawString(i.getName() + (i.isStackable() ? (" (" + i.uses + ") ") : " "), (this.getWidth()/2) - AbstractTile.STEP * 2, (y + 1) * AbstractTile.STEP);
					}
				}
				
				if(items.hasEquipt()) {
					g2d.setColor(editable ? Color.YELLOW : Color.GRAY);
					g2d.drawString("Eq", equiptbutton.x, equiptbutton.y + 10);
				} else {
					g2d.setColor(Color.GRAY);
					g2d.drawString("---", equiptbutton.x, equiptbutton.y + 10);
				}
				
				g2d.setColor(Color.YELLOW);
				
				g2d.drawString("Details", details.x, details.y + AbstractTile.STEP);
				
				g2d.setColor(editable ? Color.YELLOW : Color.GRAY);
				
				g2d.drawString("Swap", swap.x, swap.y + AbstractTile.STEP);
				g2d.drawString("Use", use.x, use.y + AbstractTile.STEP);
				g2d.drawString("Throw", throwr.x, throwr.y + AbstractTile.STEP);
				
				break;
		
			case MODE_INFO:
				if(items.get(selected) == null) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("Nothing selected", 0, AbstractTile.STEP);
				} else {
					items.get(selected).drawText(g, 0, 0);
				}
				break;
				
			default:
				break;
				
		}
				
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * LENGTH_CHARS, (AbstractTile.STEP + 2) * 6);
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
				
				if(details.contains(mouse)) {
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
