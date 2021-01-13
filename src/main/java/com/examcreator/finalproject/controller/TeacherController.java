package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.DTOEntities.*;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.*;
import com.examcreator.finalproject.entities.enumEntities.TypeOfExam;
import com.examcreator.finalproject.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final ApplicationContext context;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ExamService examService;
    private final QuestionService questionService;
    private final OptionalQuestionService optionalQuestionService;
    private final TextQuestionService textQuestionService;

    public TeacherController(ApplicationContext context, TeacherService teacherService, CourseService courseService, ExamService examService, QuestionService questionService, OptionalQuestionService optionalQuestionService, TextQuestionService textQuestionService) {
        this.context = context;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.examService = examService;
        this.questionService = questionService;
        this.optionalQuestionService = optionalQuestionService;
        this.textQuestionService = textQuestionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        String username = principal.getName();
        List<Course> coursesOfTeacher = courseService.findCoursesOfTeacher(username);
        model.addAttribute("courses", coursesOfTeacher);
        return "User/Teacher/dashboard";
    }
}
