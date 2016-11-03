package com.finalproject.addressbook.methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class AddressBookContactsMoving {
	private static String srcName = null;
	private static String destName = null;
	private static Scanner scn = new Scanner(System.in);
	private static List<ContactDetails> destContacts;
	private static List<ContactDetails> sourceContacts;
	private static ContactDetails moveContact;

	public static void moveContacts(String srcFileName) throws IOException {

		try {
			if (srcFileName != null) {
				srcName = srcFileName;
				getFromSrc();
				getFromDest();
				if (moveContact != null && destContacts != null) {
					moveFromSrcToDest();
				} else {
					System.out.println("Contact moving failed!!");
				}
			} else {
				System.out.println("Destination file invalid!!");
			}
		} catch (Exception e) {
			System.out.println("Contact moving failed");
		}
	}

	private static void getFromSrc() {
		boolean foundFlag = false;
		try {
			sourceContacts = ReadFromFile.readFromFile(srcName,
					AddressBookConstants.MOVE_MODIFIER);
			if (sourceContacts != null) {
				if (sourceContacts.size() > 0) {
					System.out
							.println("Enter the lastName/firstName/email of the contact that is to be moved");
					String moveInput = scn.nextLine().toLowerCase().trim();
					if (moveInput != null && !moveInput.equals("")) {
						Iterator<ContactDetails> contactList = sourceContacts
								.iterator();
						while (contactList.hasNext() && !foundFlag) {
							ContactDetails contact = contactList.next();
							if (contact.getLastName() != null
									&& contact.getLastName().trim()
											.equalsIgnoreCase(moveInput)) {
								moveContact = contact;
								contactList.remove();
								foundFlag = true;
							} else if (contact.getFirstName() != null
									&& contact.getFirstName().trim()
											.equalsIgnoreCase(moveInput)) {
								moveContact = contact;
								contactList.remove();
								foundFlag = true;
							} else if (contact.getEmailAddress() != null
									&& contact.getEmailAddress().trim()
											.equalsIgnoreCase(moveInput)) {
								moveContact = contact;
								contactList.remove();
								foundFlag = true;
							}
						}
					}
					if (moveContact == null) {
						System.out.println("Contact not found!!");
					} else {
						WriteToFile.writeToFile(sourceContacts, srcName);
					}
				}
			} else {
				System.out
						.println("No contacts in the address book, unable to move contacts");
			}
		} catch (Exception e) {
			System.out
					.println("Failed to get contacts from source address book");
		}
	}

	private static void getFromDest() {
		System.out.println("Please enter the destination address book name");
		try {
			while (true) {
				destName = scn.nextLine();
				if (destName != null) {
					File f = new File(destName + ".txt");
					if (f != null && f.exists()) {
						destName = AddressBookLogin.logIntoAccount()
								.getUserName();
						if (destName != null) {
							destContacts = ReadFromFile.readFromFile(destName,
									AddressBookConstants.ADD_MODIFIER);
							if (destContacts == null) {
								destContacts = new ArrayList<ContactDetails>();
							}
						}
						break;
					} else {
						System.out
								.println("Address book doesn't exist!! try again!!");
						System.out
								.println("Please re-enter the destination address book name");
					}
				} else {
					System.out.println("Invalid address book name");
					System.out
							.println("Please re-enter the destination address book name");
				}
			}
		} catch (Exception e) {
			System.out.println("Unable move contact to other address book!!");
		}
	}

	private static void moveFromSrcToDest() throws IOException {
		destContacts.add(moveContact);
		WriteToFile.writeToFile(destContacts, destName);
		System.out.println("Contact successfully moved!!");
	}
}
