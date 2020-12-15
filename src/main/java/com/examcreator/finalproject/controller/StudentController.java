package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.repository.CourseRepository;
import com.examcreator.finalproject.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    private final CourseService courseService;

    public StudentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        String username = principal.getName();
        List<Course> coursesOfStudent = courseService.findCoursesOfStudent(username);
        model.addAttribute("courses",coursesOfStudent);
        return "User/Student/dashboard";
    }
}
