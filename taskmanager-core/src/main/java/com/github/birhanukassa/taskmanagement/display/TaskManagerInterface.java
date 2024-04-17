package com.github.birhanukassa.taskmanagement.display;

import com.github.birhanukassa.Tmanagement.models.ItemList;
import com.github.birhanukassa.taskmanagement.models.*;

public interface TaskManagerInterface<E> {

	void sortThenDisplayTasks(ItemList<Task> sharedTaskList);

    void displayPriorityMatrix(ItemList<Task> taskList);

}

