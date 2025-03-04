package com.myapp.tutor.TodoApp.service;

import com.myapp.tutor.TodoApp.dto.request.TaskRequestDto;
import com.myapp.tutor.TodoApp.dto.response.TaskResponseDto;
import com.myapp.tutor.TodoApp.entity.Task;
import com.myapp.tutor.dto.MessageDto;

import java.util.List;

public interface TaskService {

    MessageDto addTask(TaskRequestDto taskRequestDto, long studentId);

    List<TaskResponseDto> getTaskList(long studentId);



    Task getTaskById(Long id);

    MessageDto deleteTask(Long id);

    MessageDto changeStatusTask(long taskId);
}
