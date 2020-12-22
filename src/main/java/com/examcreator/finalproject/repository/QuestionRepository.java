package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByExam(Exam exam);
    List<Question> findAllByTeacher(Teacher teacher);
}
