package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.CourseDTO;
import com.examcreator.finalproject.entities.DTOEntities.TeacherDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;

public interface CourseService extends BaseEntityService<Course,Long> {
    boolean saveCourse(CourseDTO courseDTO);
    boolean saveCourse(TeacherDTO teacherDTO , Long id);

    List<Course> findCoursesOfTeacher(String teacherUsername);
    List<Course> findCoursesOfStudent(String studentUsername);
}
