package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

import java.util.List;

interface TaskCommand<V> {
    TypedNameValue<V> execute(List<Task> tasks);
}
