import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.AbstractEntity;
import item.AbstractItem;
import tile.AbstractTile;

/**
 * 
 */

/**
 * @author ryan
 *
 */
public class StatusPanel extends JPanel {
	
	AbstractEntity owner;

	public StatusPanel(AbstractEntity owner) {
		this.owner = owner;
		
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setFont(MapPanel.font);
		
		g2d.setColor(Color.WHITE);
		
		g2d.drawString("HP: " + owner.hp, 0, AbstractTile.STEP);
		g2d.drawString("STR: " + owner.str, 0, AbstractTile.STEP * 2);
		g2d.drawString("DEF: " + owner.def, 0, AbstractTile.STEP * 3);
		g2d.drawString("MAG: " + owner.mag, 0, AbstractTile.STEP * 4);
		g2d.drawString("RES: " + owner.res, 0, AbstractTile.STEP * 5);
		g2d.drawString("SKL: " + owner.skl, 0, AbstractTile.STEP * 6);
		g2d.drawString("SPD: " + owner.spd, 0, AbstractTile.STEP * 7);
		
		if(owner.getInventory().hasEquipt()) {
			g2d.drawString(((owner.getInventory().get(0).isMagical() ? owner.mag : owner.str) + owner.skl + owner.getInventory().get(0).getHit()) + "% hit", 0, AbstractTile.STEP * 9);
			g2d.drawString(((owner.getInventory().get(0).isMagical() ? owner.mag : owner.str) + owner.getInventory().get(0).getCrit()) + "% crit", 0, AbstractTile.STEP * 10);
			g2d.drawString((owner.getInventory().get(0).getDamage() + (owner.getInventory().get(0).isMagical() ? owner.mag/2 : owner.str/2)) + " crit damage", 0, AbstractTile.STEP * 13);
		} else {
			g2d.drawString((owner.skl + owner.str) + "% hit", 0, AbstractTile.STEP * 9);
			g2d.drawString(owner.str + "% crit", 0, AbstractTile.STEP * 10);
			g2d.drawString((owner.dam_str + owner.str/2) + " crit damage", 0, AbstractTile.STEP * 13);
		}
		
		g2d.drawString((owner.spd + owner.def) + "% avoid str", 0, AbstractTile.STEP * 11);
		g2d.drawString((owner.spd + owner.mag) + "% avoid mag", 0, AbstractTile.STEP * 12);
			
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * 10, AbstractTile.STEP * 16);
	}

}
