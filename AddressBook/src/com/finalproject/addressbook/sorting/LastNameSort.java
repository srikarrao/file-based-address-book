package com.finalproject.addressbook.sorting;

import java.util.Comparator;

import com.finalproject.addressbook.model.ContactDetails;

public class LastNameSort implements Comparator<ContactDetails> {

	@Override
	public int compare(ContactDetails o1, ContactDetails o2) {

		if (o1.getLastName() == null) {
			o1.setLastName("");
		}

		if (o2.getLastName() == null) {
			o2.setLastName("");
		}
		return o1.getLastName().toLowerCase()
				.compareTo(o2.getLastName().toLowerCase());
	}
}
