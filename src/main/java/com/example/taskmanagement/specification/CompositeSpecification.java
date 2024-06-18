package com.example.taskmanagement.specification;

import com.example.taskmanagement.model.Task;

import java.util.ArrayList;
import java.util.List;

public class CompositeSpecification implements TaskSpecification {
    List<TaskSpecification> taskSpecifications;

    public CompositeSpecification() {
        this.taskSpecifications = new ArrayList<>();
    }

    public void addSpecification(TaskSpecification specification) {
        taskSpecifications.add(specification);
    }

    @Override
    public boolean isSatisfiedBy(Task task) {
        for (TaskSpecification specification : taskSpecifications) {
            if (!specification.isSatisfiedBy(task)) {
                return false;
            }
        }
        return true;
    }
}
