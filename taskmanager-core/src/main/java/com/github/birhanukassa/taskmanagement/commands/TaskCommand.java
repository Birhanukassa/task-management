package com.commands;
import java.util.List;

public interface TaskCommand {
    public abstract List<Task> execute(List<Task> tasks);
}
