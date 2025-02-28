package com.myapp.tutor.service;

import com.myapp.tutor.model.Task;
import java.util.List;

public interface TaskService {

    Task addTask(Task task);

    List<Task> getTask();

    Task updateTask(Task task, Long id);

    Task getTaskById(Long id);

    void deleteTask(Long id);
}
