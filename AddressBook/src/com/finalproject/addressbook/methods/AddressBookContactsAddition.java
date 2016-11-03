package com.finalproject.addressbook.methods;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.finalproject.addressbook.model.Address;
import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class AddressBookContactsAddition {

	private static String fileName = null;
	private static Scanner scn = new Scanner(System.in);

	public static void addContacts(String fName) {
		if (fName != null && !fName.equals("")) {
			fileName = fName;
			addDetails();
			System.out.println("Contacts successfully added to address book!");
		} else {
			System.out.println("Invalid file name");
		}
	}

	private static void addDetails() {
		try {
			boolean keepAdding = true;
			List<ContactDetails> addressBook = ReadFromFile.readFromFile(
					fileName, AddressBookConstants.ADD_MODIFIER);
			if (addressBook == null) {
				addressBook = new ArrayList<ContactDetails>();
			}
			while (keepAdding) {
				System.out.println("Enter the contact details");
				ContactDetails contact = new ContactDetails();

				System.out.println("Enter contact first name");
				String firstName = scn.nextLine();
				if (firstName != null) {
					contact.setFirstName(firstName);
				}

				System.out.println("Enter contact last name");
				String lastName = scn.nextLine();
				if (lastName != null) {
					contact.setLastName(lastName);
				}

				System.out
						.println("Enter date of birth of the contact [Format as MM/DD/YYYY :: Example --> 07/12/2004");
				boolean isInvalid = true;
				while (isInvalid) {
					String dob = scn.nextLine();
					if (dob != null) {
						try {
							Date date = AddressBookConstants.formatter
									.parse(dob);
							contact.setDateOfBirth(date);
							isInvalid = false;
						} catch (ParseException e) {
							System.out.println("Invalid Date");
							System.out
									.println("Please re-enter the date of birth of the contact [Format as MM/DD/YYYY :: Example --> 07/12/2004");
						}
					} else {
						System.out.println("Invalid Date");
						System.out
								.println("Please re-enter the date of birth of the contact [Format as MM/DD/YYYY :: Example --> 07/12/2004");
					}
				}

				System.out.println("Enter the contact email-address");
				String emailAddress = scn.nextLine();
				if (emailAddress != null) {
					contact.setEmailAddress(emailAddress);
				}

				System.out.println("Enter phone number");
				String phnNumber = scn.nextLine();
				if (phnNumber != null) {
					contact.setPhoneNumber(phnNumber);
				}

				System.out.println("Enter the contact's address");
				Address address = new Address();

				System.out.println("Enter the street name");
				String streetName = scn.nextLine();
				if (address != null) {
					address.setStreetName(streetName);
				}

				System.out.println("Enter the city name");
				String cityName = scn.nextLine();
				if (cityName != null) {
					address.setCityName(cityName);
				}

				System.out.println("Enter the house number");
				String hNo = scn.nextLine();
				if (hNo != null) {
					address.sethNo(hNo);
				}

				System.out.println("Enter the state name");
				String stateName = scn.nextLine();
				if (stateName != null) {
					address.setStateName(stateName);
				}

				System.out.println("Enter the area zip code");
				String zipCode = scn.nextLine();
				if (zipCode != null) {
					address.setZipCode(zipCode);
				}

				if (address != null) {
					contact.setAddress(address);
				}

				System.out.println("Would you like to add another contact?");
				System.out
						.println("Press 'Y' if you wish to add another contact else press any other key");
				String ip = scn.nextLine();
				if (ip == null || !ip.equalsIgnoreCase("Y")) {
					keepAdding = false;
				}
				if (addressBook.size() < 100) {
					addressBook.add(contact);
				} else {
					keepAdding = false;
					System.out
							.println("Address book is full!! Max size allowed is 100 contacts!!");
				}
			}
			if (addressBook != null) {
				WriteToFile.writeToFile(addressBook, fileName);
			}
		} catch (Exception e) {
			System.out.println("Failed to add contact to address book " + e);
		}
	}
}
