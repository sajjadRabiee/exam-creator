package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Score;
import com.examcreator.finalproject.service.base.BaseEntityService;

public interface ScoreService extends BaseEntityService<Score,Long> {
    Score saveScore(Long scoreNumber, Question question, Exam exam);
    Score editScore(Long scoreNumber , Question question , Exam exam);
}
