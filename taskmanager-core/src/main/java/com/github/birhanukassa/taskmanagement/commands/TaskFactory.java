package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;



public class TaskFactory {
  
    private String taskName;
    private String taskDescription;
    private Task newTask;

     public Task createTask() throws Exception {
       
        System.out.println("You chose creating a Task.");
        System.out.println("==============================\n");
        InputHandler taskNameInput = new InputHandler();
        this.taskName = taskNameInput.getUserInput("Enter the name of the task: ", String.class).getValue();

         InputHandler taskDesc = new InputHandler();
         this.taskDescription = taskDesc.getUserInput("Enter the description of the task: ", String.class).getValue();

         // Create a new Task object with the provided name and description
         this.newTask = new Task(taskName, taskDescription);
         return newTask;
     }
}

