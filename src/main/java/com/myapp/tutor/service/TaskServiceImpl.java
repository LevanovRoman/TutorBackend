package com.myapp.tutor.service;

import com.myapp.tutor.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public List<Task> getTask() {
        return List.of();
    }

    @Override
    public Task updateTask(Task task, Long id) {
        return null;
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }
}
