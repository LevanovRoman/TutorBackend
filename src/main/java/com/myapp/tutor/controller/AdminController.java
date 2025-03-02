package com.myapp.tutor.controller;

import com.myapp.tutor.dto.MessageDto;
import com.myapp.tutor.dto.request.StudentRequestDto;
import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentResponseDto>> getStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/get-student/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<MessageDto> updateStudent(@RequestBody StudentRequestDto student, @PathVariable Long studentId){
        studentService.updateStudent(student, studentId);
        return ResponseEntity.ok(new MessageDto("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageDto> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new MessageDto("Student deleted successfully"));
    }

}
