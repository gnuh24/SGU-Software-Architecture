public class UserManagerApp {
    public static void main(String[] args) {
        String username = "john_doe";
        String password = "Password123";

        if (UserRegistration.registerUser(username, password)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User registration failed.");
        }

        // Separate login functionality
        if (UserAuthenticator.authenticateUser(username, password)) {
            System.out.println("User logged in successfully.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}