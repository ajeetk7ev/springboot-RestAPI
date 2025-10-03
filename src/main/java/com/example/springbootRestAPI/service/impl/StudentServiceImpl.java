package com.example.springbootRestAPI.service.impl;

import com.example.springbootRestAPI.dto.StudentDto;
import com.example.springbootRestAPI.entity.Student;
import com.example.springbootRestAPI.repository.StudentRepository;
import com.example.springbootRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    public List<StudentDto> getAllStudents(){
         List<Student> students = studentRepository.findAll();
         List<StudentDto> studentDtoList = students.stream().
                  map(student->new StudentDto(student.getId(), student.getName(), student.getEmail(), student.getBranch())).toList();
         return studentDtoList;
    }
}
