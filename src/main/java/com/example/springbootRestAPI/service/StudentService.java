package com.example.springbootRestAPI.service;

import com.example.springbootRestAPI.dto.AddStudentRequestDto;
import com.example.springbootRestAPI.dto.StudentDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> getAllStudents();
    public StudentDto getStudentById(Long id);
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);
}
