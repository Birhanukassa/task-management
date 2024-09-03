// Package declaration
package com.github.birhanukassa.taskmanagement.util;

// Importing necessary classes 
import java.util.Scanner;

// Class declaration
/**
 * This class provides a wrapper around the Scanner class to handle user input.
 * It encapsulates the Scanner instance and provides methods to read user input.
 */
public class ScannerWrapper {
	private static final Scanner scanner = new Scanner(System.in);

	private ScannerWrapper() {
		// Private constructor to prevent instantiation from outside the class
	}

	/**
	 * Reads the next line of input from the user.
	 *
	 * @return The next line of input as a String.
	 */
	public static String nextLine() {
		return scanner.nextLine();
	}

	/**
	 * Closes the underlying Scanner instance.
	 */
	public static void close() {
        scanner.close();
	}
}
