import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
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
	
	private static final int LENGTH = 10;
	private static final int HEIGHT = 16;
	
	private int previousHP;

	public StatusPanel(AbstractEntity owner) {
		this.owner = owner;
		if(owner != null)this.previousHP = owner.hp;
		
		//this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.setBackground(Color.BLACK);
	}
	
	public void setOwner(AbstractEntity e) {
		owner = e;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		if(owner == null) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("empty", 0, AbstractTile.STEP);
		} else {
		g2d.setFont(MapPanel.font);
		
		g2d.setColor(Color.GREEN);
		
		g2d.fillRect(0, 0, (owner.hp * LENGTH * AbstractTile.STEP)/(owner.getHealthCap()), AbstractTile.STEP);
		if(owner.hp < previousHP) {
			g2d.setColor(Color.RED);
			g2d.fillRect((owner.hp * LENGTH * AbstractTile.STEP)/(owner.getHealthCap()), 0, ((previousHP - owner.hp) * LENGTH * AbstractTile.STEP)/(owner.getHealthCap()), AbstractTile.STEP);
		}
		
		g2d.setColor(Color.WHITE);
		
		g2d.drawString("HP : " + (owner.hp > 0 ? owner.hp : 0) + "/" + owner.getHealthCap(), 0, AbstractTile.STEP * 2);
		g2d.drawString("STR: " + owner.str, 0, AbstractTile.STEP * 3);
		g2d.drawString("DEF: " + owner.def, 0, AbstractTile.STEP * 4);
		g2d.drawString("MAG: " + owner.mag, 0, AbstractTile.STEP * 5);
		g2d.drawString("RES: " + owner.res, 0, AbstractTile.STEP * 6);
		g2d.drawString("SKL: " + owner.skl, 0, AbstractTile.STEP * 7);
		g2d.drawString("SPD: " + owner.spd, 0, AbstractTile.STEP * 8);
		
		if(owner.getInventory().hasEquipt()) {
			g2d.drawString(((owner.getInventory().get(0).isMagical() ? owner.mag : owner.str) + owner.skl + owner.getInventory().get(0).getHit()) + "% hit", 0, AbstractTile.STEP * 10);
			g2d.drawString(((owner.getInventory().get(0).isMagical() ? owner.mag : owner.str) + owner.getInventory().get(0).getCrit()) + "% crit", 0, AbstractTile.STEP * 11);
			g2d.drawString((owner.getInventory().get(0).getDamage() + (owner.getInventory().get(0).isMagical() ? owner.mag/2 : owner.str/2)) + " crit damage", 0, AbstractTile.STEP * 14);
		} else {
			g2d.drawString((owner.skl + owner.str) + "% hit", 0, AbstractTile.STEP * 10);
			g2d.drawString(owner.str + "% crit", 0, AbstractTile.STEP * 11);
			g2d.drawString((owner.dam_str + owner.str/2) + " crit damage", 0, AbstractTile.STEP * 14);
		}
		
		g2d.drawString((owner.spd + owner.def) + "% avoid str", 0, AbstractTile.STEP * 12);
		g2d.drawString((owner.spd + owner.mag) + "% avoid mag", 0, AbstractTile.STEP * 13);
		
		previousHP = owner.hp;
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(AbstractTile.STEP * 10, AbstractTile.STEP * 16);
	}

}
