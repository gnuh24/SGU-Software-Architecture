public class UserValidator {
	public static boolean validateUsername(String username) {
		return username.length() >= 5 && username.matches("[a-zA-Z_0-9]+");
	}

	public static boolean validatePassword(String password) {
		return password.length() >= 8 && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
	}
}
