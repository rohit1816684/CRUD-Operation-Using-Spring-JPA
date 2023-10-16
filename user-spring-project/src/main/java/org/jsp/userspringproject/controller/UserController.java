package org.jsp.userspringproject.controller;

import java.util.Scanner;

import org.jsp.userspringproject.UserConfig;
import org.jsp.userspringproject.dao.UserDao;
import org.jsp.userspringproject.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	static UserDao dao;
	static Scanner sc = new Scanner(System.in);
	static ApplicationContext context;

	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		dao = context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		boolean b = true;
		while (b) {
			System.out.println("1. Save user");
			System.out.println("2. Update user");
			System.out.println("3. Find User By Id");
			System.out.println("4. Delete User By Id");
			System.out.println("5. Verify User By Id and Password");
			System.out.println("6.  Verify User By phone and Password");
			System.out.println("7.  Verify User By Email and Password");

			switch (sc.nextInt()) {
			case 1: {
				save();
				break;
			}
			case 2: {
				update();
				break;
			}
			case 3: {
				findById();
				break;
			}
			case 4: {
				delete();
				break;
			}
			case 5: {
				verifyById();
				break;
			}
			case 6: {
				verifyByPhone();
				break;
			}
			case 7: {
				verifyByEmail();
				break;
			}

			default:
				System.out.println("ThankYou!!!!Application Closed");
				((AnnotationConfigApplicationContext) context).close();
				b = false;
			}
		}
	}

	public static void save() {
		System.out.println("Enter your name,phone,email and password to register");
		User u = new User();
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = dao.saveUser(u);
		System.out.println("User saved with id: " + u.getId());
	}

	public static void update() {
		System.out.println("Enter the User id to update");
		int id = sc.nextInt();
		System.out.println("Enter your name,phone,email and password to update");
		User u = new User();
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = dao.updateUser(u);
		System.out.println("User update with Id: " + u.getId());
	}

	public static void findById() {
		System.out.println("Enter the User Id to display details");
		int id = sc.nextInt();
		User u = dao.findById(id);
		if (u != null) {
			System.out.println("User with id " + id + " found");
			System.out.println("User id :" + u.getId());
			System.out.println("User name: " + u.getName());
			System.out.println("User phone number: " + u.getPhone());
			System.out.println("User Email: " + u.getEmail());
			System.out.println("---------------------------------------");
		} else {
			System.err.println("You have enter Invalid Id");
		}
	}

	public static void delete() {
		System.out.println("Enter the User Id to delete ");
		int id = sc.nextInt();
		boolean deleted = dao.deleteById(id);
		if (deleted) {
			System.out.println("User Found and Deleted");
		} else {
			System.err.println("You have enter an invalid id");
		}
	}

	public static void verifyByPhone() {
		System.out.println("Enter your Phone and Password to verify");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = dao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("User verifyed Successfully");
			System.out.println("User Id: " + u.getId());
			System.out.println("User Name: " + u.getName());
			System.out.println("User Phone number: " + u.getPhone());
			System.out.println("User Email: " + u.getEmail());
			System.out.println("------------------------------------------");
		} else
			System.err.println("Invalid phone or password");
	}

	public static void verifyByEmail() {
		System.out.println("Enter your Email and password to verify User");
		String email = sc.next();
		String password = sc.next();
		User u = dao.verifyUser(email, password);
		if (u != null) {
			System.out.println("User verifyed Succesfully");
			System.out.println("User Id: " + u.getId());
			System.out.println("User Name: " + u.getName());
			System.out.println("User phone: " + u.getPhone());
			System.out.println("User Email: " + u.getEmail());
			System.out.println("---------------------------------");
		} else
			System.err.println("Invalid Email or password");
	}

	public static void verifyById() {
		System.out.println("Enter the User Id and password to verify User");
		int id = sc.nextInt();
		String password = sc.next();
		User u = dao.verifyUser(id, password);
		if (u != null) {
			System.out.println("User verifyed Succesfully");
			System.out.println("User Id: " + u.getId());
			System.out.println("User Name: " + u.getName());
			System.out.println("User phone: " + u.getPhone());
			System.out.println("User Email: " + u.getEmail());
			System.out.println("---------------------------------");
		} else
			System.err.println("Invalid Email or password");
	}

}
