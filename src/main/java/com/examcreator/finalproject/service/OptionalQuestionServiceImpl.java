package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.OptionalQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.repository.OptionalQuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OptionalQuestionServiceImpl extends BaseEntityServiceImpl<OptionalQuestion, Long, OptionalQuestionRepository> implements OptionalQuestionService {
    private final ApplicationContext context;
    private final OptionalQuestionRepository optionalQuestionRepository;
    private final ExamService examService;
    private final ScoreService scoreService;
    private final StudentService studentService;
    private final StudentAnswerService studentAnswerService;

    public OptionalQuestionServiceImpl(OptionalQuestionRepository optionalQuestionRepository, ApplicationContext context, ExamService examService, ScoreService scoreService, StudentService studentService, StudentAnswerService studentAnswerService) {
        super(optionalQuestionRepository, context);
        this.context = context;
        this.optionalQuestionRepository = optionalQuestionRepository;
        this.examService = examService;
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.studentAnswerService = studentAnswerService;
    }

    @Override
    public List<OptionalQuestion> findQuestionsOfExam(Long examId) {
        Exam exam = examService.findByID(examId).get();
        return optionalQuestionRepository.findAllByExamListContaining(exam);
    }

    @Override
    public List<OptionalQuestionDTO> findQuestionsWithAnswer(Exam exam, String username) {
        Student student = studentService.findStudentByUsername(username).get();

        List<OptionalQuestion> questionsOfExam = findQuestionsOfExam(exam.getId());
        List<OptionalQuestionDTO> optionalQuestionDTOList = new ArrayList<>();
        for(OptionalQuestion q : questionsOfExam){
            Optional<StudentAnswer> answer = studentAnswerService.findByScoreAndStudent(q.getScoreObj(exam), student);
            OptionalQuestionDTO bean;
            if(answer.isPresent()){
                bean = context.getBean(OptionalQuestionDTO.class, q, exam, answer.get());
            }else{
                bean = context.getBean(OptionalQuestionDTO.class, q, exam);
            }
            optionalQuestionDTOList.add(bean);
        }
        return optionalQuestionDTOList;
    }

    @Override
    public boolean save(OptionalQuestionDTO optionalQuestionDTO, Long examId) {
        OptionalQuestion optionalQuestion = context.getBean(OptionalQuestion.class);
        Exam exam = examService.findByID(examId).get();
        optionalQuestion.setTitle(optionalQuestionDTO.getTitle());
        optionalQuestion.setContext(optionalQuestionDTO.getContext());
        int answer = optionalQuestionDTO.getAnswer() - 1;
        optionalQuestion.setAnswer(optionalQuestionDTO.getOptions().get(answer));
        optionalQuestion.setOptions(optionalQuestionDTO.getOptions());
        optionalQuestion.setExam(exam);
        optionalQuestion.setTeacher(exam.getCourse().getTeacher());
        OptionalQuestion save = optionalQuestionRepository.save(optionalQuestion);
        scoreService.saveScore(Long.valueOf(optionalQuestionDTO.getScore()), optionalQuestion, exam);
        return save != null;
    }

    @Override
    public boolean edit(OptionalQuestionDTO questionDTO, Long questionId, Long examId) {
        OptionalQuestion question = optionalQuestionRepository.findById(questionId).get();
        Exam exam = examService.findByID(examId).get();
        question.setTitle(questionDTO.getTitle());
        question.setContext(questionDTO.getContext());
        int answer = questionDTO.getAnswer() - 1;
        question.setAnswer(questionDTO.getOptions().get(answer));
        question.setOptions(questionDTO.getOptions());
        scoreService.editScore(Long.valueOf(questionDTO.getScore()), question, exam);
        OptionalQuestion save = optionalQuestionRepository.save(question);
        return save != null;
    }
}
