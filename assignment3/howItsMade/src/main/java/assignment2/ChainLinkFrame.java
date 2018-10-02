import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public abstract class ChainLinkFrame extends JFrame {
		
	private ChainLink chainlink;
	protected JPanel panel;
	// ChainLinkProcess and corresponding status label
	protected Map<HasStatus, JLabel> labels = new HashMap<HasStatus, JLabel>();

	public ChainLinkFrame(ChainLink chainlink) { //constructor
		this.chainlink = chainlink;
	    SwingUtilities.invokeLater(new Runnable() 		// create GUI on EDT
	    {
	      public void run()
	      {
	  			createGui(); 		
	      }
	    });
	}
			
	
	public void createGui() {
		setSize(LayoutCount.windowWidth, LayoutCount.windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// reveal properly besides and below each other
		setLocation(LayoutCount.getHorizontalPixels(), LayoutCount.getVerticalPixels());	
		LayoutCount.addPixels();
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(20, 1)); 
		add(panel);
		
		// label with name and port
		JLabel chainLinkName = new JLabel(chainlink.getClass().toString() + chainlink.getAddress().getPort());
		panel.add(chainLinkName);
		
		// labels of subprocesses
		addLabels();
		
		JButton endButtom = new JButton("Click to terminate");  
		endButtom.addActionListener(new EndingListener());
		add(endButtom, BorderLayout.SOUTH);
	}
	
	protected abstract void addLabels();

	// update status in panel (see class StatusUpdater)
	public void setStatus(HasStatus proces, String status) {
		labels.get(proces).setText(status.toString());
		
		// redraw the panel
		panel.revalidate();
	}
}