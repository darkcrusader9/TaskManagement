package com.example.taskmanagement;

import com.example.taskmanagement.controller.TaskManagementServiceController;
import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.filter.TaskFilter;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TaskManagementApplication {

    public static void main(String[] args) {
        TaskManagementServiceController taskManager = new TaskManagementServiceController();
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        // Create some tasks
        Task task1 = new Task("Task 1", "Description 1", new Date(), Priority.HIGH);
        taskManager.addTask(task1);
        Task task2 = new Task("Task 2", "Description 2", new Date(), Priority.MEDIUM);
        taskManager.addTask(task2);
        Task task3 = new Task("Task 3", "Description 3", new Date(), Priority.LOW);
        taskManager.addTask(task3);


        // Assign tasks to users
        taskManager.assignTask(task1.getTaskId(), user1);
        taskManager.assignTask(task2.getTaskId(), user2);
        taskManager.assignTask(task3.getTaskId(), user1);

        // Create a TaskFilter
        TaskFilter filter = new TaskFilter()
                .setPriority(Priority.HIGH)
                .setAssignedUser(user1);

        // Get filtered tasks
        List<Task> filteredTasks = taskManager.filterTasks(filter);

        // Print filtered tasks
        for (Task task : filteredTasks) {
            System.out.println("Filtered Task: " + task.getTitle());
        }
        //SpringApplication.run(TaskManagementApplication.class, args);
    }

}
