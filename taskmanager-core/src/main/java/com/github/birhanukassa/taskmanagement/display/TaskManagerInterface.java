package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.taskmanagement.models.Task;
import java.util.List;


public interface TaskManagerInterface<E extends Task> {
    // create its owen method or in here or somewhere of sorting idea 
	void sortThenDisplayTasks(List<E> sharedTaskList);

    void displayPriorityMatrix(List<E> sharedTaskList);

    

}

