package com.finalproject.addressbook.sorting;

import java.util.Comparator;

import com.finalproject.addressbook.model.ContactDetails;

public class PhoneNumberSort implements Comparator<ContactDetails> {
	@Override
	public int compare(ContactDetails o1, ContactDetails o2) {
		Long num1 = null;
		Long num2 = null;

		if (o1.getPhoneNumber() == null) {
			num1 = Long.valueOf("0000000000");
		} else {
			num1 = Long.valueOf(o1.getPhoneNumber());
		}

		if (o2.getPhoneNumber() == null) {
			num2 = Long.valueOf("0000000000");
		} else {
			num2 = Long.valueOf(o2.getPhoneNumber());
		}
		return num1.compareTo(num2);
	}
}
