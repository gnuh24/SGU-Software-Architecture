public class UserRegistration {
	public static boolean registerUser(String username, String password) {
		if (UserAuthenticator.authenticateUser(username, password)) {
			// Register user in the database
			return true;
		}
		return false;
	}
}
