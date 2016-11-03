package com.finalproject.addressbook.methods;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class AddressBookContactDeletion {
	private static String fileName = null;
	private static Scanner scn = new Scanner(System.in);
	private static boolean foundFlag = false;

	public static void removeContact(String fName) {
		if (fName != null && !fName.equals("")) {
			fileName = fName;
			removeDetails();
			if (foundFlag) {
				foundFlag = false;
				System.out.println("Deleted contact succesfully");
			}
		} else {
			System.out.println("Invalid file name");
		}
	}

	private static void removeDetails() {
		try {
			List<ContactDetails> addressBook = ReadFromFile.readFromFile(
					fileName, AddressBookConstants.DELETE_MODIFIER);
			if (addressBook != null) {
				if (addressBook.size() > 0) {
					System.out
							.println("Enter the lastName/firstName/email of the contact that is to be removed");
					String removeInput = scn.nextLine().toLowerCase().trim();
					if (removeInput != null && !removeInput.equals("")) {

						Iterator<ContactDetails> contactList = addressBook
								.iterator();
						while (contactList.hasNext() && !foundFlag) {
							ContactDetails contact = contactList.next();
							if (contact.getLastName() != null
									&& contact.getLastName().trim()
											.equalsIgnoreCase(removeInput)) {
								contactList.remove();
								foundFlag = true;
							} else if (contact.getFirstName() != null
									&& contact.getFirstName().trim()
											.equalsIgnoreCase(removeInput)) {
								contactList.remove();
								foundFlag = true;
							} else if (contact.getEmailAddress() != null
									&& contact.getEmailAddress().trim()
											.equalsIgnoreCase(removeInput)) {
								contactList.remove();
								foundFlag = true;
							}
						}
						if (!foundFlag) {
							System.out.println("Input not found!!");
						} else {
							WriteToFile.writeToFile(addressBook, fileName);
						}

					} else {
						System.out.println("Input not found!!");
					}
				} else {
					System.out.println("Address Book is empty.");
					System.out.println("Contacts cannot be removed!!");
				}
			} else {
				System.out.println("Address Book is empty.");
				System.out.println("Contacts cannot be removed!!");
			}
		} catch (Exception e) {
			System.out.println("Failed to add contact to address book " + e);
		}
	}
}