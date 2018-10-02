import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Mine extends ChainLink {

	// the frame that displays the status of the mine and the miners
	MineFrame frame; 
	//collection of miners belonging to the mine
	private ArrayList<Miner> miners = new ArrayList<>();

	public Mine(InetSocketAddress address, InetSocketAddress[] nextLinks, int numberOfMiners) {
		super(address, nextLinks, new InetSocketAddress[0]);

		// at least one miner
		if (numberOfMiners < 1) {
			numberOfMiners = 1;
		}

		// create miner Objects
		for(int i = 0; i < numberOfMiners; i++) {
			//send instance of this mine to the miner, so that it can send back the ore
			Miner miner = new Miner(this);
			miners.add(i, miner);		// add to the list of miners belonging to the mine
		}

		// first create the window because as soon as the miner is done it'll try and update the label  in the GUI
		// send Mine object to MineFrame, such that it can include the miners etc.
		frame = new MineFrame(this);
		frame.setVisible(true);

		// only after the window has been created we will start the miners
		for(Miner miner : miners) {	// miner is identified by its corresponding mine and a number
			new Thread(miner).start();  // for every miner create a separate thread
		}
	}

	public MineFrame getFrame() {
		return frame;
	}

	public void processOre(Product product) {
		// Mine received ore from miner 
		if (product != Product.Ore) {
			throw new UnsupportedOperationException("The mine can't accept " + product.toString());
		}

		try {
			send(product);
		} catch (Exception e) {
			System.out.println("Unable to send ore to factory");
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Miner> getMiners() { 
		return miners;
	}

	@Override
	public void handleMessage(String s) {   // mine cannot accept products
		throw new UnsupportedOperationException();
	}

	@Override
	public String getStatus() {
		return "Open";
	}
}