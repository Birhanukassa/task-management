package com.github.birhanukassa.taskmanagement.commands;
import java.util.List;

import com.github.birhanukassa.taskmanagement.models.Task;



public class TaskSchedulerCommand implements TaskCommand<Task> {

        /* 
        - If input `M`
        - display the list of tasks to view
        - For each task in `Tasks` and NOT E
        -  For current task
        - Ask user if they would like to manage deadlines `MD` or intervals `I`or `E for exit`
            -  System.out.print("Do you want to set up time (T) or interval (I) or exit (E) ")
                 - Save input
            - // deadline management
                - If input is T
                    - call a method to allow user to readjust day/time if they want to.
                 - else ...
                        
                    - // recurring tasks: allow users to set up a recurring interval
                        - If input is I
                            - Ask user if it's daily, weekly monthly or specific days/hours
                            - set intervals for each recurring task

        - Ask user if they still want to add tasks, prioritizing tasks or managing Tasks
        - Save the input to `input`

        - // add final greetings  
        */
        

    @Override
    public List<Task> execute(List<Task> task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
