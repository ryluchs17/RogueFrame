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

import entity.AbstractEntity;
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
	
	// number selected item index
	private short selected = 0;
	
	// the inventory being managed by this panel
	private Inventory items;
	
	// the AbstractEntity that this inventory belongs to
	private AbstractEntity owner; 
	
	// whether the user is allowed to modify the contents of this panel
	public boolean editable = true;
	
	// a map panel for getting throwing targets
	public MapPanel map;
	
	// display modes
	public final static short MODE_SELECT = 0;
	public final static short MODE_INFO = 1;
	
	// current mode
	private short mode = MODE_SELECT;
	
	// length of panel in character
	private static final int LENGTH_CHARS = 20;
	
	// the boxes representing the buttons a user can press
	private static Rectangle equiptbutton = new Rectangle(0, 0, AbstractTile.STEP * 2, AbstractTile.STEP);
	private static Rectangle details = new Rectangle(0, AbstractTile.STEP * 7, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
	private static Rectangle use = new Rectangle(AbstractTile.STEP * 8, AbstractTile.STEP * 7, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
	private static Rectangle throwcast = new Rectangle(AbstractTile.STEP * 13, AbstractTile.STEP * 7, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
//	private static Rectangle swap = new Rectangle((LENGTH_CHARS/4) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
//	private static Rectangle use = new Rectangle((LENGTH_CHARS/2) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);
//	private static Rectangle throwr = new Rectangle(((3 * LENGTH_CHARS)/4) * AbstractTile.STEP, AbstractTile.STEP * 6, AbstractTile.STEP * (LENGTH_CHARS/4), AbstractTile.STEP);

	public static final String EMPTY_ITEM_SLOT = "-----";
	
	/**
	 * 
	 */
	public InventoryPanel(AbstractEntity owner, MapPanel map) {
		super();
		
		this.owner = owner;
		items = owner.getInventory();
		
		this.map = map;
		
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

					if(i == null) {
						// set color to light blue if y is selected; white if not
						if(y == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.WHITE); };
						g2d.drawString(EMPTY_ITEM_SLOT, (this.getWidth()/2) - AbstractTile.STEP * 2, (y + 1) * AbstractTile.STEP);
					} else {
						i.drawUnshifted(g, (this.getWidth()/2) - AbstractTile.STEP * 4, y * AbstractTile.STEP);
						// set color to light blue if y is selected; white if not
						if(y == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.WHITE); };
						g2d.drawString(i.getName() + (i.isStackable() ? (" (" + i.uses + ") ") : " "), (this.getWidth()/2) - AbstractTile.STEP * 2, (y + 1) * AbstractTile.STEP);
					}
				}
				
				if(owner.getMap().tileAt(owner.getX(), owner.getY()).hasItem()) {
					i = owner.getMap().tileAt(owner.getX(), owner.getY()).getItem();
					
					i.drawUnshifted(g, (this.getWidth()/2) - AbstractTile.STEP * 4, 5 * AbstractTile.STEP);
					
					// set color to light blue if y is selected; grey if not
					if(5 == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.GRAY); };
					
					g2d.drawString(i.getName() + (i.isStackable() ? (" (" + i.uses + ") ") : " "), (this.getWidth()/2) - AbstractTile.STEP * 2, 6 * AbstractTile.STEP);
				} else {
					
					// set color to light blue if y is selected; grey if not
					if(5 == selected){ g2d.setColor(AbstractTile.TOOLTIP_ITEM); }else{ g2d.setColor(Color.GRAY); };
					
					g2d.drawString(EMPTY_ITEM_SLOT, (this.getWidth()/2) - AbstractTile.STEP * 2, 6 * AbstractTile.STEP);
				}
				
				if(items.hasEquipt()) {
					g2d.setColor(editable ? items.get(0).isCursed() ? Color.MAGENTA : Color.YELLOW : Color.GRAY);
					g2d.drawString("Eq", equiptbutton.x, equiptbutton.y + 10);
				} else {
					g2d.setColor(Color.GRAY);
					g2d.drawString("---", equiptbutton.x, equiptbutton.y + 10);
				}
				
				g2d.setColor(Color.YELLOW);
				
				g2d.drawString("Details", details.x, details.y + AbstractTile.STEP);
				
				g2d.drawString("Use", use.x, use.y + AbstractTile.STEP);
				
				g2d.drawString("Throw/Cast", throwcast.x, throwcast.y + AbstractTile.STEP);
				
//				g2d.setColor(editable ? Color.YELLOW : Color.GRAY);
				
//				g2d.drawString("Swap", swap.x, swap.y + AbstractTile.STEP);
//				g2d.drawString("Use", use.x, use.y + AbstractTile.STEP);
//				g2d.drawString("Throw", throwr.x, throwr.y + AbstractTile.STEP);
//				
				break;
		
			case MODE_INFO:
				if(selected == 5 && owner.getMap().tileAt(owner.getX(), owner.getY()).hasItem()) {
					owner.getMap().tileAt(owner.getX(), owner.getY()).getItem().drawText(g, 0, 0);
				} else if(items.get(selected) == null) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("There's nothing here", 0, AbstractTile.STEP);
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
		return new Dimension(AbstractTile.STEP * LENGTH_CHARS, (AbstractTile.STEP + 2) * 7);
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
				
				if(use.contains(mouse)) {
					if(items.get(selected) != null) {
						items.get(selected).onUse(owner);
						
						items.clean();
						repaint();
						map.rounds(1);
					}
				}
				
				if(throwcast.contains(mouse)) {
					if(selected == 5) {
						owner.throwItem(map.getSelect());
					} else {
						owner.throwItem(selected, map.getSelect());
					}
					
					repaint();
					map.rounds(1);
				}
				
				for(int y = 0; y < 6; y++) {
					if(mouse.y > y * AbstractTile.STEP && mouse.y < (y + 1) * AbstractTile.STEP && selected != y) {
						selected = (short) y;
//						System.out.println(selected);
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
		if(mode == MODE_SELECT) {
			int mouseY = e.getY();
			
			for(int y = 0; y < 6; y++) {
				if(mouseY > y * AbstractTile.STEP && mouseY < (y + 1) * AbstractTile.STEP && selected != y) {
					if(selected == 5) {
						items.swap(y, owner.getMap().tileAt(owner.getX(), owner.getY()));
					} else if(y == 5) {
						items.swap(selected, owner.getMap().tileAt(owner.getX(), owner.getY()));
					} else {
						items.swap(selected, y);
					}
					selected = (short) y;
					repaint();
				}
				
			}
			
//			if(mouseY > 5 * AbstractTile.STEP && mouseY < 5 * AbstractTile.STEP && selected != 5) {
//				items.swap(selected, owner.getMap().tileAt(owner.getX(), owner.getY()));
//				System.out.println("swapitybopity");
//				repaint();
//			}
		}
		
	}

}
