package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.entities.classEntities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByUsername(String username);
    List<Student> findAllByCourseListContains(Course course);
    List<Student> findAllByEnabledIsTrue();
}
