import java.io.*;
import java.net.Socket;

/**
 * Handles communication between the server and one client, for SketchServer
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}
	
	/**
	 * Keeps listening for and handling messages from the client
	 */
	public void run() {
		try {
			System.out.println("someone connected");
			
			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Tell the client the current state of the world
			// Loops through shapes and sends message to new editor to draw all of them
			for(int id: server.getSketch().shapes.navigableKeySet()){
				send("draw " + server.getSketch().shapes.get(id).toString());
			}

			// Keep getting and handling messages from the client
			String line;
			while ((line = in.readLine()) != null) {
				Message msg = new Message(); // create new message class object
				msg.decode(line, server.getSketch()); //decode line and update sketch object in server
				server.broadcast(line); // send message to all editors
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
