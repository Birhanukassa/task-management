package com.github.birhanukassa.taskmanagement.commands;
import com.github.birhanukassa.taskmanagement.display.DisplayImpl;
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import com.github.birhanukassa.taskmanagement.util.SelectTask;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class PriorityQueueCommand implements TaskCommand {
    private int importance = 0;
    private int urgent = 0;
    private double priorityLevel = 0;

    @Override
    public Task execute(List<Task> tasks) {

        InputHandler<String> taskImportanceScore = new InputHandler<>();
        int importance = taskImportanceScore.getUserInput("rate how important the task is (1-10): ", int.class);


        DisplayImpl display = new DisplayImpl();
        display.sortThenDisplayTasks(tasks);

        SelectTask taskSelectorInstance = new SelectTask();
        Optional<Task> maybeSelectedTask = taskSelectorInstance.taskSelectorOptional(tasks);

        maybeSelectedTask.ifPresent(task -> {
            
            System.out.println("rate how important the task is (1-10): ");
            importance = scanner.nextInt();

            System.out.println("rate how urgent the task is (1-10): ");
            urgent = scanner.nextInt(importance);



            try {
                double importance = Double.parseDouble(importance);
                double urgency = Double.parseDouble(isUrgent);
                double score = (importance * 2) + urgency;
                System.out.println("The calculated priority score is: " + score);

            } catch (NumberFormatException e) {
                System.out.println(     
                "Invalid input. Please enter numeric values for importance and urgency.")
                ;
            }


            if (!(maybeSelectedTask.isPresent())) {
                System.out.println("Thank you. You are exiting prioritizing tasks.");
            }
        });

        return maybeSelectedTask;
    }
}
// This method is not final because of Optional type handling issues 