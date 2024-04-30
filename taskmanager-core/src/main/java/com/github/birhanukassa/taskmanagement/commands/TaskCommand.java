package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import java.util.List;

interface TaskCommand {
    Task execute(List<Task> tasks);
}

