package com.github.birhanukassa.taskmanagement.display;

import java.util.List;

public interface TaskManagerInterface<E> {

	void sortThenDisplayTasks(List<E> sharedTaskList);

    void displayPriorityMatrix(List<E> sharedTaskList);

}

