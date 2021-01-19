package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.DTOEntities.CourseDTO;
import com.examcreator.finalproject.entities.DTOEntities.StudentDTO;
import com.examcreator.finalproject.entities.DTOEntities.TeacherDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import com.examcreator.finalproject.service.CourseService;
import com.examcreator.finalproject.service.StudentService;
import com.examcreator.finalproject.service.TeacherService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final ApplicationContext context;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService, ApplicationContext context) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.context = context;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-course")
    public String showAddCoursePage(Model model) {
        model.addAttribute("course", context.getBean(CourseDTO.class));
        model.addAttribute("courses", courseService.findAll());
        return "User/Admin/add-course";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-course")
    public String addCourse(@ModelAttribute CourseDTO course, BindingResult result) {
        try {
            courseService.saveCourse(course);
            return "redirect:/course/add-course";
        }catch (DateTimeParseException e){
            ObjectError error = new ObjectError("fail","we can not add your course");
            result.addError(error);
            return "redirect:/course/add-course";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.delete(courseService.findByID(id).get());
        return "redirect:/course/add-course";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-teacher-to-course/{id}")
    public String showAddTeacherToCoursePage(@PathVariable Long id, Model model) {
        Course course = courseService.findByID(id).get();
        model.addAttribute("course", context.getBean(CourseDTO.class, course));
        model.addAttribute("newTeacher", context.getBean(TeacherDTO.class));
        Function<Teacher, TeacherDTO> function = a -> {
            TeacherDTO teacherDTO = context.getBean(TeacherDTO.class, a);
            return teacherDTO;
        };
        List<TeacherDTO> teacherDTOList = teacherService.findTeachersAreEnabled().stream().map(function).collect(Collectors.toList());
        model.addAttribute("teachers", teacherDTOList);
        return "User/Admin/add-teacher-to-course";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-teacher-to-course/{id}")
    public String AddTeacherToCoursePage(@ModelAttribute("newTeacher") TeacherDTO teacherDTO, @PathVariable Long id) {

        courseService.saveCourse(teacherDTO, id);
        return "redirect:/course/add-course";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-students-to-course/{id}")
    public String showAddStudentsToCoursePage(@PathVariable Long id, Model model) {
        Course course = courseService.findByID(id).get();
        model.addAttribute("course", context.getBean(CourseDTO.class, course));
        model.addAttribute("newStudent", context.getBean(StudentDTO.class));
        Function<Student, StudentDTO> function = a -> {
            StudentDTO studentDTO = context.getBean(StudentDTO.class, a);
            return studentDTO;
        };
        List<StudentDTO> studentDTOList1 = studentService.findStudentsAreEnable().stream().map(function).collect(Collectors.toList());
        model.addAttribute("students", studentDTOList1);
        List<StudentDTO> studentDTOList2 = studentService.findStudentsOfCourse(course).stream().map(function).collect(Collectors.toList());
        model.addAttribute("studentsOfCourse", studentDTOList2);
        return "User/Admin/add-students-to-course";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-students-to-course/{id}")
    public String addStudentsToCoursePage(@ModelAttribute("newStudent") StudentDTO studentDTO, @PathVariable Long id, BindingResult result) {
        studentService.saveStudent(studentDTO, id);
        return "redirect:/course/add-students-to-course/" + id;
    }
}
