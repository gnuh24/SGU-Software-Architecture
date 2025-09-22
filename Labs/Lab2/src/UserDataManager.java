/*
 * 
 * In this file you can find violation of Single Responsibility Principle. You should refactor this
 * file. You can remove this file completely, because it will not be used during the evaluation of
 * the solution.
 * 
 */

public class UserDataManager {
	private String username;
	private String password;

	public UserDataManager(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void registerUser() {
		if (UserRegistration.registerUser(username, password)) {
			System.out.println("User registered successfully.");
		} else {
			System.out.println("Invalid username or password.");
		}
	}

	public void loginUser() {
		if (UserAuthenticator.authenticateUser(username, password)) {
			System.out.println("User logged in successfully.");
		} else {
			System.out.println("Invalid username or password.");
		}
	}


	public static void main(String[] args) {
		UserDataManager userManager = new UserDataManager("john_doe", "Password123");
		userManager.registerUser();
		userManager.loginUser();
	}
}
