import org.junit.jupiter.api.Assertions;

//...

class TaskListSingletonTest {
    @Test
    void testAddAndGetTasks() {
        TaskListSingleton taskListSingleton = TaskListSingleton.getInstance();
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");

        taskListSingleton.addTask(task1);
        taskListSingleton.addTask(task2);

        Assertions.assertEquals(2, taskListSingleton.getTasks().size());
        Assertions.assertTrue(taskListSingleton.getTasks().contains(task1));
        Assertions.assertTrue(taskListSingleton.getTasks().contains(task2));
    }

    // Add more unit tests for other classes and methods
}