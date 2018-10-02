import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public abstract class ChainLink {
	private InetSocketAddress address;
	private InetSocketAddress[] nextLinks;
	private InetSocketAddress[] previousLinks;
	
	
	public ChainLink(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks) {
		this.address = address;
		this.nextLinks = nextLinks;
		this.previousLinks = previousLinks;
		
		// only listen for input when there are previous links
		if (this.previousLinks.length > 0) {
			try {
				// start the InputServer so that the ChainLink is ready to process the input of products/materials
				Thread inputServer = new Thread(new InputServer(this, this.address.getPort())); 
				inputServer.start();
				System.out.println("Listening at " + this.address.getPort());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	// used to update the GUI
	public abstract ChainLinkFrame getFrame();

	public InetSocketAddress getAddress() {
		return address;
	}
	
	// send product to a randomly selected next link
	public void send(Product product) throws IOException {
		if (this.nextLinks.length == 0) {				// no links
			throw new UnsupportedOperationException();
		}

		// get  random next link
		InetSocketAddress nextLink = chooseRandomNextLink(this.nextLinks);
		
        Socket socket = new Socket(nextLink.getHostName(), nextLink.getPort());

        //Send the message to the server
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(product.toString());			// 'send' product
        bw.flush();
        
        bw.close();
        osw.close();
        os.close();
        socket.close();
	}
	
	// chooses random ChainLink address from an array of addresses
	public InetSocketAddress chooseRandomNextLink (InetSocketAddress[] links) {
		Random generator = new Random();
		int nextLink = generator.nextInt(links.length);
		return links[nextLink];
	}
	
	// this is implemented in subclasses
	public abstract void handleMessage(String s);
	
	public abstract String getStatus();
	
	
}