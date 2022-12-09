import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Handles communication to/from the server for the editor
 *
 */
public class EditorCommunicator extends Thread {
    private PrintWriter out;        // to server
    private BufferedReader in;      // from server
    protected Editor editor;        // handling communication for

    /**
     * Establishes connection and in/out pair
     */
    public EditorCommunicator(String serverIP, Editor editor) {
        this.editor = editor;
        System.out.println("connecting to " + serverIP + "...");
        try {
            Socket sock = new Socket(serverIP, 4242);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            System.out.println("...connected");
        } catch (IOException e) {
            System.err.println("couldn't connect");
            System.exit(-1);
        }
    }

    /**
     * Sends message to the server
     * @param msg message to be sent to server
     */
    public void send(String msg) {
        out.println(msg);
    }

    /**
     * Keeps listening for and handling (your code) messages from the server
     */
    public void run() {
        try {
            // Handle messages
            String line;
            while ((line = in.readLine()) != null) {
                Message msg = new Message(); //create Message class object
                msg.decode(line, editor.getSketch()); //decode message and update sketch object in editor
                editor.repaint(); //call repaint in editor
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("server hung up");
        }
    }

    // Send editor requests to the server

    /**
     * Sends requests to create shape to the server
     * @param requestMsg message to be sent along with "draw" to server
     */
    public void requestCreateShape(String requestMsg) {
        this.out.println("draw " + requestMsg);
    }

    /**
     * Sends requests to move shape to the server
     * @param requestMsg message to be sent along with "draw" to server
     */
    public void requestMove(String requestMsg) {
        this.out.println("move " +requestMsg);
    }

    /**
     * Sends requests to recolor shape to the server
     * @param requestMsg message to be sent along with "draw" to server
     */
    public void requestRecolor(String requestMsg) {
        this.out.println("recolor " +requestMsg);
    }

    /**
     * Sends requests to delete shape to the server
     * @param requestMsg message to be sent along with "draw" to server
     */
    public void requestDelete(String requestMsg) {
        this.out.println("delete " + requestMsg);
    }

}
