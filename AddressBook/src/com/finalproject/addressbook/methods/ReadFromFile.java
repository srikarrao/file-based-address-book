package com.finalproject.addressbook.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.finalproject.addressbook.model.AddressBookConstants;
import com.finalproject.addressbook.model.ContactDetails;

public class ReadFromFile {
	@SuppressWarnings("unchecked")
	public static List<ContactDetails> readFromFile(String fileName, String flag)
			throws IOException {
		FileInputStream fin = null;
		String fName = fileName + ".txt";
		ObjectInputStream ois = null;
		File file = null;
		List<ContactDetails> addrBook = null;
		try {
			if (fName != null && !fName.equals("")) {
				file = new File(fName);
				if (file != null && file.exists()) {
					fin = new FileInputStream(file);
					if (fin != null) {
						try {
							ois = new ObjectInputStream(fin);
							addrBook = (List<ContactDetails>) ois.readObject();
							return addrBook;
						} finally {
							fin.close();
							ois.close();
						}
					}
				}
			}
		} catch (Exception ex) {
			if (!flag.equals(AddressBookConstants.ADD_MODIFIER)) {
				System.out.println("Address Book is empty.");
				if (flag.equals(AddressBookConstants.DELETE_MODIFIER)) {
					System.out.println("Contacts cannot be removed!!");
				} else if (flag.equals(AddressBookConstants.MOVE_MODIFIER)) {
					System.out.println("Add contacts to be moved!!");
				}else if(flag.equals(AddressBookConstants.DISPLAY_MODIFIER)){
					System.out.println("Cannot display contacts!!");
				}
			}
		}
		return addrBook;
	}
}
