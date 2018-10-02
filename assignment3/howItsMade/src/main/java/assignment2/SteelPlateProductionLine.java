public class SteelPlateProductionLine extends ProductionLine {
	public SteelPlateProductionLine(Factory factory, Backlog backlog) {
		super(factory, backlog);
	}
	
	@Override
	public Product convert() {
		return Product.SteelPlate;
	}
}