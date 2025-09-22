public class UserManagerApp {
	public static void main(String[] args) {

		UserValidator validator = new UserValidator();
		UserRegistration userRegistration = new UserRegistration(validator);
		UserAuthenticator userAuthenticator = new UserAuthenticator(validator);

		// Valid user data
		User validUser = new User("john_doe", "Password123");
		userRegistration.registerUser(validUser); // Should register successfully
		userAuthenticator.authenticateUser(validUser); // Should authenticate successfully


		// Invalid user data
		User invalidUser1 = new User("abc", "Password123");
		userRegistration.registerUser(invalidUser1); // Invalid username (too short)
		userAuthenticator.authenticateUser(invalidUser1); // Invalid username (too short)


		User invalidUser2 = new User("john_doe", "password123");
		userRegistration.registerUser(invalidUser2); // Invalid password (no uppercase)
		userAuthenticator.authenticateUser(invalidUser2); // Invalid password (no uppercase)


		User invalidUser3 = new User("john_doe", "Password");
		userRegistration.registerUser(invalidUser3); // Invalid password (no digit)
		userAuthenticator.authenticateUser(invalidUser3); // Invalid password (no digit)


	}
}
