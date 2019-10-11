package com.t1708e.springassignment.service;

import com.t1708e.springassignment.config.WebSecurityConfig;
import com.t1708e.springassignment.entity.ClassRoom;
import com.t1708e.springassignment.entity.Student;
import com.t1708e.springassignment.repository.ClassRoomRepository;
import com.t1708e.springassignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    WebSecurityConfig webSecurityConfig;

    public List<Student> listStudent() {
        return studentRepository.findAll();
    }

    public Student store(Student student) {
        student.setPassword(webSecurityConfig.passwordEncoder().encode(student.getPassword()));
        return studentRepository.save(student);
    }
    public Student getByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public Student saveClass(String cl) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Student student = studentRepository.findByEmail(email);
        ClassRoom classRoom = classRoomRepository.findById(Integer.parseInt(cl)).orElse(null);
        classRoom.addStudent(student);
        classRoomRepository.save(classRoom);
        return student;
    };
}
