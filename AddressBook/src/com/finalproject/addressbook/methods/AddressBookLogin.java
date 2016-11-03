package com.finalproject.addressbook.methods;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import com.finalproject.addressbook.model.AccountDetails;

public class AddressBookLogin {
	private static String userName;
	private static String password;
	private static Scanner scn = new Scanner(System.in);

	public static AccountDetails logIntoAccount() throws IOException {
		if (isLogged()) {
			AccountDetails accDetails = new AccountDetails();
			accDetails.setUserName(userName);
			accDetails.setPassword(password);
			return accDetails;
		}
		return null;
	}

	private static boolean isLogged() throws IOException {
		boolean isLogged = false;
		File f = null;
		int i = 0;
		while (!isLogged && i < 3) {
			try {
				System.out.println("Enter username");
				userName = scn.nextLine().toLowerCase();
				if (userName != null && !userName.equals("")) {
					f = new File(userName + ".txt");
					if (f == null || !f.exists()) {
						System.out
								.println("UserName Invalid :: Please enter re-try!!");
						i++;
					} else {
						System.out
								.println("Enter password for the address book!!");
						password = scn.nextLine().toLowerCase();
						// XORPassword.decryptFile(userName, password);
						System.out.println("Succesfully Logged in!!");
						isLogged = true;
					}
				} else {
					System.out.println("Enter a valid username");
				}
			} catch (IllegalArgumentException ie) {
				System.out.println("Failed to log into the account " + ie);
			}
		}
		return isLogged;
	}
}
