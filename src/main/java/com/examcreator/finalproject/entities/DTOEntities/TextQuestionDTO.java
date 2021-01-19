package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TextQuestionDTO {
    private Long id;
    private String title;
    private String score;
    private String context;
    private String answer;
    private String studentAnswer;

    public TextQuestionDTO() {
    }

    public TextQuestionDTO(TextQuestion textQuestion, Exam exam) {
        this.id = textQuestion.getId();
        this.title = textQuestion.getTitle();
        this.score = String.valueOf(textQuestion.getScore(exam));
        this.context = textQuestion.getContext();
        this.answer = textQuestion.getAnswer();
    }

    public TextQuestionDTO(TextQuestion textQuestion, Exam exam, StudentAnswer studentAnswer) {
        this.id = textQuestion.getId();
        this.title = textQuestion.getTitle();
        this.score = String.valueOf(textQuestion.getScore(exam));
        this.context = textQuestion.getContext();
        this.answer = textQuestion.getAnswer();
        this.studentAnswer = studentAnswer.getAnswer();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
}
