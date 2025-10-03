package com.example.springbootRestAPI.controller;

import com.example.springbootRestAPI.dto.StudentDto;
import com.example.springbootRestAPI.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDto> getAllStudents(){
        System.out.println("ka hal chal");
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable Long id){
       return studentService.getStudentById(id);
    }
}
