// manages the stock of raw material for factories
public class Backlog {
	private int value = 0;

	public synchronized int get() {
		return value;
	}

	public synchronized void increment() {
		value++;
		notify();
	}

	public synchronized void decrement() {
		value--;
		notify();
	}
}
