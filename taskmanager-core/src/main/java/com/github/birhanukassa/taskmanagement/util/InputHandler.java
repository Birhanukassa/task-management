package com.github.birhanukassa.taskmanagement.util;

import java.util.Scanner;

public class InputHandler<T> {
    private Scanner scanner;

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public T getUserInput(String prompt, Class<T> targetType) {
        System.out.print(prompt + ": ");
        String userInput = scanner.nextLine();
        if (userInput == null ||  userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Input can't be empty.");
        }

        // Create an instance of the specified type (assuming it has a constructor that takes a String)
        try {
            return targetType.getConstructor(String.class).newInstance(userInput);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


// iTS A REFLECTION CLASS 
