package com.display;

import com.commands.Task; 

import java.util.List;
import java.util.Optional;


public interface TaskManagerInterface {

    void displaySortedTasks(List<Task> tasks);

    void displayPriorityMatrix(List<Task> tasks);

    public Optional<Task> selectTask(List<Task> tasks);
}

