package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import com.examcreator.finalproject.repository.OptionalQuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;

public class OptionalQuestionServiceImpl extends BaseEntityServiceImpl<OptionalQuestion, Long, OptionalQuestionRepository> implements OptionalQuestionService {
    public OptionalQuestionServiceImpl(OptionalQuestionRepository optionalQuestionRepository, ApplicationContext context) {
        super(optionalQuestionRepository, context);
    }
}
