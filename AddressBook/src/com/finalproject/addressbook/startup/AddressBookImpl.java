package com.finalproject.addressbook.startup;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import com.finalproject.addressbook.methods.AddressBookBirthdays;
import com.finalproject.addressbook.methods.AddressBookContactDeletion;
import com.finalproject.addressbook.methods.AddressBookContactFinder;
import com.finalproject.addressbook.methods.AddressBookContactsAddition;
import com.finalproject.addressbook.methods.AddressBookContactsDisplay;
import com.finalproject.addressbook.methods.AddressBookContactsMoving;
import com.finalproject.addressbook.methods.AddressBookCreation;
import com.finalproject.addressbook.methods.AddressBookLogin;
import com.finalproject.addressbook.methods.AddressBookSorting;
import com.finalproject.addressbook.model.AccountDetails;

public class AddressBookImpl {

	private static Scanner scn = new Scanner(System.in);
	private static String userName;
	private static String password;

	public static void main(String[] args) throws ParseException, IOException {
		displayWelcome();
		if (startProcess()) {
			selectMenuOptions();
		}
	}

	private static void displayWelcome() {
		System.out
				.println("		********* Welcome To Address Book Application *********		");
		System.out
				.println("		-------------------------------------------------------		");
		System.out.println("Login to the account to access the address book");
		System.out.println("Please press '1' to sign-in.");
		System.out
				.println("If you are new user, else press '2' to sign-up [Create a new Account].");
	}

	private static boolean startProcess() throws ParseException, IOException {
		String input = null;
		AccountDetails acc = null;
		while (true) {
			try {
				input = scn.nextLine();
				if (input.equals("1")) {
					acc = AddressBookLogin.logIntoAccount();
					if (acc != null && acc.getUserName() != null
							&& acc.getPassword() != null) {
						userName = acc.getUserName();
						password = acc.getPassword();
						break;
					} else {
						System.out
								.println("Re-enter the option '1' for log-in and '2' for create addressbook");
					}
				} else if (input.equalsIgnoreCase("2")) {
					if (AddressBookCreation.createAccountForAddressBook()) {
						System.out.println("Please log-in now.");
						acc = AddressBookLogin.logIntoAccount();
						if (acc != null && acc.getUserName() != null
								&& acc.getPassword() != null) {
							userName = acc.getUserName();
							password = acc.getPassword();
							break;
						}
					}
				} else {
					System.out.println("Input is invalid please try again!! ");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Input is invalid please try again!! ");
			}
		}
		return true;
	}

	private static void selectMenuOptions() throws IOException {
		String option = null;
		boolean keepAlive = true;
		System.out.println("Near birthdays of your contacts!!");
		AddressBookBirthdays.getBirthdaysList(userName);
		System.out
				.println("Choose any of the below options to use the address book :-");
		System.out.println("Choose by pressing a key!!!");
		displayMenu();
		try {
			while (keepAlive) {
				option = scn.nextLine();
				switch (option) {
				case "1":
					AddressBookContactsDisplay.displayAddressBook(userName);
					replayMenu();
					break;
				case "2":
					AddressBookContactsAddition.addContacts(userName);
					replayMenu();
					break;
				case "3":
					AddressBookSorting.sortAddressBook(userName);
					replayMenu();
					break;
				case "4":
					AddressBookContactDeletion.removeContact(userName);
					replayMenu();
					break;
				case "5":
					AddressBookContactsMoving.moveContacts(userName);
					replayMenu();
					break;
				case "6":
					AddressBookContactFinder.findContacts(userName);
					replayMenu();
					break;
				case "7":
					displayWelcome();
					userName = null;
					if (startProcess()) {
						selectMenuOptions();
					}
					break;
				case "8":
					// XORPassword.encryptFile(userName, password, "");
					System.out.println("Exit!!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option try again!!");
					displayMenu();
				}
			}
		} catch (Exception e) {
			System.out.println("Menu selection failed!!");
		}
	}

	private static void replayMenu() throws IOException {
		System.out.println("Want to continue? Press 'Y' else any other key");
		String continueInput = scn.nextLine();
		if (continueInput != null && continueInput.equalsIgnoreCase("Y")) {
			displayMenu();
		} else {
			// XORPassword.encryptFile(userName, password, "");
			System.out.println("Exit!!");
			System.out.println("Thanks and visit again!! :-)");
			System.exit(0);
		}
	}

	private static void displayMenu() {
		System.out
				.println("---------------------------------------------------------");
		System.out
				.println("Press '1' to display all contacts in the address book");
		System.out.println("Press '2' to add a contact to the address book");
		System.out
				.println("Press '3' to get contacts by specific category [lastName, fistName, email, date-of-birth, address]");
		System.out
				.println("Press '4' to remove a contact from the address book");
		System.out.println("Press '5' to move a contact to other address book");
		System.out.println("Press '6' to find a contact in the address book");
		System.out.println("Press '7' to log-in to another address book");
		System.out.println("Press '8' to exit from the menu");
	}
}
