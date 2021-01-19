package com.examcreator.finalproject.entities.DTOEntities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BankQuestionDTO {
    private long questionId;
    private long examId;
    private long newScore;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public long getNewScore() {
        return newScore;
    }

    public void setNewScore(long newScore) {
        this.newScore = newScore;
    }
}
