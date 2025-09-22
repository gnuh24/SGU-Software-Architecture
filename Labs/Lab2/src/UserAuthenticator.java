public class UserAuthenticator {
	public static boolean authenticateUser(String username, String password) {
		if (UserValidator.validateUsername(username) && UserValidator.validatePassword(password)) {
			// Authenticate user
			return true;
		}
		return false;

	}
}
