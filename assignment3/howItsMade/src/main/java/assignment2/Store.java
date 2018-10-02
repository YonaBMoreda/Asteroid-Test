import java.net.InetSocketAddress;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.SwingUtilities;

public class Store extends ChainLink implements HasStatus {
	Map<Product, Double> prices = new HashMap<Product, Double>();
	private Double bank = 0d;	// total revenue
	private StoreFrame frame;
	private ShopStatus status = ShopStatus.initialised;

	public Store(InetSocketAddress address, InetSocketAddress[] previousLinks) {
		super(address, new InetSocketAddress[0], previousLinks);

		prices.put(Product.SteelPlate, 75.00);
		prices.put(Product.SteelCable, 100.00);

		frame = new StoreFrame(this);
		frame.setVisible(true);
	}

	@Override
	public  void handleMessage(String s) {
		Product product = Product.valueOf(s);

		if (!prices.containsKey(product)) {
			throw new UnsupportedOperationException("Shop can't sell " + product.toString());
		}

		setStatus("selling");

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		setStatus("finished");

		bank += prices.get(product);
	}

	@Override
	public ChainLinkFrame getFrame() {
		return frame;
	}

	public void setStatus(String status) {
		this.status = ShopStatus.valueOf(status);
		// invoke GUI updater on EDT 
		SwingUtilities.invokeLater(new StatusUpdater(this));
	}

	@Override
	public String getStatus() {
		Locale locale = new Locale("en", "US");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return "Shop is " + status.toString() + ", " + currencyFormatter.format(bank) + " in the bank";
	}
}