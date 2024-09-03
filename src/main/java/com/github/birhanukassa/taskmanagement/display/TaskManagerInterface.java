// Package declaration
package com.github.birhanukassa.taskmanagement.display;
// Importing necessary classes 
import java.util.List;

// Interface declaration for TaskManagerInterface
/**
 * TaskManagerInterface defines the contract for managing tasks.
 * It provides a method to sort and display tasks based on priority.
 */
public interface TaskManagerInterface<E> {
    // create its own method or in here or somewhere of sorting idea 
	void sortThenDisplayTasks(List<E> sharedTaskList);

    void displayPriorityMatrix(List<E> sharedTaskList);
}

