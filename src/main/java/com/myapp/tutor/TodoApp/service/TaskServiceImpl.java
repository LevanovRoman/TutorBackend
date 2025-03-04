package com.myapp.tutor.TodoApp.service;

import com.myapp.tutor.TodoApp.dto.request.TaskRequestDto;
import com.myapp.tutor.TodoApp.dto.response.TaskResponseDto;
import com.myapp.tutor.TodoApp.entity.Task;
import com.myapp.tutor.TodoApp.repository.TaskRepository;
import com.myapp.tutor.dto.MessageDto;
import com.myapp.tutor.entity.Student;
import com.myapp.tutor.exception.ObjectNotFoundCustomException;
import com.myapp.tutor.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final StudentService studentService;
    private final TaskRepository taskRepository;

    @Override
    public MessageDto addTask(TaskRequestDto taskRequestDto, long studentId) {
        Student student = studentService.findStudentById(studentId);
        Task task = new Task();
        task.setTitle(taskRequestDto.title());
        task.setCompleted(false);
        task.setStudent(student);
        taskRepository.save(task);

        return new MessageDto("Task added successfully");
    }

    @Override
    public List<TaskResponseDto> getTaskList(long studentId) {
        Student student = studentService.findStudentById(studentId);
        return student.getTaskList().stream().map(this::convertTaskToTaskResponseDto).toList();
    }

    private TaskResponseDto convertTaskToTaskResponseDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.isCompleted());
    }

    @Override
    public Task getTaskById(Long id) {
        return null;
    }

    @Override
    public MessageDto deleteTask(Long id) {
        taskRepository.delete(findTaskById(id));
        return new MessageDto("Task deleted successfully");
    }

    @Override
    public MessageDto changeStatusTask(long taskId) {
        Task task = findTaskById(taskId);
        task.setCompleted(! task.isCompleted());
        taskRepository.save(task);
        return new MessageDto("successfully change");
    }

    private Task findTaskById(long taskId){
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ObjectNotFoundCustomException("Task not found"));
    }
}
