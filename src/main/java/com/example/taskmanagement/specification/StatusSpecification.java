package com.example.taskmanagement.specification;

import com.example.taskmanagement.enums.Status;
import com.example.taskmanagement.model.Task;

public class StatusSpecification implements TaskSpecification{
    private final Status status;

    public StatusSpecification(Status status) {
        this.status = status;
    }

    @Override
    public boolean isSatisfiedBy(Task task) {
        return task.getStatus() == status;
    }
}
