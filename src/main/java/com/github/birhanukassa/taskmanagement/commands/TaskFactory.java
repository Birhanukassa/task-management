// Package declaration
package com.github.birhanukassa.taskmanagement.commands;

// These are the import statements for the required classes
import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

/**
 * The TaskFactory class is responsible for creating new Task objects.
 * It prompts the user to enter the name and description of the task,
 * creates a new Task object with the provided information, and returns it.
 */
public class TaskFactory {

   public static Task createTask() {
      String taskName;
      String taskDescription;
      Task newTask;
      
      System.out.println("You chose creating a Task.");
      System.out.println("\n==========================\n");

      taskName = InputHandler.getUserInput(
         "Enter the name of the task: ", String.class)
         .getValue();

      taskDescription = InputHandler.getUserInput(
         "Enter the description of the task: ", String.class)
         .getValue();

      newTask = new Task(taskName, taskDescription);
      return newTask;
   }
}


