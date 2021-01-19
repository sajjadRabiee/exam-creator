package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class OptionalQuestionDTO {
    private Long id;
    private String title;
    private String score;
    private String context;
    private int answer;
    private String studentAnswer;
    private List<String> options = new ArrayList<>();

    public OptionalQuestionDTO() {
    }

    public OptionalQuestionDTO(OptionalQuestion optionalQuestion, Exam exam) {
        this.id = optionalQuestion.getId();
        this.title = optionalQuestion.getTitle();
        this.score = String.valueOf(optionalQuestion.getScore(exam));
        this.context = optionalQuestion.getContext();
        this.answer = optionalQuestion.getOptions().indexOf(optionalQuestion.getAnswer());
        this.options = optionalQuestion.getOptions();
    }

    public OptionalQuestionDTO(OptionalQuestion optionalQuestion, Exam exam, StudentAnswer studentAnswer) {
        this.id = optionalQuestion.getId();
        this.title = optionalQuestion.getTitle();
        this.score = String.valueOf(optionalQuestion.getScore(exam));
        this.context = optionalQuestion.getContext();
        this.answer = optionalQuestion.getOptions().indexOf(optionalQuestion.getAnswer());
        this.studentAnswer = studentAnswer.getAnswer();
        this.options = optionalQuestion.getOptions();
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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
}
