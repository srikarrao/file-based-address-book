package com.finalproject.addressbook.methods;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;
import com.finalproject.addressbook.sorting.DOBSort;
import com.finalproject.addressbook.sorting.EmailSort;
import com.finalproject.addressbook.sorting.FistNameSort;
import com.finalproject.addressbook.sorting.LastNameSort;
import com.finalproject.addressbook.sorting.PhoneNumberSort;

public class AddressBookSorting {
	private static Scanner scn = new Scanner(System.in);
	private static String fileName = null;

	public static void sortAddressBook(String fName) throws IOException {

		if (fName != null && !fName.equalsIgnoreCase("")) {
			fileName = fName;
			displayOptions();
		} else {
			System.out.println("Invalid file name!!");
		}
	}

	private static void displayOptions() throws IOException {
		System.out.println("Choose any of the option to sort!!");
		System.out.println("Press '1' to sort by first name");
		System.out.println("Press '2' to sort by last name");
		System.out.println("Press '3' to sort by email address");
		System.out.println("Press '4' to sort by phone number");
		System.out.println("Press '5' to sort by date of birth");
		doSorting();
	}

	private static void doSorting() throws IOException {

		String option = scn.nextLine();
		switch (option) {
		case "1":
			sortByFirstName();
			break;
		case "2":
			sortByLastName();
			break;
		case "3":
			sortByEmail();
			break;
		case "4":
			sortByPhoneNumbers();
			break;
		case "5":
			sortByDOB();
			break;
		default:
			System.out.println("Invalid option, try again!!");
			doSorting();
			break;
		}
	}

	private static void sortByFirstName() throws IOException {
		List<ContactDetails> contactDetails = ReadFromFile.readFromFile(
				fileName, AddressBookConstants.SORT_MODIFIER);
		if (contactDetails != null && contactDetails.size() > 0) {
			Collections.sort(contactDetails, (new FistNameSort()));
			AddressBookContactsDisplay.displaySortedAddressBook(contactDetails);
		} else {
			System.out.println("No contacts in the address book!!");
		}
	}

	private static void sortByLastName() throws IOException {

		List<ContactDetails> contactDetails = ReadFromFile.readFromFile(
				fileName, "sort");
		if (contactDetails != null && contactDetails.size() > 0) {
			Collections.sort(contactDetails, (new LastNameSort()));
			AddressBookContactsDisplay.displaySortedAddressBook(contactDetails);
		} else {
			System.out.println("No contacts in the address book!!");
		}
	}

	private static void sortByEmail() throws IOException {
		List<ContactDetails> contactDetails = ReadFromFile.readFromFile(
				fileName, "sort");
		if (contactDetails != null && contactDetails.size() > 0) {
			Collections.sort(contactDetails, new EmailSort());
			AddressBookContactsDisplay.displaySortedAddressBook(contactDetails);
		} else {
			System.out.println("No contacts in the address book!!");
		}
	}

	private static void sortByDOB() throws IOException {
		List<ContactDetails> contactDetails = ReadFromFile.readFromFile(
				fileName, "sort");
		Collections.sort(contactDetails, new DOBSort());
		if (contactDetails != null && contactDetails.size() > 0) {
			AddressBookContactsDisplay.displaySortedAddressBook(contactDetails);
		} else {
			System.out.println("No contacts in the address book!!");
		}
	}

	private static void sortByPhoneNumbers() throws IOException {
		List<ContactDetails> contactDetails = ReadFromFile.readFromFile(
				fileName, "sort");
		Collections.sort(contactDetails, new PhoneNumberSort());
		if (contactDetails != null && contactDetails.size() > 0) {
			AddressBookContactsDisplay.displaySortedAddressBook(contactDetails);
		} else {
			System.out.println("No contacts in the address book!!");
		}
	}

}
