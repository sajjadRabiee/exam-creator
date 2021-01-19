package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.OptionalQuestion;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Question;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.TextQuestion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class QuestionDTO {
    private long id;
    private String type;
    private String title;
    private String score;

    public QuestionDTO() {
    }

    public QuestionDTO(Question question, Exam exam) {
        this.id = question.getId();
        if(question instanceof OptionalQuestion){
            this.type = "optional";
        }else if(question instanceof TextQuestion){
            this.type = "text";
        }
        this.title = question.getTitle();
        this.score = String.valueOf(question.getScore(exam));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
