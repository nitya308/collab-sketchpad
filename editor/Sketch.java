import java.awt.*;
import java.util.TreeMap;

/**
 * Sketch class to hold TreeMap of shapes in editors and server
 */

public class Sketch {
    public TreeMap<Integer, Shape> shapes = new TreeMap<Integer, Shape>(); //Store shapes by id
    public static int id = 0;

    /**
     * To add shapes to sketch
     *
     * @param shape shape to be added
     */
    public synchronized void addShape(Shape shape) {
        shapes.put(id, shape);
        id++;
    }

    /**
     * To remove shape from sketch
     *
     * @param id id of shape to be removed
     */
    public synchronized void removeShape(int id) {
        if(shapes.containsKey(id)) shapes.remove(id);
    }

    /**
     * To recolor shape in sketch
     *
     * @param id    id of shape to be recolored
     * @param color new color of shape to recolor to
     */
    public synchronized void recolorShape(int id, Color color) {
        if(shapes.containsKey(id)) shapes.get(id).setColor(color);
    }

    /**
     * To move shapes in sketch
     *
     * @param id id of shape to be removed
     * @param dx no of pixels shape should be moved by horizontally
     * @param dy no of pixels shape should be moved by vertically
     */
    public synchronized void moveShape(int id, int dx, int dy) {
        if(shapes.containsKey(id)) shapes.get(id).moveBy(dx, dy);
    }
}
