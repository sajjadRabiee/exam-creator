package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.entities.classEntities.Student;
import com.examcreator.finalproject.entities.classEntities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByTeacherEquals(Teacher teacher);
    List<Course> findAllByStudentListContains(Student student);
}
