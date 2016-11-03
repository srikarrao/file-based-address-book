package com.finalproject.addressbook.sorting;

import java.util.Comparator;

import com.finalproject.addressbook.model.ContactDetails;

public class EmailSort implements Comparator<ContactDetails> {

	@Override
	public int compare(ContactDetails o1, ContactDetails o2) {

		if (o1.getEmailAddress() == null) {
			o1.setEmailAddress("");
		}

		if (o2.getEmailAddress() == null) {
			o2.setEmailAddress("");
		}
		return o1.getEmailAddress().toLowerCase()
				.compareTo(o2.getEmailAddress().toLowerCase());
	}
}
