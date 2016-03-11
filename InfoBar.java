/**
 * A class for life bars, mana bars, etc. for video games
 * @author Ryan Luchs
 *
 */

import javax.swing.*;
import java.awt.*;

public class InfoBar extends JPanel {

	// length of the bar in characters
	private int length;
	
	// maximum value of the bar, and current value
	private int maximum; private int value;
	
//	// when the current value decreases, show stores by how much
//	private int decrease;
	
	// color to fill bar with
	private Color fillColor = Color.RED;
	
//	// color to fill decrease with
//	private Color decreaseColor;
	
	// Title of the bar and color of the title
	private String title;
	private Color titleColor = Color.WHITE;
	
	public static final int STEP = 10;
	
	public InfoBar(String title, int maximum, int value, int length) {
		this.title = title;
		this.maximum = maximum;
		this.value = value;
		this.length = length;
		
		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g) {
		// draw title
		g.setColor(titleColor);
		g.drawString(title, 0, 0);
		
		// draw bar
		g.setColor(Color.WHITE);
		g.drawString("[", 0, STEP);
		
		g.setColor(fillColor);
		for(int i = STEP; i < (length*STEP) - 1; i += STEP) {
			g.drawString("#", i, STEP);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("]", (length*STEP) - 1, STEP);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension((STEP*length) + 2, STEP*2);
	}
}
