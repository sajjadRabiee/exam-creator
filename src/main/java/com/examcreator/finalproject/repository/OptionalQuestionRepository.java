package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionalQuestionRepository extends JpaRepository<OptionalQuestion,Long> {
    List<OptionalQuestion> findAllByExamListContaining(Exam exam);
}
