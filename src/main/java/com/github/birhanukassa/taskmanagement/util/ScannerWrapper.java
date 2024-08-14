package com.github.birhanukassa.taskmanagement.util;

import java.util.Scanner;
public class ScannerWrapper {
	private static final Scanner scanner = new Scanner(System.in);

	private ScannerWrapper() {
		// Private constructor to prevent instantiation from outside the class
	}

	public static String nextLine() {
		return scanner.nextLine();
	}

	public static void close() {
        scanner.close();
	}
}
