import java.net.InetSocketAddress;

public class PureSteelFactory extends Factory {
	public PureSteelFactory(InetSocketAddress address, InetSocketAddress[] nextLinks, InetSocketAddress[] previousLinks, int numberOfLines) {
		super(address, nextLinks, previousLinks);

		// create production lines
		for(int i = 0; i < numberOfLines; i++) {
			PurifiedSteelProductionLine line = new PurifiedSteelProductionLine(this, backlog);
			Thread t = new Thread(line);
			t.start();					// each production line is separate thread
		}
	}

	public boolean checkProduct(Product product) {
		// only accept Ore
		if (product != Product.Ore) {
			throw new UnsupportedOperationException("PureSteelFactory does not accept anything other than Ore");
		} else {
			return true;
		}
	}

	public  void  acceptProduct(Product product) {
		if (product == Product.PurifiedSteel){
			try {
				send(product);
			} catch (Exception e) {
				System.out.println("Unable to send purified steel from factory to the other factory");
				System.out.println(e);
			}
		} else {
			throw new UnsupportedOperationException("Factory expected to receive " + Product.PurifiedSteel.toString() + " but got " + product.toString());
		}
	}
}
