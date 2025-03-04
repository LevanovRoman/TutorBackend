package com.myapp.tutor.TodoApp.controller;

import com.myapp.tutor.TodoApp.dto.request.TaskRequestDto;
import com.myapp.tutor.TodoApp.dto.response.TaskResponseDto;
import com.myapp.tutor.TodoApp.repository.TaskRepository;
import com.myapp.tutor.TodoApp.service.TaskService;
import com.myapp.tutor.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-user/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/list/{studentId}")
    public ResponseEntity<List<TaskResponseDto>> getTaskList(@PathVariable("studentId") long studentId){
        return ResponseEntity.ok(taskService.getTaskList(studentId));
    }

    @PostMapping("/add/{studentId}")
    public ResponseEntity<MessageDto> addTaskToStudent(@RequestBody TaskRequestDto taskRequestDto,
                                                       @PathVariable("studentId") long studentId) {
        System.out.println(taskRequestDto.title());
        System.out.println(studentId);
        return ResponseEntity.ok(taskService.addTask(taskRequestDto, studentId));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<MessageDto> deleteTask(@PathVariable("studentId") long studentId) {
        return ResponseEntity.ok(taskService.deleteTask(studentId));
    }

    @GetMapping("/change-status/{taskId}")
    public ResponseEntity<MessageDto> changeStatusTask(@PathVariable("taskId") long taskId){
        return ResponseEntity.ok(taskService.changeStatusTask(taskId));
    }
}
