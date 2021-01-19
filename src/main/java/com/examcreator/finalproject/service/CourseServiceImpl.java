package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.CourseDTO;
import com.examcreator.finalproject.entities.DTOEntities.TeacherDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import com.examcreator.finalproject.repository.CourseRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseServiceImpl extends BaseEntityServiceImpl<Course,Long, CourseRepository> implements CourseService {
    private final ApplicationContext context;
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseServiceImpl(@Autowired CourseRepository courseRepository, @Autowired ApplicationContext context, @Autowired TeacherService teacherService, StudentService studentService) {
        super(courseRepository,context);
        this.context = context;
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    @Override
    public boolean saveCourse(CourseDTO courseDTO) {
        String title = courseDTO.getTitle();
        String startDay = courseDTO.getStartDay();
        String endDay = courseDTO.getEndDay();

        Course course = context.getBean(Course.class);
        course.setTitle(title);
        course.setStartDay(LocalDate.parse(startDay));
        course.setEndDay(LocalDate.parse(endDay));

        Course save = courseRepository.save(course);
        return save != null;
    }

    @Override
    public boolean saveCourse(TeacherDTO teacherDTO, Long id) {
        String teacherName = teacherDTO.getUsername();
        Course course = courseRepository.findById(id).get();
        course.setTeacher(teacherService.findTeacherByUsername(teacherName).get());
        Course save = courseRepository.save(course);
        return save != null;
    }

    @Override
    public List<Course> findCoursesOfTeacher(String teacherUsername) {
        Teacher teacher = teacherService.findTeacherByUsername(teacherUsername).get();
        return courseRepository.findAllByTeacherEquals(teacher);
    }

    @Override
    public List<Course> findCoursesOfStudent(String studentUsername) {
        Student student = studentService.findStudentByUsername(studentUsername).get();
        return courseRepository.findAllByStudentListContains(student);
    }
}
