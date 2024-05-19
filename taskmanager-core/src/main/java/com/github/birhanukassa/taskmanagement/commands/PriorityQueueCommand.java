package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

public class PriorityQueueCommand implements TaskCommand<Task> {
    private Integer importance;
    private Integer urgency;
    private double priorityLevel;

    @Override
    public void execute(Task task) {
        InputHandler inputHandler = new InputHandler();
        try {
            importance = getValidatedInput(inputHandler, "Rate how important the task is (1-10): ", 1, 10);
            urgency = getValidatedInput(inputHandler, "Rate how urgent the task is (1-10): ", 1, 10);
            priorityLevel = calculatePriorityLevel(importance, urgency);
            task.setPriorityScore(priorityLevel);
            System.out.println("The calculated priority score is: " + priorityLevel);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private int getValidatedInput(InputHandler inputHandler, String prompt, int min, int max) throws Exception {
        NamedTypedValue<Integer> userInput;
        int value;
        do {
            userInput = inputHandler.getUserInput(prompt, Integer.class);
            value = userInput.getValue();
            if (value < min || value > max) {
                System.out.println("Invalid input. Please enter a value between " + min + " and " + max + ".");
            }
        } while (value < min || value > max);
        return value;
    }

    private double calculatePriorityLevel(int importance, int urgency) {
        return (importance * 2) + urgency;
    }

    static {
        InputHandler.registerTypeConverter(Integer.class, Integer::valueOf);
    }
}




/* // The box is only pack one item such as importance or urgency ... 
 * 
 * ==========================================
 * input: collection
 * output: task object
   - do input handler to preform 
   - call the get user input and pass the current massage
      - if return value is E 
          - return Typed name value with value E 
      - else if 
         - extract task 
            - return Typed name value with Task object 
        

 * ==========================================
 * 
 */

