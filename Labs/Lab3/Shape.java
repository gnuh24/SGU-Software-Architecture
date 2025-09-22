package Labs.Lab3;

public abstract class Shape {
	public abstract double calculateArea();

	// Validation helper
	protected void validatePositiveValue(double value, String fieldName) {
		if (value <= 0) {
			throw new IllegalArgumentException(fieldName + " must be positive");
		}
	}
}

