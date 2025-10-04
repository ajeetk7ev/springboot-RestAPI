package com.example.springbootRestAPI.service.impl;

import com.example.springbootRestAPI.dto.AddStudentRequestDto;
import com.example.springbootRestAPI.dto.StudentDto;
import com.example.springbootRestAPI.entity.Student;
import com.example.springbootRestAPI.repository.StudentRepository;
import com.example.springbootRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    public List<StudentDto> getAllStudents(){
         List<Student> students = studentRepository.findAll();
         List<StudentDto> studentDtoList = students.stream().
                  map(student->new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getBranch())).toList();
         return studentDtoList;
    }

    public StudentDto getStudentById(Long id){
        Student st = studentRepository.getReferenceById(id);
        StudentDto student = modelMapper.map(st, StudentDto.class);
        return student;
    }

    @Async
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto){
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);

        return studentDto;
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + id));

        // Update fields
        student.setName(addStudentRequestDto.getName());
        student.setEmail(addStudentRequestDto.getEmail());
        student.setBranch(addStudentRequestDto.getBranch());

        // Save updated student
        Student updatedStudent = studentRepository.save(student);

        // Convert entity -> DTO (assuming you have a mapper or constructor)
        return new StudentDto(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getBranch()
        );


    }
}
