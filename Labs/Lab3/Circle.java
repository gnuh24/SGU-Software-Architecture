package Labs.Lab3;

public class Circle extends Shape {
	private final double radius;

	public Circle(double radius) {
		validatePositiveValue(radius, "Radius");
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public double calculateArea() {
		return Math.PI * Math.pow(radius, 2);
	}
}
