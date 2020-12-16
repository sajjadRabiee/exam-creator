package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.entities.classEntities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findAllByCourse(Course course);
}
