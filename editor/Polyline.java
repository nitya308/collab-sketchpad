import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 */
public class Polyline implements Shape {
    ArrayList<Point> poly; // stores ArrayList of points in Polyline
    private Color color; // stores current color

    /**
     * Creates a new Polyline with a starting point and a color
     * @param p starting point of polyline
     * @param color color of polyline
     */
    public Polyline(Point p, Color color) {
        poly = new ArrayList<>();
        poly.add(p);
        this.color = color;
    }

    /**
     * Adds a new point to polyline
     * @param p point to be added
     */
    public void addPoint(Point p) {
        poly.add(p);
    }

    /**
     * moves each point in polyline
     * @param dx no of pixels to move x coordinates by
     * @param dy no of pixels to move y coordinates by
     */
    @Override
    public void moveBy(int dx, int dy) {
        for (Point p : poly) {
            p.x += dx;
            p.y+=dy;
        }
    }

    /**
     * returns color of polyline
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * sets color of polyline
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns true if point is near polyline, otherwise returns false
     *
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     */
    @Override
    public boolean contains(int x, int y) {
        for (int i = 0; i < poly.size() - 1; i++) {
            if( Segment.pointToSegmentDistance(x, y, poly.get(i).x, poly.get(i).y, poly.get(i + 1).x, poly.get(i + 1).y)<=3){
                return true;
            }
        }
        return false;
    }

    /**
     * draws polyline
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < poly.size() - 1; i++) {
            Segment seg = new Segment(poly.get(i).x, poly.get(i).y, poly.get(i + 1).x, poly.get(i + 1).y, color);
            seg.draw(g);
        }

    }

    /**
     * converts shape to a string
     * @return string representation of polyline
     */
    @Override
    public String toString() {
        String str = "";
        str += "polyline " + color.getRGB() + " ";
        for (Point p : poly) {
            str += p.x + " " + p.y + " ";
        }
        return str;
    }
}
