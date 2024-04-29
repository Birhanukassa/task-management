package com.github.birhanukassa.taskmanagement.commands;


interface TaskCommand<TaskList> {
    TaskList execute(TaskList task);
}

