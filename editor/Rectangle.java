import java.awt.Color;
import java.awt.Graphics;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 */
public class Rectangle implements Shape {
	private int x1, y1, x2, y2;		// upper left and lower right
	private Color color; // stores color of rectangle

	/**
	 * An "empty" rectangle, with only one point set so far
	 */
	public Rectangle(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}

	/**
	 * An rectangle defined by two corners
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2);
		this.color = color;
	}

	/**
	 * Redefines the rectangle based on new corners
	 */
	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * Moves rectangle by dx and dy
	 *
	 * @param dx no of pixels to move x coordinates by
	 * @param dy no of pixels to move y coordinates by
	 */
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx; y1 += dy;
		x2 += dx; y2 += dy;
	}

	/**
	 * returns color of rectangle
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * sets color of rectangle
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns true if rectangle contains point, false otherwise
	 *
	 * @param x x-coordinate of point
	 * @param y y-coordinate of point
	 */
	@Override
	public boolean contains(int x, int y) {
		return (x1<=x && x<=x2 && y1<=y && y<=y2);
	}

	/**
	 * draws rectangle
	 * @param g Graphics object
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1, y1, x2-x1, y2-y1);
	}

	/**
	 * Represents rectangle as string
	 * @return string representation of rectangle
	 */
	public String toString() {
		return "rectangle "+x1+" "+y1+" "+x2+" "+y2+" "+color.getRGB();
	}
}
