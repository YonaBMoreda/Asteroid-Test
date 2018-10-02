import javax.swing.SwingUtilities;

public class Miner extends ChainLinkProcess implements Runnable {

	private MinerStatus status;

	public Miner(Mine mine) { 
		super(mine);
		this.status = MinerStatus.initialised;  // miner is created
	}

	public void run() {
		while (true) {
			setStatus("mining");

			try {
				Thread.sleep(1000);			// mining takes 1 second
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			((Mine)link).processOre(Product.Ore);     // give ore to Mine

			setStatus("finished");
		}
	}

	@Override
	public void setStatus(String status) {
		this.status = MinerStatus.valueOf(status);
		// invoke GUI updater on EDT 
		SwingUtilities.invokeLater(new StatusUpdater(this, this.getChainlink()));
	}

	@Override
	public String getStatus() {
		return this.status.toString();
	}
}