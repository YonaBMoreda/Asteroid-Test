public class SteelCableProductionLine extends ProductionLine {
	public SteelCableProductionLine(Factory factory, Backlog backlog) {
		super(factory, backlog);
	}

	@Override
	public Product convert() {
		return Product.SteelCable;
	}
}