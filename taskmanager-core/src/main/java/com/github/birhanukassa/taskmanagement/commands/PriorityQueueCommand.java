package com.commands;

import java.util.List;
import java.util.Optional;

import com.display.DisplayImpl;
import com.display.TaskManagerInterface;


public class PriorityQueueCommand {
    private int importance = 0;
    private int urgent = 0;
    private double priorityScore = 0;

    public PriorityQueueCommand() {
        // 
    }

    public List<Task> prioritizeTasks(List<Task> tasks) {
      
        TaskManagerInterface taskManager = new DisplayImpl();
        
         // Define maybeSelectedTask (assuming it's an Optional<Task>)
         Optional<Task> maybeSelectedTask = Optional.empty(); // Initialize it with an empty Optional
         // Call methods from DisplayImpl or TaskManagerInterface as needed
        taskManager.selectTask(tasks);
     

         

        if (maybeSelectedTask.isPresent()) {
            Task selectedTask = maybeSelectedTask.get(); // Get the task from the Optional
        
        // - Prompt the user to rate the current task's importance (1 to 5)
        // - Prompt the user to rate the current task's urgency (1 to 5)
        // - Calculate a priority score (importance * 2 + urgency)

        // - Update the task's priority attribute with the calculated score

        }
        return tasks;
    }
}
    
// This method is not final because of Optional type handling issues 