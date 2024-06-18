package com.example.taskmanagement.specification;

import com.example.taskmanagement.model.Task;

public class AssignedUserSpecification implements TaskSpecification {
    String assignedUser;

    public AssignedUserSpecification(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public boolean isSatisfiedBy(Task task) {
        return task.getAssignedUser().getName().equals(assignedUser);
    }
}
