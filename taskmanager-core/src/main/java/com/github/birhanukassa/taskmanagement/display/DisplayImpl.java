package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.Tmanagement.models.ItemList;
import com.github.birhanukassa.taskmanagement.models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class DisplayImpl implements TaskManagerInterface<Task> {
 
    @Override
    public void sortThenDisplayTasks(ItemList<ArrayList<Task>>  taskList) {
        List<Task> tasks = taskList.getTasks();
        taskList.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());
       
        System.out.println("Displaying sorted tasks:");
      
        for (int i = 0; i < task.size(); i++) {
            System.out.println("Key: " + i + ", Task: " + task.get(i).getTaskName() +
                               ", Priority Score: " + task.get(i).getPriorityScore());
        }
    }

    @Override
    public void displayPriorityMatrix(ItemList<Task> task) {
        System.out.println("Displaying priority matrix:");
        for (Task task : task) {
            System.out.println("Task: " + task.getTaskName() + ", Priority Score: " + task.getPriorityScore());
        }
    }

    // @Override
    // public Optional<Task> selectTask(List<Task> tasks) {
    //     Optional<Task> selectedTask = Optional.empty();
    //     String inputKey;

    //     displaySortedTasks(tasks);

    //     Scanner scanner = new Scanner(System.in);
    //     System.out.print("Enter the key of the task you want to manage, or 'E' to exit: ");
    //     inputKey = scanner.nextLine();

    //     do {
    //         if ("E".equalsIgnoreCase(inputKey)) {
    //             System.out.println("Exiting task Manager.");
    //             break;
    //         }

    //         int selectedTaskIndex;

    //         try {
    //             selectedTaskIndex = Integer.parseInt(inputKey, 10);
        
    //         } catch (NumberFormatException e) {
    //             System.out.println("Invalid key. Please enter a number.");
    //             continue;
    //         }

    //         if (selectedTaskIndex < 0 || selectedTaskIndex >= tasks.size()) {
    //             System.out.println("Invalid key. Please try again.");
    //             continue;
    //         }

    //         selectedTask = Optional.of(tasks.get(selectedTaskIndex));
    //         System.out.println("Selected Task: " + selectedTask.get().getTaskName());

    //     } while (true);

    //     scanner.close();
    //     return selectedTask;
    //

  
}