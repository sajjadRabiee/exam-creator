package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.BankQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.repository.QuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl extends BaseEntityServiceImpl<Question, Long, QuestionRepository> implements QuestionService{
    private final ApplicationContext context;
    private final QuestionRepository questionRepository;
    private final ExamService examService;
    private final ScoreService scoreService;

    public QuestionServiceImpl(QuestionRepository questionRepository, ApplicationContext context, ExamService examService, ScoreService scoreService) {
        super(questionRepository, context);
        this.context = context;
        this.questionRepository = questionRepository;
        this.examService = examService;
        this.scoreService = scoreService;
    }

    @Override
    public List<Question> findQuestionsOfExam(Long examId) {
        Exam exam = examService.findByID(examId).get();
        return questionRepository.findAllByExamListContaining(exam);
    }

    @Override
    public Set<Question> findQuestionsOfCourse(Long courseId) {
        List<Exam> examsOfCourse = examService.findExamsOfCourse(courseId);
        Set<Question> questions = new HashSet<>();
        for(Exam e : examsOfCourse){
            Set<Question> collect = new HashSet<>(questionRepository.findAllByExamListContaining(e));
            questions.addAll(collect);
        }
        return questions;
    }

    @Override
    public boolean addQuestionFromBank(BankQuestionDTO bankQuestionDTO) {
        Question question = questionRepository.findById(bankQuestionDTO.getQuestionId()).get();
        Exam exam = examService.findByID(bankQuestionDTO.getExamId()).get();
        question.setExam(exam);
        scoreService.saveScore(bankQuestionDTO.getNewScore(),question,exam);
        Question save = questionRepository.save(question);
        return save != null;
    }
}
