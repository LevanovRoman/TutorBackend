package com.myapp.tutor.service;

import com.myapp.tutor.dto.request.StudentRequestDto;
import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long studentId);

    StudentResponseDto getMyInfo(String email);

//    void createStudent(StudentRequestDto studentRequestDto);

    void updateStudent(StudentRequestDto studentRequestDto, Long studentId);

    void deleteStudent(Long studentId);

    Student findStudentById(Long studentId);
}
