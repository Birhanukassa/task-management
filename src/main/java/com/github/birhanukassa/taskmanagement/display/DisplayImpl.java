package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.taskmanagement.models.Task;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
public class DisplayImpl implements TaskManagerInterface<Task> {
    private static final Logger logger = Logger.getLogger(DisplayImpl.class.getName());

    @Override
    public void sortThenDisplayTasks(List<Task> sharedTaskList) {
        if (!sharedTaskList.isEmpty()) {
            sharedTaskList.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());
            sharedTaskList.forEach(System.out::println);
        } else {
            logger.info("\nNo tasks to display. Please create a task first.\n");
        }
    }

    @Override
    public void displayPriorityMatrix(List<Task> sharedTaskList) {
        logger.info("\nDisplaying priority matrix:\n============================\n");
        sharedTaskList.forEach(System.out::println);
    }
}
