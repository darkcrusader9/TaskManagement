package com.example.taskmanagement.specification;

import com.example.taskmanagement.model.Task;

public interface TaskSpecification {
    boolean isSatisfiedBy(Task task);
}
