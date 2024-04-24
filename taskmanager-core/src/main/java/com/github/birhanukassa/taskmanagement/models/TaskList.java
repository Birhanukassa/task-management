package com.github.birhanukassa.taskmanagement.models;

import java.util.ArrayList;
import java.util.List;

public enum TaskList {
    INSTANCE;
  
    private final List<Task> tasks = new ArrayList<>();
  
    public List<Task> getTasks() {
      return tasks;
    }
  
  }

  
// public class GenericSingleton {
//     private GenericSingleton() {}

//     private static class SingletonHolder {
//         private static final GenericSingleton INSTANCE = new GenericSingleton();
//     }

//     public static GenericSingleton getInstance() {
//         return SingletonHolder.INSTANCE;
//     }

//     // Your methods here
// }


/*import java.util.ArrayList;
import java.util.List;

public class GenericSingleton {
    private static class SingletonHolder {
        private static final GenericSingleton INSTANCE = new GenericSingleton();
    }

    // Singleton instance holding a list of tasks
    private final List<Task> tasks;

    private GenericSingleton() {
        // Initialize the list of tasks
        tasks = new ArrayList<>();
    }

    // Get the singleton instance
    public static GenericSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // Get the list of tasks
    public List<Task> getTasks() {
        return tasks;
    }
}
 */