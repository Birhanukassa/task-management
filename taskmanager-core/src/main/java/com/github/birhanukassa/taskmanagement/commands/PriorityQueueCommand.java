package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import com.github.birhanukassa.taskmanagement.util.TaskSelector;

import java.util.List;

public class PriorityQueueCommand<V> implements TaskCommand<V> {
    private int importance;
    private int urgency;
    private double priorityLevel;

    @Override
    public TypedNameValue<V> execute(List<Task> tasks) {
        InputHandler handler = new InputHandler();

        TypedNameValue<String> inputValue = (TypedNameValue<String>) handler.getUserInput(
            "integer", "Rate how important the task is (1-10): ");
       
        TypedNameValue<Integer> urgencyInput = (TypedNameValue<Integer>) handler.getUserInput(
            "integer", "Rate how urgent the task is (1-10): ");
        

        if ("E".equalsIgnoreCase((String) inputValue.getValue()) || "E".equalsIgnoreCase((String) inputValue.getValue()) ) {
            System.out.println("Exiting task manager.");
            return (TypedNameValue<V>) new TypedNameValue<String>("Exit", "input", "E");

        }

        TaskSelector taskSelectorInstance = new TaskSelector();
        TypedNameValue<?> maybeSelectedTask = taskSelectorInstance.selectTask(tasks);

        if (!(maybeSelectedTask.getValue() instanceof Task)) {
            System.out.println("Invalid selection of a task form tasks.");
            return null;
        }

        Task selectedTask = (Task) maybeSelectedTask.getValue();
        
       

        // inject those values to task and return task
        // selectedTask.
        
        try {
            // importance = inputValue.getValue();
            // urgency = urgencyInput.getValue();
            // priorityLevel = (importance * 2) + urgency;
            selectedTask.setImportance());
            selectedTask.setUrgency(urgencyInput.getValue());

            System.out.println("The calculated priority score is: " + priorityLevel);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values for importance and urgency.");
        }

        return new TypedNameValue<V>("Task", "selectedTask", (V) selectedTask);
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

