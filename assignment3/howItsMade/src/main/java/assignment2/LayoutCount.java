// displays windows neatly. First next to each other, then at a second row

public class LayoutCount {
	public static int totalHorizontalPixels;
	public static int totalVerticalPixels;
	public static int windowWidth = 250;
	public static int windowHeight = 500;

	public static void addPixels() {

		totalHorizontalPixels += windowWidth;
		if (totalHorizontalPixels > 1700) {
			totalHorizontalPixels = 0;
			totalVerticalPixels = 510;
		}
	}

	public static int getHorizontalPixels() {
		return totalHorizontalPixels;
	}

	public static int getVerticalPixels() {
		return totalVerticalPixels;
	}
}
