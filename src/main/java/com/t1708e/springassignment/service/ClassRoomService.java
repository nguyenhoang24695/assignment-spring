package com.t1708e.springassignment.service;

import com.t1708e.springassignment.entity.ClassRoom;
import com.t1708e.springassignment.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomService {
    @Autowired
    ClassRoomRepository classRoomRepository;
    public List<ClassRoom> classRoomList(){
        return classRoomRepository.findAll();
    }
}
