package com.finalproject.addressbook.methods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class AddressBookContactFinder {

	private static String fileName;
	private static Scanner scn = new Scanner(System.in);

	public static void findContacts(String fName) {
		if (fName != null) {
			fileName = fName;
			List<ContactDetails> result = searchAndFetchContacts();
			if (result != null && result.size() > 0) {
				AddressBookContactsDisplay.displaySortedAddressBook(result);
			} else {
				System.out
						.println("Cannot find the contacts in the address book!!");
			}
		} else {
			System.out.println("File name invalid!!");
		}
	}

	private static List<ContactDetails> searchAndFetchContacts() {
		boolean isInputValid = false;
		String option = null;
		List<ContactDetails> contactsList = new ArrayList<ContactDetails>();
		try {
			contactsList = ReadFromFile.readFromFile(fileName,
					AddressBookConstants.FIND_MODIFIER);
			if (contactsList != null && contactsList.size() > 0) {
				Iterator<ContactDetails> iterator = contactsList.iterator();

				System.out.println("Choose option below for finding!!");
				System.out
						.println("Press '1' for find by characters containing in the last name");
				System.out
						.println("Press '2' for find by first letters of the last name");
				System.out
						.println("Press '3' for find by last letters of the last name");
				System.out.println("Press '4' for find by exact last name");

				String optionRegex = "[1-4]";
				boolean isOptionInValid = true;
				while (isOptionInValid) {
					option = scn.nextLine();
					if (option != null) {
						if (option.matches(optionRegex)) {
							isOptionInValid = false;
						}
					}
					if (isOptionInValid) {
						System.out
								.println("Invalid option, please re-enter the option!!");
					}
				}

				while (!isInputValid) {
					System.out.println("Enter the find pattern as characters");
					String lastName = scn.nextLine().toLowerCase();
					if (lastName != null && !lastName.equals("")) {
						isInputValid = true;

						while (iterator.hasNext()) {
							ContactDetails contacts = iterator.next();
							boolean isFound = false;
							if (contacts != null) {
								switch (option) {
								case "1":
									if (contacts.getLastName() != null
											&& contacts.getLastName()
													.toLowerCase().trim()
													.contains(lastName)) {
										isFound = true;
									}
									break;
								case "2":
									if (contacts.getLastName() != null
											&& contacts.getLastName()
													.toLowerCase().trim()
													.startsWith(lastName)) {
										isFound = true;
									}
									break;
								case "3":
									if (contacts.getLastName() != null
											&& contacts.getLastName()
													.toLowerCase().trim()
													.endsWith(lastName)) {
										isFound = true;
									}
									break;
								case "4":
									if (contacts.getLastName() != null
											&& contacts.getLastName()
													.toLowerCase().trim()
													.equals(lastName)) {
										isFound = true;
									}
									break;
								}
								if (!isFound) {
									iterator.remove();
									isFound = false;
								}
							}
						}
					} else {
						System.out
								.println("Input invalid!! Re-Enter lastName starting or containing or ending  characters");
					}
				}
			}
		} catch (Exception e) {
			System.out
					.println(" Failed to perform search and fetch contacts!!");
		}
		return contactsList;
	}
}
