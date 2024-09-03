// Package declaration
package com.github.birhanukassa.taskmanagement.commands;

// Import statements
import com.github.birhanukassa.taskmanagement.models.Task;
// this interface is used to define a contract for executing a task command
interface TaskCommand<V> {
    void execute(Task task);
}
