package com.finalproject.addressbook.sorting;

import java.util.Comparator;

import com.finalproject.addressbook.model.ContactDetails;

public class FistNameSort implements Comparator<ContactDetails> {

	@Override
	public int compare(ContactDetails o1, ContactDetails o2) {

		if (o1.getFirstName() == null) {
			o1.setFirstName("");
		}

		if (o2.getFirstName() == null) {
			o2.setFirstName("");
		}
		return o1.getFirstName().toLowerCase()
				.compareTo(o2.getFirstName().toLowerCase());
	}

}
