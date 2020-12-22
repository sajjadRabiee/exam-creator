package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Exam;
import com.examcreator.finalproject.entities.classEntities.Question;
import com.examcreator.finalproject.entities.classEntities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByExam(Exam exam);
    List<Question> findAllByTeacher(Teacher teacher);
}
