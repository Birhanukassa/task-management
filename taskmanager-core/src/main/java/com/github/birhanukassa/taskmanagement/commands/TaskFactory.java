package com.github.birhanukassa.taskmanagement.commands;

import java.util.Scanner;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.util.InputHandler;



public class TaskFactory extends Task{
  

    private String taskName;
    private String taskDescription;
    private Task newTask;
    
    public TaskFactory() {
        super();
    }

     public void createTask() {
       
        System.out.println("You chose creating a Task.");
        System.out.println("==============================\n");
        InputHandler<String> taskN = new InputHandler<>();
        this.taskName = taskN.getUserInput("Enter the name of the task: ", String.class);

         InputHandler<String>  taskDesc = new InputHandler<>();
         this.taskDescription = taskDesc.getUserInput("Enter the description of the task: ", String.class);

         // Create a new Task object with the provided name and description
         this.newTask = new Task(taskName, taskDescription);
     }

    public Task getNewTask() {
        return this.newTask;
    }
}

// make sure the errors are fixed 
// make sure local variables are used hence probably
// make sure to fix constructor Task bypassing problem 
// ? 