public class UserAuthenticator {
	private UserValidator validator;

	public UserAuthenticator(UserValidator validator) {
		this.validator = validator;
	}

	public UserValidator getValidator() {
		return validator;
	}

	public void setValidator(UserValidator validator) {
		this.validator = validator;
	}

	public boolean authenticateUser(User user) {
		if (validator.validateUser(user)) {
			// Logic xác thực đăng nhập
			System.out.println("User logged in successfully.");
			return true;
		}
		System.out.println("Invalid username or password.");
		return false;
	}

}
