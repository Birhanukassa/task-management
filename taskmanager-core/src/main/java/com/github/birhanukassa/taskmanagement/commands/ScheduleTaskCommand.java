package com.commands;

public class ScheduleTaskCommand implements TaskCommand {

    @Override
    public Task execute(List<Task> tasks) {

        /* 
        - If input `M`
        - display the list of tasks to view
        - For each task in `Tasks` and NOT E
        -  For current task
        - Ask user if they would like to manage deadlines `MD` or intervals `I`or `E for exit`
        - Save input
            - // deadline management
                - If input is MD
                    - call a method to allow user to readjust day/time if they want to.
                    - 
            - // recurring tasks: allow users to set up a recurring interval
                - If input is I
                    - Ask user if it's daily, weekly monthly or specific days/hours
                    - set intervals for each recurring task

        - Ask user if they want to add tasks, prioritizing tasks or managing Tasks
        - Save the input to `input`

        - // add final greetings  
        */
        


        return tasks;
        
    }
    
}
