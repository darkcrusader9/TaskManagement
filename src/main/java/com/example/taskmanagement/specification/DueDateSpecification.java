package com.example.taskmanagement.specification;

import com.example.taskmanagement.model.Task;
import java.util.Date;

public class DueDateSpecification implements TaskSpecification {
    private final Date dueDate;

    public DueDateSpecification(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean isSatisfiedBy(Task task) {
        return task.getDueDate().equals(dueDate);
    }
}
