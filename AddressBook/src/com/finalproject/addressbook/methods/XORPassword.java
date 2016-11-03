package com.finalproject.addressbook.methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XORPassword {
	private static String username;
	private static String password;

	public static void encryptFile(String user, String pswd, String flag)
			throws IOException {
		if (pswd != null && user != null) {
			password = pswd;
			username = user;
			File file;
			FileWriter fw;
			BufferedWriter bw = null;
			List<Character> reader = null;
			try {
				char[] writer;

				if (!flag.equalsIgnoreCase("create")) {
					reader = readBytesFromFile();
					file = new File(username + ".txt");
					fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					if (reader != null) {
						writer = new char[reader.size()];
						int ptr = 0;
						int pswdPtr = 0;
						while (ptr < reader.size()) {
							char c = (char) (reader.get(ptr) ^ password
									.charAt(pswdPtr++));
							writer[ptr++] = c;
							if (pswdPtr == password.length() - 1) {
								pswdPtr = 0;
							}
						}
						bw.write(writer);
					}
				} else {
					int i = 0;
					file = new File(username + ".txt");
					fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					writer = new char[password.length()];
					while (i < password.length()) {
						char c = (char) (' ' ^ password.charAt(i));
						writer[i++] = c;
					}
					bw.write(writer);
				}
			} catch (Exception e) {
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		}
	}

	public static void decryptFile(String user, String pswd) throws IOException {
		if (pswd != null && user != null) {
			password = pswd;
			username = user;
			File file;
			FileWriter fw;
			BufferedWriter bw = null;
			List<Character> reader = null;
			try {
				char[] writer;
				reader = readBytesFromFile();
				if (reader != null) {
					file = new File(username + ".txt");
					fw = new FileWriter(file);
					bw = new BufferedWriter(fw);
					writer = new char[reader.size()];
					int ptr = 0;
					int pswdPtr = 0;
					while (ptr < reader.size()) {
						char c = (char) (reader.get(ptr) ^ password
								.charAt(pswdPtr++));
						writer[ptr++] = c;
						if (pswdPtr == password.length() - 1) {
							pswdPtr = 0;
						}
					}
					bw.write(writer);
				}
			} catch (Exception e) {
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		}
	}

	private static List<Character> readBytesFromFile() throws IOException {
		List<Character> charList = new ArrayList<Character>();
		FileReader fr;
		BufferedReader br = null;
		File file;
		try {
			file = new File(username + ".txt");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String current;
			while ((current = br.readLine()) != null) {
				int i = 0, j = 0;
				while (i < current.length()) {
					char c = (char) (current.charAt(i++) ^ password.charAt(j++));
					charList.add(c);
					if (j == password.length() - 1) {
						j = 0;
					}
				}
			}
		} catch (Exception e) {

		} finally {
			br.close();
		}
		return charList;
	}
}