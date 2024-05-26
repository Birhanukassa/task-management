package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;

public class TaskSchedulerCommand implements TaskCommand<Task> {

    @Override
    public void execute(Task task) {


/* 
- Ask user if they would like to manage due date `Ti` or intervals `I` or `E`for exit`
    -  System.out.print("Do you want to set up time (T) or interval (I) or exit (E) ")
        - Save input

    -WHILE not E 
    

        - If E 
            return 

        - If input is T
            - call a method to allow user to add field of day/time/min
        - else if is I
            - call a method for T and add  
        
                -  interval functionality / recurring tasks: 
                allow users to set up a recurring interval
                    - check if the target task has setup one time date/hour/minute 
                        - if it does not have 
                                - Ask user the amount of day 
                                - Ask user what time 
                                - Ask user what minutes 
                        - add those info to be a selected task field and make it interval 
        - Ask user if they still want to add tasks, prioritizing tasks or managing Tasks
        - Save the input to `input`

        - // add final greetings  
        */

        /**
         * method for setting up one time 
         *      - Ask user what day
         *      - Ask user what time
         *      - Ask user what minutes
         *           - add those info to be a selected task field 
         *            
         * method for setting up recurring interval
         *      - Ask user the amount of day
         *      - Ask user what time
         *      - Ask user what minutes
         *           - add those info to be a selected task field  
         */


    }
}
