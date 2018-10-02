import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {

		// production chain 1
		Map<String, InetSocketAddress> links1 = new HashMap<String, InetSocketAddress>();
		links1.put("Mine", new InetSocketAddress("localhost", 1500));
		links1.put("PurifiedSteelFactory", new InetSocketAddress("localhost", 1600));
		links1.put("SteelProductFactory", new InetSocketAddress("localhost", 1650));
		links1.put("Store", new InetSocketAddress("localhost", 1700));

		Store store = new Store(
				links1.get("Store"), 
				new InetSocketAddress[] {links1.get("SteelProductFactory")}
				);

		Mine mine = new Mine(
				links1.get("Mine"), 
				new InetSocketAddress[]{links1.get("PurifiedSteelFactory")}, 
				2   											// number of miners
				);
		PureSteelFactory purFactory = new PureSteelFactory(
				links1.get("PurifiedSteelFactory"), 
				new InetSocketAddress[]{links1.get("SteelProductFactory")}, 
				new InetSocketAddress[]{links1.get("Mine")},
				1											// number of production lines
				);
		SteelProductFactory proFactory = new SteelProductFactory(
				links1.get("SteelProductFactory"), 
				new InetSocketAddress[]{links1.get("Store")},
				new InetSocketAddress[]{links1.get("PurifiedSteelFactory")},
				2, 0									//cable lines, plate lines
				);

		// production chain 2
		Map<String, InetSocketAddress> links2 = new HashMap<String, InetSocketAddress>();
		links2.put("Mine 1", new InetSocketAddress("localhost", 2500));
		links2.put("Mine 2", new InetSocketAddress("localhost", 2510));
		links2.put("PurifiedSteelFactory 1", new InetSocketAddress("localhost", 2600));
		links2.put("PurifiedSteelFactory 2", new InetSocketAddress("localhost", 2610));
		links2.put("SteelProductFactory 1", new InetSocketAddress("localhost", 2650));
		links2.put("SteelProductFactory 2", new InetSocketAddress("localhost", 2660));
		links2.put("SteelProductFactory 3", new InetSocketAddress("localhost", 2670));
		links2.put("Store 1", new InetSocketAddress("localhost", 2700));
		links2.put("Store 2", new InetSocketAddress("localhost", 2710));

		Store store1 = new Store(
				links2.get("Store 1"), 
				new InetSocketAddress[] {links2.get("SteelProductFactory 1"), links2.get("SteelProductFactory 2")}
				);
		Store store2 = new Store(
				links2.get("Store 2"), 
				new InetSocketAddress[] {links2.get("SteelProductFactory 2"), links2.get("SteelProductFactory 3")}	
				);

		Mine mine1 = new Mine(
				links2.get("Mine 1"), 
				new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1")}, 
				2 // number of miners
				);
		Mine mine2 = new Mine(
				links2.get("Mine 2"), 
				new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1"), links2.get("PurifiedSteelFactory 2")}, 
				3 // number of miners
				);
		PureSteelFactory purFactory1 = new PureSteelFactory(
				links2.get("PurifiedSteelFactory 1"), 
				new InetSocketAddress[]{links2.get("SteelProductFactory 1"), links2.get("SteelProductFactory 2")}, 
				new InetSocketAddress[]{links2.get("Mine 1"), links2.get("Mine 2")},
				2 // number of production lines
				);
		PureSteelFactory purFactory2 = new PureSteelFactory(
				links2.get("PurifiedSteelFactory 2"), 
				new InetSocketAddress[]{links2.get("SteelProductFactory 2"), links2.get("SteelProductFactory 3")}, 
				new InetSocketAddress[]{links2.get("Mine 2")},
				1 // number of production lines
				);
		SteelProductFactory proFactory1 = new SteelProductFactory(
				links2.get("SteelProductFactory 1"), 
				new InetSocketAddress[]{links2.get("Store 1")},
				new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1")},
				2, 0 //cable lines, plate lines
				);
		SteelProductFactory proFactory2 = new SteelProductFactory(
				links2.get("SteelProductFactory 2"), 
				new InetSocketAddress[]{links2.get("Store 1"), links2.get("Store 2")},
				new InetSocketAddress[]{links2.get("PurifiedSteelFactory 1"), links2.get("PurifiedSteelFactory 2")},
				1, 1 //cable lines, plate lines
				);
		SteelProductFactory proFactory3 = new SteelProductFactory(
				links2.get("SteelProductFactory 3"), 
				new InetSocketAddress[]{links2.get("Store 2")},
				new InetSocketAddress[]{links2.get("PurifiedSteelFactory 2")},
				0, 2 //cable lines, plate lines
				);
	}
}
