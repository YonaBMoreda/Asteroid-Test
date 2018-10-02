import java.net.InetSocketAddress;

public class SteelProductFactory extends Factory {
	public SteelProductFactory(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks, int numberOfCableLines, int numberOfPlateLines) {
		super(address, nextLinks, previousLinks);

		for(int i = 0; i < numberOfCableLines; i++) {
			// create cable production line
			SteelCableProductionLine line = new SteelCableProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();
		}

		for(int i = 0; i < numberOfPlateLines; i++) {
			// create plate production line
			SteelPlateProductionLine line = new SteelPlateProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();
		}
	}

	public boolean checkProduct(Product product) {
		// only accept PurifiedSteel
		if (product != Product.PurifiedSteel) {
			throw new UnsupportedOperationException("SteelProductFactory does not accept anything other than PurifiedSteel");
		} else {
			return true;
		}
	}

	public  void acceptProduct(Product product) {
		if (product == Product.SteelCable || product == Product.SteelPlate){
			try {
				send(product);
			} catch (Exception e) {
				System.out.println("Unable to send finished steel product from the factory to the store");
				System.out.println(e);
			}
		} else {
			throw new UnsupportedOperationException("Factory expected to receive " + Product.SteelCable.toString() + " or " + Product.SteelPlate.toString() + " but got " + product.toString());
		}
	}
}
