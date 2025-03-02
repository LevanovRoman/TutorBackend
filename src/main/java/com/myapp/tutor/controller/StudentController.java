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



//    @GetMapping("/{id}")
//    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id){
//        return ResponseEntity.ok(studentService.getStudentById(id));
//    }

//    @PostMapping("/create")
//    public ResponseEntity<MessageDto> addStudent(@RequestBody StudentRequestDto student){
//        studentService.createStudent(student);
//        return ResponseEntity.ok(new MessageDto("Student created successfully"));
//    }




}
