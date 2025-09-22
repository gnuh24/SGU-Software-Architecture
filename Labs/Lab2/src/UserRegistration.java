public class UserRegistration {
	private UserValidator validator = new UserValidator();

	public UserRegistration(UserValidator validator) {
		this.validator = validator;
	}

	public UserValidator getValidator() {
		return validator;
	}

	public void setValidator(UserValidator validator) {
		this.validator = validator;
	}

	public boolean registerUser(User user) {
		if (validator.validateUser(user)) {
			// Logic đăng ký
			System.out.println("User registered successfully.");
			return true;
		}
		System.out.println("Invalid username or password.");
		return false;
	}
}
