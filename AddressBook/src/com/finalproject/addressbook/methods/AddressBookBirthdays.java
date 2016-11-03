package com.finalproject.addressbook.methods;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;
import com.finalproject.addressbook.sorting.DOBSort;

public class AddressBookBirthdays {
	private static String fileName = null;
	private static Date todayDate;
	private static String T_DATE = null;
	private static int day;
	private static int month;
	private static int yest_day;
	private static int yest_month;
	private static int tmw_day;
	private static int tmw_month;

	private static Calendar cal;
	private static List<ContactDetails> birthdaysList = new ArrayList<ContactDetails>();
	static {
		todayDate = new Date();
		T_DATE = AddressBookConstants.formatter.format(todayDate);
		try {
			todayDate = AddressBookConstants.formatter.parse(T_DATE);
			cal = Calendar.getInstance();
			cal.setTime(todayDate);
			day = cal.get(Calendar.DAY_OF_MONTH);
			month = cal.get(Calendar.MONTH);

			cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, -1);
			yest_day = cal.get(Calendar.DAY_OF_MONTH);
			yest_month = cal.get(Calendar.MONTH);

			cal = Calendar.getInstance();
			cal.setTime(todayDate);
			cal.add(Calendar.DATE, 1);
			tmw_day = cal.get(Calendar.DAY_OF_MONTH);
			tmw_month = cal.get(Calendar.MONTH);
		} catch (ParseException e) {
		}
	}

	public static void getBirthdaysList(String fName) throws IOException {
		if (fName != null) {
			fileName = fName;
			fetchBirthdays();
			if (birthdaysList != null & birthdaysList.size() > 0) {
				displayBirthdays();
			}
		}
	}

	private static void displayBirthdays() {
		Collections.sort(birthdaysList, new DOBSort());
		Iterator<ContactDetails> iterator = birthdaysList.iterator();

		while (iterator.hasNext()) {
			ContactDetails bdayDetails = iterator.next();
			if (bdayDetails != null) {
				if (bdayDetails.getFirstName() != null) {
					System.out.print(bdayDetails.getFirstName() + " ");
				}

				if (bdayDetails.getLastName() != null) {
					System.out.print(bdayDetails.getLastName() + " ");
				}

				if (bdayDetails.getDateOfBirth() != null) {
					System.out.print(AddressBookConstants.formatter
							.format(bdayDetails.getDateOfBirth()) + " ");
					System.out.println();
				} else {
					System.out.println();
				}
			}
		}
	}

	private static void fetchBirthdays() throws IOException {
		List<ContactDetails> contactsList = null;
		if (day > 0 && month > 0) {
			contactsList = ReadFromFile.readFromFile(fileName, "");
			if (contactsList != null && contactsList.size() > 0) {
				int start = 0;
				int end = contactsList.size() - 1;

				ContactDetails contact = null;
				while (start <= end && contactsList.get(start) != null) {
					contact = contactsList.get(start++);
					if (contact != null && contact.getDateOfBirth() != null) {
						cal.setTime(contact.getDateOfBirth());
						if ((yest_day == cal.get(Calendar.DAY_OF_MONTH) && yest_month == cal
								.get(Calendar.MONTH))
								|| (day == cal.get(Calendar.DAY_OF_MONTH) && month == cal
										.get(Calendar.MONTH))
								|| (tmw_day == cal.get(Calendar.DAY_OF_MONTH) && tmw_month == cal
										.get(Calendar.MONTH))) {
							birthdaysList.add(contact);
						}
					}
				}
			}
		}
	}
}
