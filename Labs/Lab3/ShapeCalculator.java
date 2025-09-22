package Labs.Lab3;

import java.util.List;

public class ShapeCalculator {

	public double calculateTotalArea(List<Shape> shapes) {
		if (shapes == null) {
			return 0.0;
		}

		double total = 0.0;
		for (Shape shape : shapes) {
			if (shape != null) {
				total += shape.calculateArea();
			}
		}
		return total;
	}
}
