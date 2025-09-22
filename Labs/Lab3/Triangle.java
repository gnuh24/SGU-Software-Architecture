package Labs.Lab3;

public class Triangle extends Shape {
	private final double base;
	private final double height;

	public Triangle(double base, double height) {
		validatePositiveValue(base, "Base");
		validatePositiveValue(height, "Height");
		this.base = base;
		this.height = height;
	}

	@Override
	public double calculateArea() {
		return 0.5 * base * height;
	}
}

