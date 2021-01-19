package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Score;
import com.examcreator.finalproject.repository.ScoreRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends BaseEntityServiceImpl<Score,Long, ScoreRepository> implements ScoreService {
    private final ApplicationContext context;
    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository, ApplicationContext context) {
        super(scoreRepository, context);
        this.context = context;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score saveScore(Long scoreNumber, Question question, Exam exam) {
        Score score = context.getBean(Score.class);
        score.setScore(scoreNumber);
        score.setExam(exam);
        score.setQuestion(question);
        Score save = scoreRepository.save(score);
        return save;
    }

    @Override
    public Score editScore(Long scoreNumber, Question question, Exam exam) {
        Score score = scoreRepository.findScoreByExamAndQuestion(exam, question);
        score.setScore(scoreNumber);
        Score save = scoreRepository.save(score);
        return save;
    }
}
