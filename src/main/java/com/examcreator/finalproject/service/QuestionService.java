package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.BankQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;
import java.util.Set;

public interface QuestionService extends BaseEntityService<Question,Long> {
    List<Question> findQuestionsOfExam(Long examId);

    Set<Question> findQuestionsOfCourse(Long courseId);

    boolean addQuestionFromBank(BankQuestionDTO bankQuestionDTO);
}
