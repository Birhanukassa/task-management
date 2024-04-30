//   TODO 
//!  this class is planned to be dedicated for prioritizing tasks and then 
//!  push it to 4 boxes for ethinhour matrix 

// package com.github.birhanukassa.taskmanagement.commands;

// import com.github.birhanukassa.taskmanagement.display.*;
// import com.github.birhanukassa.taskmanagement.commands.*;
// import com.github.birhanukassa.taskmanagement.models.*;
// import com.github.birhanukassa.taskmanagement.util.*;


// import java.util.List;
// import java.util.Optional;
// import java.util.Scanner;



// public class PrioritizeTaskCommand implements TaskCommand<Task> {

   
//     @Override
//     public List<Task> execute(List<Task> tasks) {
     


//         optionalTask.ifPresent(task -> {
//             Scanner scanner = new Scanner(System.in);
//             String action = scanner.nextLine();
            
//             System.out.println("rate how important the task is (1-10): ");
//             String isImportant = scanner.nextLine();
//             System.out.println("rate how urgent the task is (1-10): ");
//             String isUrgent = scanner.nextLine();

//             try {
//                 double importance = Double.parseDouble(isImportant);
//                 double urgency = Double.parseDouble(isUrgent);
//                 double score = (importance * 2) + urgency;
//                 System.out.println("The calculated priority score is: " + score);

//             } catch (NumberFormatException e) {
//                 System.out.println(     
//                 "Invalid input. Please enter numeric values for importance and urgency.")
//                 ;
//             }

//             scanner.close();


//             // switch (action.toUpperCase()) {
//             //     case "DO":
//             //         // Logic for doing the task immediately
//             //         System.out.println("Performing task: " + task.getTaskName());
//             //         break;
//             //     case "SCHEDULE":
//             //         // Logic for scheduling the task
//             //         System.out.println("Scheduling task: " + task.getTaskName());
//             //         break;
//             //     case "DELEGATE":
//             //         // Logic for delegating the task
//             //         System.out.println("Delegating task: " + task.getTaskName());
//             //         break;
//             //     case "DELETE":
//             //         tasks.remove(task);
//             //         System.out.println("Task deleted.");
//             //         break;
//             //     default:
//             //         System.out.println("Invalid action. Please try again.");
//             //         execute(tasks);  // ? calling recursively  ?
//             //         break;
//             // }
        
//         });

//         if (!(optionalTask.isPresent())) {
//             System.out.println("Thank you. You are exiting prioritizing tasks.");
//         }

//         return tasks;
//     }
// }
