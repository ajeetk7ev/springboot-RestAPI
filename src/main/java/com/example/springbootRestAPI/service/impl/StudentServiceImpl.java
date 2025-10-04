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
}
