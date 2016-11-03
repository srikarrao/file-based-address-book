package com.finalproject.addressbook.methods;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Class to create the address book
 * 
 *
 */
public class AddressBookCreation {

	private static Scanner scn = new Scanner(System.in);
	private static String userName;
	private static String password;

	/**
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public static boolean createAccountForAddressBook() throws ParseException,
			IOException {
		boolean isAccountCreated = false;
		System.out.println("Creating an account for addressBook");
		while (!isAccountCreated) {
			try {
				if (createUserName() && createPassword()) {
					new FileOutputStream(userName + ".txt");
					// XORPassword.encryptFile(userName, password,"create");
					System.out.println("Account successfully created!!");
					isAccountCreated = true;
				} else {
					System.out
							.println("Account unable to create!! Please re-try");
				}
			} catch (Exception e) {
				System.out
						.println("Exception occurred during account creation :: "
								+ e);
			}
		}
		return true;
	}

	/**
	 * method to create userName for an account
	 */
	public static boolean createUserName() {

		boolean isUserNameValid = false;
		System.out.println("Please Enter a userName");
		while (!isUserNameValid) {
			try {
				userName = scn.nextLine().toLowerCase();
				if (userName != null && !userName.equals("")) {
					File f = new File(userName + ".txt");
					if (f != null && f.exists()) {
						System.out
								.println("UserName taken, please try another username");
					} else {
						isUserNameValid = true;
					}
				} else {
					System.out.println("Please re-enter a valid username");
				}
			} catch (Exception e) {
				System.out
						.println("Error occurred during account creation due to: "
								+ e);
			}
		}
		return true;
	}

	/**
	 * method to create a password for an account
	 */
	public static boolean createPassword() {
		boolean isPasswordValid = false;

		while (!isPasswordValid) {
			System.out.println("Enter the password");
			try {
				password = scn.nextLine();
				System.out.println("Confirm the password");
				String cofirmationPassword = scn.nextLine();

				if (password != null && cofirmationPassword != null
						&& password.equals(cofirmationPassword)) {
					isPasswordValid = true;
				} else {
					System.out.println("Try again the password confirmation!!");
				}
			} catch (Exception e) {
				System.out
						.println("Error occurred during password creation due to: "
								+ e);
			}
		}
		return true;
	}
}
