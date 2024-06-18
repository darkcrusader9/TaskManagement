package com.example.taskmanagement.specification;

import com.example.taskmanagement.enums.Priority;
import com.example.taskmanagement.model.Task;

public class PrioritySpecification implements TaskSpecification{
    private final Priority priority;

    public PrioritySpecification(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean isSatisfiedBy(Task task) {
        return task.getPriority() == priority;
    }
}
