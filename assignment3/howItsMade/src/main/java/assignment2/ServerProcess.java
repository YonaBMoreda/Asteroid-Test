/*An instance of ServerProcess is created when the server receives new request*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerProcess  implements Runnable {  
	private Socket inputSocket;
	private ChainLink link;

	public ServerProcess(ChainLink link, Socket socket) {
		inputSocket = socket;
		this.link = link;	
	}

	public void run() {
		try {
			BufferedReader inPutReader = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));

			this.link.handleMessage(inPutReader.readLine());    // handleMessage implemented in ChainLink subclasses 

			inputSocket.close();
		} catch (Exception e) {
			System.out.println("Error while handling incoming data:");
			System.out.println(e);
		}
	}
}

