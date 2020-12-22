package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.repository.QuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;

public class QuestionServiceImpl extends BaseEntityServiceImpl<Question, Long, QuestionRepository> implements QuestionService{
    public QuestionServiceImpl(QuestionRepository questionRepository, ApplicationContext context) {
        super(questionRepository, context);
    }
}
