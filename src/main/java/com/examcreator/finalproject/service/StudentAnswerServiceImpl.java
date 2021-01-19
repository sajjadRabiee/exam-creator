package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.StudentAnswerDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.*;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.repository.StudentAnswerRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAnswerServiceImpl extends BaseEntityServiceImpl<StudentAnswer, Long , StudentAnswerRepository> implements StudentAnswerService {
    private final ApplicationContext context;
    private final StudentAnswerRepository studentAnswerRepository;
    private final StudentService studentService;
    private final ExamService examService;
    private final QuestionService questionService;

    public StudentAnswerServiceImpl(StudentAnswerRepository studentAnswerRepository, ApplicationContext context, StudentService studentService, ExamService examService, QuestionService questionService) {
        super(studentAnswerRepository, context);
        this.context = context;
        this.studentAnswerRepository = studentAnswerRepository;
        this.studentService = studentService;
        this.examService = examService;
        this.questionService = questionService;
    }

    @Override
    public Optional<StudentAnswer> findByScoreAndStudent(Score score, Student student) {
        Optional<StudentAnswer> studentAnswer = studentAnswerRepository.findByScoreAndStudent(score, student);
        return studentAnswer;
    }

    @Override
    public boolean saveAnswer(StudentAnswerDTO studentAnswerDTO,String username) {
        Exam exam = examService.findByID(studentAnswerDTO.getExamId()).get();
        Question question = questionService.findByID(studentAnswerDTO.getQuestionId()).get();
        StudentAnswer studentAnswer = context.getBean(StudentAnswer.class);
        studentAnswer.setAnswer(studentAnswerDTO.getAnswer());
        studentAnswer.setScore(question.getScoreObj(exam));
        studentAnswer.setStudent(studentService.findStudentByUsername(username).get());
        if(question instanceof OptionalQuestion){
            if(question.getAnswer().equals(studentAnswerDTO.getAnswer())){
                studentAnswer.setTrue(true);
            }else{
                studentAnswer.setTrue(false);
            }
        }
        StudentAnswer save = studentAnswerRepository.save(studentAnswer);
        return save != null;
    }
}
