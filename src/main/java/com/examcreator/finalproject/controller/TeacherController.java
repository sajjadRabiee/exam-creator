package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.service.CourseService;
import com.examcreator.finalproject.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model){
        String username = principal.getName();
        List<Course> coursesOfTeacher = courseService.findCoursesOfTeacher(username);
        model.addAttribute("courses",coursesOfTeacher);
        return "User/Teacher/dashboard";
    }
}
