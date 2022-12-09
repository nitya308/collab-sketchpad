
import java.awt.*;
/**
 * Message class to parse messages between client and server
 *
 * @author Nitya Agarwala
 */

public class Message {

    private String[] tokens; // array to hold each word in the message

    public Message() { //constructor to create a new object of the message class
    }

    /**
     * Decodes message
     * @param msg Message to decode
     * @param sketch Sketch object to add shape to/modify shape from
     */
    public void decode(String msg, Sketch sketch) {
        //Split message into tokens array
        tokens = msg.split(" ");
        // For creating shapes
        if (tokens[0].equals("draw")) {
            Shape shape = null;
            // Second word in message must be shape type
            String shapeType = tokens[1];
            if ("ellipse".equals(shapeType)) {
                Color color = new Color(Integer.parseInt(tokens[6])); //parses color from last item in token
                sketch.addShape(new Ellipse(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), color));
            } else if ("rectangle".equals(shapeType)) {
                Color color = new Color(Integer.parseInt(tokens[6])); //parses color from last item in token
                sketch.addShape(new Rectangle(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), color));
            } else if ("segment".equals(shapeType)) {
                Color color = new Color(Integer.parseInt(tokens[6])); //parses color from last item in token
                sketch.addShape(new Segment(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), color));
            } else if ("polyline".equals(shapeType)) {
                Color color = new Color(Integer.parseInt(tokens[2])); //parses color from third item in token
                //Create a new polyline poly with first two points
                Shape poly = new Polyline(new Point(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])), color);
                // loop over tokens which have point in x y format and add all to poly
                for (int i = 5; i < tokens.length - 1; i += 2) {
                    ((Polyline) poly).addPoint(new Point(Integer.parseInt(tokens[i]), Integer.parseInt(tokens[i + 1])));
                }
                //add poly to sketch
                sketch.addShape(poly);
            }
            //If wrong message is passed
            else{
                System.out.println("Not a shape type!");
            }
        }
        // For recoloring shape
        else if (tokens[0].equals("recolor")) {
            // Parses id and color from message
            int id= Integer.parseInt(tokens[1]);
            sketch.recolorShape(id, new Color(Integer.parseInt(tokens[2])));
        }

        // Moving shape
        else if (tokens[0].equals("move")) {
            // Parses id, dx and dy from message
            int id= Integer.parseInt(tokens[1]);
            sketch.moveShape(id, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        }

        // Deleting shape
        else if (tokens[0].equals("delete")) {
            // Parses id to delete
            int id= Integer.parseInt(tokens[1]);
            sketch.removeShape(id);
        }
    }
}
