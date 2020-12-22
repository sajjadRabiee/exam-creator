package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import com.examcreator.finalproject.repository.TextQuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;

public class TextQuestionServiceImpl extends BaseEntityServiceImpl<TextQuestion,Long, TextQuestionRepository> implements TextQuestionService {
    public TextQuestionServiceImpl(TextQuestionRepository textQuestionRepository, ApplicationContext context) {
        super(textQuestionRepository, context);
    }
}
