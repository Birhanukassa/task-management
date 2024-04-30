package com.github.birhanukassa.taskmanagement.commands;
import com.github.birhanukassa.taskmanagement.display.DisplayImpl;
import com.github.birhanukassa.taskmanagement.models.Task;

import java.util.List;
import java.util.Optional;


public class PriorityQueueCommand implements TaskCommand<Task> {
    private int importance = 0;
    private int urgent = 0;
    private double priorityScore = 0;

    public PriorityQueueCommand() {
        // 
    }

    public List<Task> prioritizeTasks(List<Task> tasks) {
        DisplayImpl taskManager = new DisplayImpl();
        
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
    



   
    @Override
    public List<Task> execute(List<Task> tasks) {
     


        optionalTask.ifPresent(task -> {
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            
            System.out.println("rate how important the task is (1-10): ");
            String isImportant = scanner.nextLine();
            System.out.println("rate how urgent the task is (1-10): ");
            String isUrgent = scanner.nextLine();

            try {
                double importance = Double.parseDouble(isImportant);
                double urgency = Double.parseDouble(isUrgent);
                double score = (importance * 2) + urgency;
                System.out.println("The calculated priority score is: " + score);

            } catch (NumberFormatException e) {
                System.out.println(     
                "Invalid input. Please enter numeric values for importance and urgency.")
                ;
            }

            scanner.close();


            // switch (action.toUpperCase()) {
            //     case "DO":
            //         // Logic for doing the task immediately
            //         System.out.println("Performing task: " + task.getTaskName());
            //         break;
            //     case "SCHEDULE":
            //         // Logic for scheduling the task
            //         System.out.println("Scheduling task: " + task.getTaskName());
            //         break;
            //     case "DELEGATE":
            //         // Logic for delegating the task
            //         System.out.println("Delegating task: " + task.getTaskName());
            //         break;
            //     case "DELETE":
            //         tasks.remove(task);
            //         System.out.println("Task deleted.");
            //         break;
            //     default:
            //         System.out.println("Invalid action. Please try again.");
            //         execute(tasks);  // ? calling recursively  ?
            //         break;
            // }
        
        });

        if (!(optionalTask.isPresent())) {
            System.out.println("Thank you. You are exiting prioritizing tasks.");
        }

        return tasks;
    }

    @Override
    public Task execute(Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}

// This method is not final because of Optional type handling issues 