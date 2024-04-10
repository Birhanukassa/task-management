package com.github.birhanukassa.taskmanagement.util;
import com.github.birhanukassa.taskmanagement.commands.*;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;


public class SelectTask<T> {

        public Optional<T> taskSelectorOptional(List<T> task) {
            Optional<T> selectedTask = Optional.empty();
            String inputKey;
    
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the key of the task you want to manage, or 'E' to exit: ");
            inputKey = scanner.nextLine();
    
            do {
                if ("E".equalsIgnoreCase(inputKey)) {
                    System.out.println("Exiting task Manager.");
                    break;
                }
    
                int selectedTaskIndex;
    
                try {
                    selectedTaskIndex = Integer.parseInt(inputKey, 10);
            
                } catch (NumberFormatException e) {
                    System.out.println("Invalid key. Please enter a number.");
                    continue;
                }
    
                if (selectedTaskIndex < 0 || selectedTaskIndex >= tasks.size()) {
                    System.out.println("Invalid key. Please try again.");
                    continue;
                }
    
                selectedTask = Optional.of(tasks.get(selectedTaskIndex));
                System.out.println("Selected Task: " + selectedTask.get().getTaskName());
    
            } while (true);
    
            scanner.close();
          return selectedTask;
        }
}
       