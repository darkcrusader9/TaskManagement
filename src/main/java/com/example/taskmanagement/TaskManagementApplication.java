package com.example.taskmanagement;

import com.example.taskmanagement.service.TaskManagementService;
import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.filter.TaskFilter;
import com.example.taskmanagement.model.Task;
import com.example.taskmanagement.model.User;
import com.example.taskmanagement.specification.AssignedUserSpecification;
import com.example.taskmanagement.specification.CompositeSpecification;
import com.example.taskmanagement.specification.PrioritySpecification;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TaskManagementApplication {

    public static void main(String[] args) {
        TaskManagementService taskManager = new TaskManagementService();
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
//        TaskFilter filter = new TaskFilter()
//                .setPriority(Priority.HIGH)
//                .setAssignedUser(user1);

        //Filter HIGH Priority tasks
        CompositeSpecification filter = new CompositeSpecification();
        filter.addSpecification(new PrioritySpecification(Priority.HIGH));

        //Filter tasks by assigned user
        CompositeSpecification filter1 = new CompositeSpecification();
        filter1.addSpecification(new AssignedUserSpecification("Alice"));
        filter1.addSpecification(new PrioritySpecification(Priority.HIGH));

        // Get filtered tasks
        List<Task> filteredTasks = taskManager.filterTasks(filter1);

        // Print filtered tasks
        for (Task task : filteredTasks) {
            System.out.println("Filtered Task: " + task.getTitle());
        }

        //Notify user
        taskManager.notify(task1);

        List<Task> taskList = taskManager.getTasksHistory(user1);
        System.out.println("Task History for User 1 is");
        for (Task task : taskList) {
            System.out.println("Task is: " + task.getTitle());
        }

        //Delete a task and check history
        taskManager.deleteTask(task3.getTaskId());

        System.out.println("Task history after deleting...");

        List<Task> taskList1 = taskManager.getTasksHistory(user1);
        System.out.println("Task History for User 1 is");
        for (Task task : taskList1) {
            System.out.println("Task is: " + task.getTitle());
        }

        //SpringApplication.run(TaskManagementApplication.class, args);
    }

}
