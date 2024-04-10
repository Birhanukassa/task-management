package com.github.birhanukassa.taskmanagement.commands;

import java.util.List;

interface TaskCommand<T> {
    List<T> execute(List<T> task);
}

