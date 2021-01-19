package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.StudentAnswerDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Score;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.Optional;


public interface StudentAnswerService extends BaseEntityService<StudentAnswer,Long> {
    Optional<StudentAnswer> findByScoreAndStudent(Score score, Student student);

    boolean saveAnswer(StudentAnswerDTO studentAnswerDTO,String username);
}
