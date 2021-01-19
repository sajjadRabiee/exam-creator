package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.StudentDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;
import java.util.Optional;

public interface StudentService extends BaseEntityService<Student,Long> {
    Optional<Student> findStudentByUsername(String username);
    List<Student> findStudentsOfCourse(Course course);
    List<Student> findStudentsAreEnable();
    boolean saveStudent(StudentDTO studentDTO,Long courseId);
}
