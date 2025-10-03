package com.example.springbootRestAPI.repository;

import com.example.springbootRestAPI.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
