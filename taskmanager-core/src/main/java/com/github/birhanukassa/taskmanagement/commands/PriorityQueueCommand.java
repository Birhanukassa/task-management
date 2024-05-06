package com.github.birhanukassa.taskmanagement.commands;

import com.github.birhanukassa.taskmanagement.models.*;
import com.github.birhanukassa.taskmanagement.util.InputHandler;
import com.github.birhanukassa.taskmanagement.util.TaskSelector;

import java.util.List;

public class PriorityQueueCommand implements TaskCommand {
    private int importance;
    private int urgency;
    private double priorityLevel;

    @Override
    public TypedNameValue<String, Object> execute(List<Task> tasks) {
        InputHandler handler = new InputHandler();
        TypedNameValue<String, Object> input = handler.getUserInput("rate how important the task is (1-10): ");

        // DisplayImpl display = new DisplayImpl();
        // display.sortThenDisplayTasks(tasks);

        TaskSelector taskSelectorInstance = new TaskSelector();
        TypedNameValue<String, Object> maybeSelectedTask = taskSelectorInstance.selectTask(tasks);

        if ("E".equalsIgnoreCase((String) maybeSelectedTask.getValue())) {
            System.out.println("Exiting task Manager.");
            return new TypedNameValue<String, Object>("Exit", "input", "E");
        }

        if (!(maybeSelectedTask.getValue() instanceof Task)) {
            return null;
        }

        Task selectedTask = (Task) maybeSelectedTask.getValue();
        TypedNameValue<String, Object> urgencyInput = handler.getUserInput("rate how urgent the task is (1-10): ");

        try {
            importance = Integer.parseInt((String) input.getValue());
            urgency = Integer.parseInt((String) urgencyInput.getValue());
            priorityLevel = (importance * 2) + urgency;
            System.out.println("The calculated priority score is: " + priorityLevel);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values for importance and urgency.");
        }

        return new TypedNameValue<String, Object>("Task", "selectedTask", selectedTask);
    }
}

/*
 * 
 * ==========================================
 * input: collection
 * output: task object
 * 
 * ex: input ' ', output:
 * - should keep asking
 * - --- E
 * - return E
 * - --- Task
 * return Task
 * 
 * 2 input
 * 1) Tasks,
 * 2) Chosen index num
 * 
 * propt to choice the task via index or E
 * if choice not index or E
 * 
 * 
 * While loop
 * try to extract the approprate Task object using the key
 * if out bound ask for corrct choice
 * if choice is valid index
 * extract the Task
 * save the Task inside the NameValue object
 * if choice is e/E
 * save the character inside the NameValue object
 * END loop
 * 
 * return NameValue
 * 
 * ==========================================
 * 
 */

/*
 * public class PriorityQueueCommand implements TaskCommand {
 * private int importance = 0;
 * private int urgent = 0;
 * private double priorityLevel = 0;
 * 
 * 
 * @Override
 * public Task execute(List<Task> tasks) {
 * 
 * InputHandler<String> input = new InputHandler<>();
 * int importance =
 * input.getUserInput("rate how important the task is (1-10): ", int.class);
 * 
 * DisplayImpl display = new DisplayImpl();
 * display.sortThenDisplayTasks(tasks);
 * 
 * SelectTask taskSelectorInstance = new SelectTask();
 * NameValue maybeSelectedTask = taskSelectorInstance.selectTask(tasks);
 * 
 * if (maybeSelectedTask.getName().equals("E")) {
 * return "E";
 * 
 * } else if (maybeSelectedTask.getValue() !== instanceof Task) {
 * return null; //??
 * 
 * } else {
 * Task selectedTask = (Task) maybeSelectedTask.getValue();
 * double urgency;
 * double importance;
 * // ??
 * try {
 * input.getUserInput("rate how urgent the task is (1-10): ", int.class);
 * } catch (Exception e) {
 * // TODO: handle exception
 * urgency = Double.parseDouble(isUrgent);
 * }
 * try {
 * input.getUserInput("rate how important the task is (1-10): ", int.class);
 * } catch (Exception e) {
 * // TODO: handle exception
 * importance = Integer.parseInt(isImportance);
 * }
 * 
 * try {
 * 
 * double score = (importance * 2) + urgency;
 * System.out.println("The calculated priority score is: " + score);
 * 
 * } catch (NumberFormatException e) {
 * System.out.println(
 * "Invalid input. Please enter numeric values for importance and urgency.");
 * }
 * 
 * //if (!(maybeSelectedTask.isPresent())) {
 * // System.out.println("Thank you. You are exiting prioritizing tasks.");
 * }
 * }
 * 
 * return maybeSelectedTask;
 * }
 * }
 * // This method is not final because of Optional type handling issues
 */