package com.github.birhanukassa.taskmanagement.util;

import java.util.ArrayList;
import java.util.List;

public class TaskCombinationGenerator {

    public static void main(String[] args) {
        List<String> input = List.of("starting-date", "starting-time", "ending-date", "ending-time", "interval");

        List<List<String>> result = generateCombinations(input, new ArrayList<>(), 0, new ArrayList<>());

        for (List<String> combination : result) {
            System.out.println(new ArrayList<>(List.of(combination.toString())));
        }
    }

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

    private static boolean isValidCombination(List<String> combination) {
        boolean hasStartingDate = combination.contains("starting-date");
        boolean hasEndingTime = combination.contains("ending-time");
        boolean hasStartingTime = combination.contains("starting-time");

        return hasStartingDate && (!hasEndingTime || hasStartingTime);
    }
}
