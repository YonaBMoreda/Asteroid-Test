// called from EDT to update status in ChainLinkFrames
public class StatusUpdater implements Runnable {
	HasStatus target;  // a Class that can have a status which can be displayed in a ChainLinkFrame
	ChainLink chainlink;
	
	public StatusUpdater(HasStatus target, ChainLink chainlink) {
		this.target = target;
		this.chainlink = chainlink;
	}

	public StatusUpdater(ChainLink chainlink) {
		this.chainlink = chainlink;
		this.target = (HasStatus)chainlink;
	}

	public void run()
    {
		chainlink.getFrame().setStatus(target, target.getStatus());  // updates status on panel
    }
}