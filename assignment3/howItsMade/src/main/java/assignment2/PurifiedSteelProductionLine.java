public class PurifiedSteelProductionLine extends ProductionLine {
	public PurifiedSteelProductionLine(Factory factory, Backlog backlog) {
		super(factory, backlog);
	}

	@Override
	public Product convert() {
		return Product.PurifiedSteel;
	}
}
