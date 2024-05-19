package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;

interface TaskCommand<V> {
    void execute(Task task);
}
