import com.github.birhanukassa.taskmanagement.models.Task;
import com.github.birhanukassa.taskmanagement.models.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TaskManagerTest {

    private static final String TEST_CSV_FILE = "src/test/resources/test_tasks.csv";

    @Test
    public void testLoadTasksFromValidCsv() throws IOException {
        TaskManager taskManager = TaskManager.getInstance();
        List<Task> tasks = taskManager.loadTasksFromCsv(TEST_CSV_FILE);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());
    }

    @Test
    public void testLoadTasksFromInvalidCsv() {
        TaskManager taskManager = TaskManager.getInstance();
        Assertions.assertThrows(IOException.class, () -> taskManager.loadTasksFromCsv("invalid_file.csv"));
    }

    @Test
    public void testUpdateTaskAndSaveToFile() throws IOException {
        TaskManager taskManager = TaskManager.getInstance();
        List<Task> tasks = taskManager.loadTasksFromCsv(TEST_CSV_FILE);
        Assertions.assertNotNull(tasks);
        Assertions.assertFalse(tasks.isEmpty());

        // Modify a task
        Task modifiedTask = tasks.get(0);
        modifiedTask.setTaskName("Modified Task Name");

        taskManager.updateTaskAndSaveToFile();

        List<String> updatedTasksFromFile = Files.readAllLines(Paths.get(TEST_CSV_FILE));
        Assertions.assertTrue(updatedTasksFromFile.stream()
                .anyMatch(line -> line.contains("Modified Task Name")));
    }

    @Test
    public void testGetFieldValuesAsCsv() {
        Task task = new Task("Task Name", "Task Description");
        task.setPriorityScore(3.5);
        task.setImportance(5);
        task.setUrgency(7);

        String csvString = TaskManager.getFieldValuesAsCsv(task);
        Assertions.assertNotNull(csvString);
        Assertions.assertTrue(csvString.contains("name: Task Name"));
        Assertions.assertTrue(csvString.contains("description: Task Description"));
        Assertions.assertTrue(csvString.contains("priorityLevel: 3.5"));
        Assertions.assertTrue(csvString.contains("importance: 5"));
        Assertions.assertTrue(csvString.contains("urgency: 7"));
    }
}
