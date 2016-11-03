package com.finalproject.addressbook.sorting;

import java.util.Comparator;
import com.finalproject.addressbook.model.ContactDetails;

public class DOBSort implements Comparator<ContactDetails> {
	@Override
	public int compare(ContactDetails o1, ContactDetails o2) {

		if (o1.getDateOfBirth() == null) {
		}

		if (o2.getDateOfBirth() == null) {
		}
		return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
	}
}
