package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.models.TypedNameValue;

import java.util.List;

interface TaskCommand {
    TypedNameValue<String, Object> execute(List<Task> tasks);
}
