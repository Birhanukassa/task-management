package com.github.birhanukassa.taskmanagement.display;
import com.github.birhanukassa.taskmanagement.commands.*;


import java.util.List;
import java.util.Optional;


public interface TaskManagerInterface {

    void displaySortedTasks(List<Task> tasks);

    void displayPriorityMatrix(List<Task> tasks);

    public Optional<Task> selectTask(List<Task> tasks);
}

