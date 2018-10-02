import javax.swing.SwingUtilities;

public abstract class ProductionLine extends ChainLinkProcess implements Runnable {

	private Factory factory;
	protected ProductionLineStatus status;
	private Backlog backlog;

	public ProductionLine(Factory factory, Backlog backlog) {  // production line knows the factory and its backlog
		super(factory);
		this.backlog = backlog;
		this.factory = factory;
		this.status = ProductionLineStatus.initialised;
	}

	public void run() {
		while(true) {
			// don't do anything when there's no product on the production line
			if (backlog.get() > 0) {
				setStatus(ProductionLineStatus.producing.toString());

				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				// do the actual conversion
				Product product = convert();

				// from production line to factory
				this.factory.acceptProduct(product);

				backlog.decrement();

				setStatus(ProductionLineStatus.nonactive.toString());
			}
		}
	}

	
	public String getStatus() {
		return this.status.toString();
	}

	@Override
	public void setStatus(String status) {
		this.status = ProductionLineStatus.valueOf(status);
		// invoke GUI updater on EDT 
		SwingUtilities.invokeLater(new StatusUpdater(this, this.getChainlink()));
	}	

	public Factory getFactory() {
		return this.factory;
	}
	
	// this is implemented in subclasses
	public abstract Product convert() ;
}