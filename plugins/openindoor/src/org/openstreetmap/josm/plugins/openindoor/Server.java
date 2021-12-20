package org.openstreetmap.josm.plugins.openindoor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.openstreetmap.josm.plugins.openindoor.HttpHandler;

/**
 * @author ambarmodi
 *
 */
// public class Server {
public class Server extends Thread {
	// private static int PORT = 8080;
	private ServerSocket serverSocket;
	private static HashMap<String,Integer> requestedRes = new HashMap<String,Integer>();
	private static final String DELIMITER = "|";
	
	public Server(int port) throws IOException {
		// PORT = port;
		serverSocket = new ServerSocket(port);
	}

	/**
	 * @throws IOException
	 */
	private void start_() throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			HttpHandler connection = new HttpHandler(socket);

			Thread request = new Thread(connection);
			request.start();
		}
	}
	
	public void run() {
		try {
			this.start_();
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			System.exit(0);
		}
	}

	/**
	 * 
	 * @param res
	 * @param port2
	 * @param ipAddress
	 */
	public static void printResult(String res, int port2, String ipAddress) {
		ipAddress = ipAddress.split(":")[0].replace("/", "");
		if(requestedRes.get(res) == null) {
			requestedRes.put(res, 1);
		} else {
			requestedRes.put(res, requestedRes.get(res) + 1);
		}
		System.out.println(res + DELIMITER+ ipAddress + DELIMITER + port2 +DELIMITER + requestedRes.get(res));
	}
}
