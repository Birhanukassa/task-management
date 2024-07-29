package com.github.birhanukassa.taskmanagement.models;

import com.github.birhanukassa.taskmanagement.util.TimePeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class TaskTest {

    @Test
    void testTaskConstructor() {
        Task task = new Task("Task Name", "Task Description");
        Assertions.assertEquals("Task Name", task.getTaskName());
        Assertions.assertEquals("Task Description", task.getDescription());
    }

    @Test
    void testSetAndGetTaskName() {
        Task task = new Task("Task Name", "Task Description");
        task.setTaskName("New Task Name");
        Assertions.assertEquals("New Task Name", task.getTaskName());
    }

    @Test
    void testSetAndGetDescription() {
        Task task = new Task("Task Name", "Task Description");
        task.setDescription("New Task Description");
        Assertions.assertEquals("New Task Description", task.getDescription());
    }

    @Test
    void testSetAndGetPriorityScore() {
        Task task = new Task("Task Name", "Task Description");
        task.setPriorityScore(3.5);
        Assertions.assertEquals(3.5, task.getPriorityScore());
    }

    @Test
    void testSetAndGetImportance() {
        Task task = new Task("Task Name", "Task Description");
        task.setImportance(5);
        Assertions.assertEquals(5, task.getImportance());
    }

    @Test
    void testSetAndGetUrgency() {
        Task task = new Task("Task Name", "Task Description");
        task.setUrgency(7);
        Assertions.assertEquals(7, task.getUrgency());
    }

    @Test
    void testSetAndGetTimePeriod() {
        Task task = new Task("Task Name", "Task Description");
        TimePeriod timePeriod = new TimePeriod.Builder()
                .withStartDate(LocalDate.now())
                .withEndDate(LocalDate.now().plusDays(7))
                .withStartTime(LocalTime.now())
                .withEndTime(LocalTime.now().plusHours(2))
                .withInterval(3)
                .build();
        task.setTimePeriod(timePeriod);
        Assertions.assertEquals(timePeriod, task.getTimePeriod());
    }

    @Test
    void testToString() {
        Task task = new Task("Task Name", "Task Description");
        Assertions.assertNotNull(task.toString());
    }
}
