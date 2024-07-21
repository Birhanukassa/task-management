package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;

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


