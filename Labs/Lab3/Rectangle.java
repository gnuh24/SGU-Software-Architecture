package Labs.Lab3;

public class Rectangle extends Shape {
	private final double width;
	private final double height;

	public Rectangle(double width, double height) {
		validatePositiveValue(width, "Width");
		validatePositiveValue(height, "Height");
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public double calculateArea() {
		return width * height;
	}
}
