package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.ExamDTO;
import com.examcreator.finalproject.entities.DTOEntities.OptionalQuestionDTO;
import com.examcreator.finalproject.entities.DTOEntities.TextQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;

public interface TextQuestionService extends BaseEntityService<TextQuestion, Long> {
    List<TextQuestion> findQuestionsOfExam(Long examId);

    List<TextQuestionDTO> findQuestionsWithAnswer(Exam exam, String username);

    boolean save(TextQuestionDTO textQuestionDTO, Long examId);

    boolean edit(TextQuestionDTO textQuestionDTO, Long questionId, Long examId);
}
