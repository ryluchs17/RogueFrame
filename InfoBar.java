/**
 * A class for life bars, mana bars, etc. for video games
 * @author Ryan Luchs
 *
 */

import javax.swing.*;
import java.awt.*;

public class InfoBar extends JPanel {

	private static final long serialVersionUID = 1L;

	// length of the bar in characters
	private int length;
	
	// maximum value of the bar, and current value
	private int maximum; private int value;
	
	// when the current value decreases, show stores by how much
	private int decrease;
	
	// color to fill bar with
	private Color fillColor = Color.GREEN;
	
	// color to fill decrease with
	private Color decreaseColor = Color.RED;
	
	// Title of the bar and color of the title
	private String title;
	private Color titleColor = Color.WHITE;
	
	// Boolean for toggling whether to show decrease
	private boolean showDecrease = false;
	
//	// Boolean for toggling showing of values
//	private boolean showValues = false;
	
	public static final int STEP = 12;
	
	public InfoBar(String title, int maximum, int value, int length) {
		this.title = title;
		this.maximum = maximum;
		this.value = value;
		this.length = length;
	}
	
	/**
	 * Sets the current numeric value of the bar
	 * @param v the value
	 */
	public void setValue(int v) {
		if(value + v < 0) {
			decrease = value;
			value = 0;
		} else if(v > maximum) {
			value = maximum;
		} else {
			decrease = v < value ? value - v : 0;
			value = v;
		}
	}
	
	/**
	 * Gets the current value of the bar
	 * @return the numeric value of the bar
	 */
	public int getValue() {
		return value; 
	}
	
	/**
	 * Sets the maximum value of the bar
	 * @param m the maximum value
	 */
	public void setMax(int m) {
		maximum = m;
	}
	
	/**
	 * Gets the maximum value of the bar
	 * @return the maximum numeric value of the bar
	 */
	public int getMax() {
		return maximum;
	}
	
	/**
	 * Shifts the value of the bar by a given amount
	 * @param v the value to shift by
	 */
	public void shiftValue(int v) {
		if(value + v < 0) {
			decrease = value;
			value = 0;
		} else if(value + v > maximum) {
			value = maximum;
		} else {
			decrease = v < 0 ? v*-1 : 0;
			value += v;
		}
	}
	
	/**
	 * Sets the bar's title
	 * @param t the title
	 */
	public void setTitle(String t) {
		title = t;
	}
	
	/**
	 * Sets the color of the bar's title
	 * @param c the color
	 */
	public void setTitleColor(Color c) {
		titleColor = c;
	}
	
	/**
	 * Sets the color of the bar
	 * @param c the color
	 */
	public void setColor(Color c) {
		fillColor = c;
	}
	
	/**
	 * Sets the color that decreases of the bar are shown in
	 * @param c the color
	 */
	public void setDecreaseColor(Color c) {
		decreaseColor = c;
	}
	
	/**
	 * Toggles whether to show the decrease of the bar
	 * @param d Whether to show the decrease or not
	 */
	public void showDecrease(boolean b) {
		showDecrease = b;
	}
	
//	public void showValues(boolean b) {
//		showValues = b;
//	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw title
		g.setColor(titleColor);
		//g.drawString(showValues ? title + " " + value + "/" + maximum : title, 0, STEP);
		g.drawString(title, 0, STEP);
		
		// draw bar
		g.setColor(fillColor);
		g.fillRect(0, STEP + 2, (length*value)/maximum, STEP);
		
		if(showDecrease && decrease > 0) {
			g.setColor(decreaseColor);
			g.fillRect(((length*value)/maximum), STEP + 2, ((length*decrease)/maximum), STEP);
		}
		
//		if(showValues) {
//			g.setColor(titleColor);
//			g.drawString(value + "/" + maximum, 0, STEP*2);
//		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(length, STEP*2);
	}
}
