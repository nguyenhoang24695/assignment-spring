package com.t1708e.springassignment.repository;

import com.t1708e.springassignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);
}
