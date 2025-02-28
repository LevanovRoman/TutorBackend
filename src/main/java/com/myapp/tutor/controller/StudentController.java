package com.myapp.tutor.controller;

import com.myapp.tutor.dto.MessageDto;
import com.myapp.tutor.dto.request.StudentRequestDto;
import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentResponseDto>> getStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDto> addStudent(@RequestBody StudentRequestDto student){
        studentService.createStudent(student);
        return ResponseEntity.ok(new MessageDto("Student created successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MessageDto> updateStudent(@RequestBody StudentRequestDto student, @PathVariable Long id){
        studentService.updateStudent(student, id);
        return ResponseEntity.ok(new MessageDto("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new MessageDto("Student deleted successfully"));
    }
}
