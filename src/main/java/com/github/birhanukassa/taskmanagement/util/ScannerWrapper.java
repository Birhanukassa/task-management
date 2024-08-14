package com.github.birhanukassa.taskmanagement.util;

import java.util.Scanner;

public class ScannerWrapper {
    private Scanner scanner;

    public ScannerWrapper() {
        this.scanner = new Scanner(System.in);
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
