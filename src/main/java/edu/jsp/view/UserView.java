package edu.jsp.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import edu.jsp.controller.UserController;
import edu.jsp.model.User;

public class UserView {
	private static Scanner scanner = new Scanner(System.in);
	private static UserController userController = new UserController();

	public static void main(String[] args) {
		while (true) {
			welcome();
			switch (scanner.nextInt()) {
			case 1 -> saveUser();
			case 2 -> updateUser();
			case 3 -> findUserById();
			case 4 -> findUserByEmailAndPassword();
			case 5 -> deleteUser();
			case 6 -> findAllUsers();
			case 7 -> System.exit(0);
			}
		}
	}

	private static void welcome() {
		System.out.println("1.create User");
		System.out.println("2.Update");
		System.out.println("3.fetch User By id");
		System.out.println("4.fetch user by email and password");
		System.out.println("5.delete");
		System.out.println("6.fetch All Users");
		System.out.println("7.exit");
		System.out.println("enter your choice ");
	}

	public static void saveUser() {
		System.out.println("enter your details");
		User user = new User();
		System.out.println("enter the name");
		user.setName(scanner.next());
		System.out.println("enter the email");
		user.setEmail(scanner.next());
		System.out.println("enter the password");
		user.setPassword(scanner.next());
		System.out.println("enter the phone");
		user.setPhone(scanner.nextLong());
		System.out.println("enter the address");
		scanner.nextLine();
		user.setAddress(scanner.nextLine());
		System.out.println("enter the dob");
		System.out.println("example :  YYYY-MM-DD enter in this format");
		user.setDob(LocalDate.parse(scanner.next()));

		User savedUser = userController.saveUser(user);
		if (savedUser != null)
			System.out.println("user details saved ");
		else
			System.out.println("404  error");
	}

	private static void findUserByEmailAndPassword() {
		System.out.println("enter your email and password");
		String email = scanner.next();
		String password = scanner.next();
		User user = userController.findUser(email, password);
		if (user == null)
			System.out.println(
					"some thing error occurred may be user details not found for the specified email : %s and password %S"
							.formatted(email, password));
		else
			System.out.println(user);
	}

	private static void updateUser() {
		System.out.println("enter the id");
		int id = scanner.nextInt();
		System.out.println("enter the name to be updated");
		String name = scanner.next();
		User user = userController.updateUser(id, name);
		if (user != null)
			System.out.println("user details updated successfully");
		else
			System.out.println("some thing error occurred may be user details not found for the specified number : %d"
					.formatted(id));
	}

	private static void findAllUsers() {
		List<User> users = userController.findAllUsers();
		users.stream().forEach(user -> System.out.println(user));
	}

	private static void deleteUser() {
		System.out.println("enter the id");
		int id = scanner.nextInt();
		boolean result = userController.deleteUser(id);
		if (result)
			System.out.println("user account deleted successfully");
		else
			System.out.println("some thing error occurred may be user details not found for the specified number :  %d"
					.formatted(id));
	}

	private static void findUserById() {
		System.out.println("enter the id");
		int id = scanner.nextInt();
		User user = userController.findUserById(id);
		if (user != null) {
			System.out.println("user found with id : %d".formatted(id));
			System.out.println(user);
		} else
			System.out.println("some thing error occurred may be user details not found for the specified number :  %d"
					.formatted(id));
	}

}
