package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.StudentDTO;
import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.entities.classEntities.Student;
import com.examcreator.finalproject.repository.CourseRepository;
import com.examcreator.finalproject.repository.StudentRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl extends BaseEntityServiceImpl<Student,Long, StudentRepository> implements StudentService {
    private final ApplicationContext context;
    private final CourseService courseService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(@Autowired StudentRepository studentRepository, @Autowired ApplicationContext context,@Autowired @Lazy CourseService courseService) {
        super(studentRepository,context);
        this.context = context;
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public Optional<Student> findStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }

    @Override
    public List<Student> findStudentsOfCourse(Course course) {
        return studentRepository.findAllByCourseListContains(course);
    }

    @Override
    public List<Student> findStudentsAreEnable() {
        return studentRepository.findAllByEnabledIsTrue();
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO, Long courseId) {
        Course course = courseService.findByID(courseId).get();
        Student student = studentRepository.findStudentByUsername(studentDTO.getUsername()).get();
        List<Student> studentList = course.getStudentList();
        studentList.add(student);
        Course save = courseService.save(course);
        return save != null;
    }
}
