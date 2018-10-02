import java.util.ArrayList;

import javax.swing.JLabel;

public class FactoryFrame extends ChainLinkFrame {
	
	public FactoryFrame(Factory factory) { //constructor
		super(factory);
	}

	@Override
	protected void addLabels() {
	}

	@Override
	public void setStatus(HasStatus proces, String status) {		// status info in GUI
		if (!labels.containsKey(proces)) {
			JLabel name = new JLabel("ProductionLine " + labels.size() + ":");
			super.panel.add(name);
			JLabel statusLabel = new JLabel(proces.getStatus().toString());
			super.panel.add(statusLabel);
			labels.put(proces, statusLabel);
		}

		super.setStatus(proces, status);
	}
}