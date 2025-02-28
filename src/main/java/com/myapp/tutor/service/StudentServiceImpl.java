package com.myapp.tutor.service;

import com.myapp.tutor.dto.request.StudentRequestDto;
import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.exception.StudentAlreadyExistsException;
import com.myapp.tutor.exception.StudentNotFoundException;
import com.myapp.tutor.model.Student;
import com.myapp.tutor.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll().stream().map(this::convertStudentToStudentResponseDto).toList();
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = findStudentById(id);
        return convertStudentToStudentResponseDto(student);
    }

    @Override
    public void createStudent(StudentRequestDto studentRequestDto) {
        if (studentAlreadyExists(studentRequestDto.email())){
            throw  new StudentAlreadyExistsException(studentRequestDto.email() + " already exists!");
        }
        Student studentCreated = new Student();
        saveStudent(studentRequestDto, studentCreated);
    }

    @Override
    public void updateStudent(StudentRequestDto studentRequestDto, Long studentId) {
        Student studentUpdated = findStudentById(studentId);
        saveStudent(studentRequestDto, studentUpdated);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(findStudentById(id));
    }

    private StudentResponseDto convertStudentToStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getCity());
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with the Id :" + id));
    }

    private void saveStudent(StudentRequestDto studentRequestDto, Student student) {
        student.setFirstName(studentRequestDto.firstName());
        student.setLastName(studentRequestDto.lastName());
        student.setEmail(studentRequestDto.email());
        student.setCity(studentRequestDto.city());
        student.setPassword(bCryptPasswordEncoder.encode(studentRequestDto.password()));
        student.setRole(studentRequestDto.role());
        studentRepository.save(student);
    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
