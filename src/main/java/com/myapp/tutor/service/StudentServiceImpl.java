package com.myapp.tutor.service;

import com.myapp.tutor.dto.request.StudentRequestDto;
import com.myapp.tutor.dto.response.StudentResponseDto;
import com.myapp.tutor.exception.StudentNotFoundException;
import com.myapp.tutor.entity.Role;
import com.myapp.tutor.entity.Student;
import com.myapp.tutor.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public StudentResponseDto getMyInfo(String email) {
        try{
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()) {
                return convertStudentToStudentResponseDto(studentOptional.get());
            } else throw new StudentNotFoundException("Student not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void createStudent(StudentRequestDto studentRequestDto) {
//        if (studentAlreadyExists(studentRequestDto.email())){
//            throw  new StudentAlreadyExistsException(studentRequestDto.email() + " already exists!");
//        }
//        Student studentCreated = new Student();
//        saveStudent(studentRequestDto, studentCreated);
//    }

    @Override
    public void updateStudent(StudentRequestDto studentRequestDto, Long studentId) {
        Student studentUpdated = findStudentById(studentId);
        studentUpdated.setFirstName(studentRequestDto.firstName());
        studentUpdated.setLastName(studentRequestDto.lastName());
        studentUpdated.setEmail(studentRequestDto.email());
        studentUpdated.setCity(studentRequestDto.city());
        studentUpdated.setRole(Role.valueOf(studentRequestDto.role()));
        studentRepository.save(studentUpdated);
    }
    //TODO смена пароля

    @Override
    public void deleteStudent(Long id) {
        studentRepository.delete(findStudentById(id));
    }

    private StudentResponseDto convertStudentToStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getId(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getCity(), student.getRole().name());
    }

    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with the Id :" + studentId));
    }

//    private void saveStudent(StudentRequestDto studentRequestDto, Student student) {
//        student.setFirstName(studentRequestDto.firstName());
//        student.setLastName(studentRequestDto.lastName());
//        student.setEmail(studentRequestDto.email());
//        student.setCity(studentRequestDto.city());
//        student.setPassword(bCryptPasswordEncoder.encode(studentRequestDto.password()));
//        student.setRole(Role.valueOf(studentRequestDto.role()));
//        studentRepository.save(student);
//    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
