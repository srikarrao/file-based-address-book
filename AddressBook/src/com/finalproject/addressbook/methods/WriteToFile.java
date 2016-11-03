package com.finalproject.addressbook.methods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.finalproject.addressbook.model.ContactDetails;

public class WriteToFile {
	public static void writeToFile(List<ContactDetails> addressBook,
			String fileName) throws IOException {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		try {
			if (addressBook != null) {
				fout = new FileOutputStream(fileName + ".txt");
				if (fout != null) {
					oos = new ObjectOutputStream(fout);
					oos.writeObject(addressBook);
				}
			} else {
				System.out
						.println("Add details to enter the contact into address book");
			}
		} catch (Exception ex) {
			System.out.println("File not found!!");
		} finally {
			fout.close();
			oos.close();
		}
	}
}
