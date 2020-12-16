package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.Exam;
import com.examcreator.finalproject.repository.ExamRepository;
import com.examcreator.finalproject.service.base.BaseEntityService;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;

public class ExamServiceImpl extends BaseEntityServiceImpl<Exam,Long, ExamRepository> implements ExamService {
    private final ApplicationContext context;
    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository, ApplicationContext context) {
        super(examRepository, context);
        this.context = context;
        this.examRepository = examRepository;
    }
}
