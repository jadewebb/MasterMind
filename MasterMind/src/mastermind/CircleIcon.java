package mastermind;

import java.awt.*;
import java.awt.Component;
import java.awt.geom.*;
import javax.swing.*;

public class CircleIcon implements Icon {

	private int diameter;
	public Color color;
	
	/**
	 * Initializes a CircleIcon with a given diameter
	 * @param diameter - diameter of the circle
	 */
	public CircleIcon(int diameter) {
		this.diameter = diameter;
	}
	
	/**
	 * Gets the width of the icon
	 * @return the diameter of the icon
	 */
	public int getIconWidth() {
		return diameter;
	}
	
	/**
	 * Gets the height of the icon
	 * @return the diameter of the icon
	 */
	public int getIconHeight() {
		return diameter;
	}
	
	/**
	 * Sets the color of the icon
	 * @param color - the color of the icon
	 */
	public void setIconColor(Color color) {
		this.color = color;
	}

	/**
	 * Paints the icon to contain a circle filled with a certain color
	 * @param c - the component to contain the icon
	 * @param g - the graphics used to paint the icon
	 * @param x - the x coordinate of the icon
	 * @param y - the y coordinate of the icon
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Float circle = new Ellipse2D.Float(x, y, diameter, diameter);
		g2.setColor(color);
		g2.fill(circle);
		g2.draw(circle);
	}
	
	
	
}
