import java.util.ArrayList;
import javax.swing.JLabel;

public class MineFrame extends ChainLinkFrame {

	private ArrayList<Miner> miners;	

	public MineFrame(Mine mine) { //constructor
		super(mine);
		this.miners = mine.getMiners();
	}

	@Override
	protected void addLabels() {
		// add the miners + status labels
		for(int i = 0; i < miners.size(); i++) {
			Miner m = miners.get(i);
			JLabel minerName = new JLabel("Miner " + i + ":");
			super.panel.add(minerName);
			JLabel statusLabel = new JLabel(m.getStatus().toString());
			super.panel.add(statusLabel);
			labels.put(m, statusLabel);	
		}
	}
}
