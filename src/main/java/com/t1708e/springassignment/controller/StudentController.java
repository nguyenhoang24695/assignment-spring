package com.t1708e.springassignment.controller;

import com.t1708e.springassignment.entity.ClassRoom;
import com.t1708e.springassignment.entity.Student;
import com.t1708e.springassignment.repository.ClassRoomRepository;
import com.t1708e.springassignment.service.ClassRoomService;
import com.t1708e.springassignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassRoomService classRoomService;

    @RequestMapping(method = RequestMethod.GET, value = "list")
    public String studentList() {
        studentService.listStudent();
        return "student/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String create(Model model) {
        model.addAttribute("student",new Student());
        return "student/form";
    }
    @RequestMapping( method = RequestMethod.POST,value = "create")
    public String store(Model model, @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "student/form";
        }
        studentService.store(student);
        return "redirect:/student/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail")
    public String edit( Model model) {
        String email = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Student student = studentService.getByEmail(email);

        if (student == null) {
            return "404";
        }
        List<ClassRoom> classRoomList = classRoomService.classRoomList();
        model.addAttribute("classRoomList",classRoomList);
        model.addAttribute("student", student);
        return "student/detail";
    }
    @RequestMapping(method = RequestMethod.POST,value = "/classroom")
    public String addClass(@RequestParam String[] classRoom){
        for (String cl: classRoom
             ) {
            studentService.saveClass(cl);
        }
        return "redirect:/student/detail";
    };
}
