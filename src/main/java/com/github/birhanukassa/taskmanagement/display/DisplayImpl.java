// Package declaration
package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.taskmanagement.models.Task;

// Importing necessary classes 
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * DisplayImpl class implements the TaskManagerInterface for Task object.
 * It provides methods to display and sort tasks.
 */
public class DisplayImpl implements TaskManagerInterface<Task> {
    // Logger instance for logging information and potential issues 
    private static final Logger logger = Logger.getLogger(DisplayImpl.class.getName());

    /**
     * Sorts the tasks by priority score (Highest to Lowest) and displays them.
     * If the list is empty, it informs the user that no tasks are available. 
     * 
     * @param sharedTaskList List of tasks to be displayed and sorted.
     * @see Task
     * @see List
     * @see Comparator
     * @see Comparator#comparingDouble(java.util.function.ToDoubleFunction
     * 
     */
    @Override
    public void sortThenDisplayTasks(List<Task> sharedTaskList) {
        if (!sharedTaskList.isEmpty()) {
            // Sort tasks in defending order of priority score 
            sharedTaskList.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());

            sharedTaskList.forEach(System.out::println);
        } else {
            logger.info("\nNo tasks to display. Please create a task first.\n");
        }
    }

    /**
     * Display the priority matrix, which is a simple list of all tasks. 
     * @param sharedTaskList the list of tasks to be displayed. 
     * @see Task
     * @see List
     * @see Comparator #comparingDouble(java.util.function.ToDoubleFunction)
     * @see Comparator#reversed()
     * @see #sortThenDisplayTasks(List<Task>)
     */
    @Override
    public void displayPriorityMatrix(List<Task> sharedTaskList) {
        logger.info("\nDisplaying priority matrix:\n============================\n");
        sharedTaskList.forEach(System.out::println);
    }
}
