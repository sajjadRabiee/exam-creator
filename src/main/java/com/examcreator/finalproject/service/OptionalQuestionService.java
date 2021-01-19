package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.OptionalQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;

public interface OptionalQuestionService extends BaseEntityService<OptionalQuestion, Long> {
    List<OptionalQuestion> findQuestionsOfExam(Long examId);

    List<OptionalQuestionDTO> findQuestionsWithAnswer(Exam exam, String username);

    boolean save(OptionalQuestionDTO optionalQuestionDTO, Long examId);

    boolean edit(OptionalQuestionDTO optionalQuestionDTO, Long questionId, Long examId);
}
