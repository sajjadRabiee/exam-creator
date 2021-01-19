package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.TextQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import com.examcreator.finalproject.repository.TextQuestionRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TextQuestionServiceImpl extends BaseEntityServiceImpl<TextQuestion, Long, TextQuestionRepository> implements TextQuestionService {
    private final ApplicationContext context;
    private final TextQuestionRepository textQuestionRepository;
    private final ExamService examService;
    private final ScoreService scoreService;
    private final StudentService studentService;
    private final StudentAnswerService studentAnswerService;

    public TextQuestionServiceImpl(TextQuestionRepository textQuestionRepository, ApplicationContext context, ExamService examService, ScoreService scoreService, StudentService studentService, StudentAnswerService studentAnswerService) {
        super(textQuestionRepository, context);
        this.context = context;
        this.textQuestionRepository = textQuestionRepository;
        this.examService = examService;
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.studentAnswerService = studentAnswerService;
    }

    @Override
    public List<TextQuestion> findQuestionsOfExam(Long examId) {
        Exam exam = examService.findByID(examId).get();
        return textQuestionRepository.findAllByExamListContaining(exam);
    }

    @Override
    public List<TextQuestionDTO> findQuestionsWithAnswer(Exam exam, String username) {
        Student student = studentService.findStudentByUsername(username).get();

        List<TextQuestion> questionsOfExam = findQuestionsOfExam(exam.getId());
        List<TextQuestionDTO> textQuestionDTOList = new ArrayList<>();
        for (TextQuestion q : questionsOfExam) {
            Optional<StudentAnswer> answer = studentAnswerService.findByScoreAndStudent(q.getScoreObj(exam), student);
            TextQuestionDTO bean;
            if (answer.isPresent()) {
                bean = context.getBean(TextQuestionDTO.class, q, exam, answer.get());
            } else {
                bean = context.getBean(TextQuestionDTO.class, q, exam);
            }
            textQuestionDTOList.add(bean);
        }
        return textQuestionDTOList;
    }

    @Override
    public boolean save(TextQuestionDTO textQuestionDTO, Long examId) {
        TextQuestion textQuestion = context.getBean(TextQuestion.class);
        Exam exam = examService.findByID(examId).get();
        textQuestion.setTitle(textQuestionDTO.getTitle());
        textQuestion.setContext(textQuestionDTO.getContext());
        textQuestion.setAnswer(textQuestionDTO.getAnswer());
        textQuestion.setExam(exam);
        textQuestion.setTeacher(exam.getCourse().getTeacher());
        TextQuestion save = textQuestionRepository.save(textQuestion);
        scoreService.saveScore(Long.valueOf(textQuestionDTO.getScore()), textQuestion, exam);
        return save != null;
    }

    @Override
    public boolean edit(TextQuestionDTO questionDTO, Long questionId, Long examId) {
        TextQuestion question = textQuestionRepository.findById(questionId).get();
        Exam exam = examService.findByID(examId).get();
        question.setTitle(questionDTO.getTitle());
        question.setContext(questionDTO.getContext());
        question.setAnswer(questionDTO.getAnswer());
        scoreService.editScore(Long.valueOf(questionDTO.getScore()), question, exam);
        TextQuestion save = textQuestionRepository.save(question);
        return save != null;
    }
}
