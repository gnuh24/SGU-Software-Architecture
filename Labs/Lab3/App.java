package Labs.Lab3;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Shape circle = new Circle(5.0); // ~78.54
		System.out.println("Circle area: " + circle.calculateArea());

		Shape rectangle = new Rectangle(4.0, 6.0); // 24.0
		System.out.println("Rectangle area: " + rectangle.calculateArea());

		Shape triangle = new Triangle(3.0, 8.0); // 12.0
		System.out.println("Triangle area: " + triangle.calculateArea());

		List<Shape> shapes = Arrays.asList(circle, rectangle, triangle);

		ShapeCalculator calc = new ShapeCalculator();
		System.out.println("Total area: " + calc.calculateTotalArea(shapes)); // ~114.54

		// Testing with invalid shapes
		try {
			Shape invalidCircle = new Circle(-5.0);
			System.out.println("Circle area: " + invalidCircle.calculateArea());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage()); // Radius must be positive
		}

		try {
			Shape invalidRectangle = null;
			System.out.println("Circle area: " + invalidRectangle.calculateArea());
		} catch (NullPointerException e) {
			System.err.println("Invalid: Shape is null"); // Null pointer exception
		}

	}
}
