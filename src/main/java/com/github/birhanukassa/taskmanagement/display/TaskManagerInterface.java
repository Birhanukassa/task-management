package com.github.birhanukassa.taskmanagement.display;
import java.util.List;


public interface TaskManagerInterface<E> {
    // create its own method or in here or somewhere of sorting idea 
	void sortThenDisplayTasks(List<E> sharedTaskList);

    void displayPriorityMatrix(List<E> sharedTaskList);
}

