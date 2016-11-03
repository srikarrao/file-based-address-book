package com.finalproject.addressbook.methods;

import java.io.IOException;
import java.util.List;

import com.finalproject.addressbook.model.Address;
import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class AddressBookContactsDisplay {

	public static void displayAddressBook(String fName) throws IOException {
		System.out.println("Displaying all the contacts in the address book!!");
		if (fName != null && !fName.equals("")) {
			displayContacts(ReadFromFile.readFromFile(fName,
					AddressBookConstants.DISPLAY_MODIFIER));
		}
	}

	public static void displaySortedAddressBook(List<ContactDetails> addrBook) {
		displayContacts(addrBook);
	}

	private static void displayContacts(List<ContactDetails> addrBook) {

		if (addrBook != null && addrBook.size() > 0) {
			System.out
					.println("		Displaying the contacts in the address book!!		");
			System.out
					.println("		---------------------------------------------		");
			int i = 0;
			while (i < addrBook.size()) {
				ContactDetails contactInfo = addrBook.get(i);
				if (contactInfo != null) {
					Address addr = contactInfo.getAddress();

					System.out.print("Name-> ");
					if (contactInfo.getFirstName() != null
							&& contactInfo.getLastName() != null) {
						System.out.print(contactInfo.getFirstName() + " ");
						System.out.println(contactInfo.getLastName());
					} else {
						System.out.println();
					}

					System.out.print("D.O.B-> ");
					if (contactInfo.getDateOfBirth() != null) {
						System.out.println(AddressBookConstants.formatter
								.format(contactInfo.getDateOfBirth()));
					} else {
						System.out.println();
					}

					System.out.print("Contact-> ");
					if (contactInfo.getEmailAddress() != null) {
						System.out.println(contactInfo.getEmailAddress());
					} else {
						System.out.println();
					}
					System.out.print("Phone-Numbers-> ");
					if (contactInfo.getPhoneNumber() != null) {
						System.out.print(contactInfo.getPhoneNumber() + " ");
					} else {
						System.out.println();
					}
					System.out.print("Address Line1-> ");
					if (addr.getStreetName() != null) {
						System.out.print(addr.getStreetName() + ",");
					}
					if (addr.gethNo() != null) {
						System.out.println(addr.gethNo());
					} else {
						System.out.println();
					}

					System.out.print("Address Line2-> ");
					if (addr.getCityName() != null) {
						System.out.print(addr.getCityName() + ",");
					}
					if (addr.getStateName() != null) {
						System.out.print(addr.getStateName() + ",");
					}

					if (addr.getZipCode() != null) {
						System.out.println(addr.getZipCode());
					} else {
						System.out.println();
					}

					System.out
							.println("----------------------------------------------------------------------");
					i++;
				}
			}
		} else {
			System.out.println("Address Book is empty.");
			System.out.println("Add contacts to get the contacts.");
		}
	}
}