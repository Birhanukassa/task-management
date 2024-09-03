// Package declaration
package com.github.birhanukassa.taskmanagement.util;

// Importing necessary classes 
import java.util.ArrayList;
import java.util.List;

// Class definition
/**
 * This class generates all possible combinations of a given list of strings,
 * considering a specific validity condition.
 */

/**
 * Example usage:
 * Input: ["starting-date", "starting-time", "ending-date", "ending-time", "interval"]
 * Output: [
 *   ["starting-date", "starting-time", "ending-date", "ending-time"],
 *   ["starting-date", "starting-time", "ending-date", "ending-time", "interval"],
 *   ["starting-date", "starting-time", "ending-date", "ending-time", "interval"],
 *   ["starting-date", "starting-time", "ending-date", "ending-time"]
 * ]
 */
public class TaskCombinationGenerator {
    // Main method
    /**
     * The main method that generates and prints all valid combinations of the input list.
     */
    public static void main(String[] args) {
        List<String> input = List.of("starting-date", "starting-time", "ending-date", "ending-time", "interval");

        List<List<String>> result = generateCombinations(input, new ArrayList<>(), 0, new ArrayList<>());

        for (List<String> combination : result) {
            System.out.println(new ArrayList<>(List.of(combination.toString())));
        }
    }

    /**
     * Recursive method to generate all combinations of the input list, considering the validity condition.
     * 
     * @param input
     * @param current
     * @param index
     * @param validCombinations
     * @return list of a valid combinations
     */
    private static List<List<String>> generateCombinations(List<String> input, List<String> current, int index,
            List<List<String>> validCombinations) {
        if (index == input.size()) {
            if (isValidCombination(current))
                validCombinations.add(new ArrayList<>(current));
            return validCombinations;
        }

        // Case 1: Exclude the current element
        generateCombinations(input, current, index + 1, validCombinations);

        // Case 2: Include the current element
        current.add(input.get(index));

        generateCombinations(input, current, index + 1, validCombinations);
        current.remove(current.size() - 1);
        return validCombinations;
    }

    /**
     * Helper method to check if a given combination is valid based on the specified conditions.
     *
     * @param combination
     * @return boolean 
     */
    private static boolean isValidCombination(List<String> combination) {
        boolean hasStartingDate = combination.contains("starting-date");
        boolean hasEndingTime = combination.contains("ending-time");
        boolean hasStartingTime = combination.contains("starting-time");

        return hasStartingDate && (!hasEndingTime || hasStartingTime);
    }
}
