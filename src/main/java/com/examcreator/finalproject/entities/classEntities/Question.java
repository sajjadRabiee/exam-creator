package com.examcreator.finalproject.entities.classEntities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope("prototype")

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "question" , discriminatorType = DiscriminatorType.STRING)
public class Question {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private Long score;

    @Lob
    private String context;

    @Lob
    private String answer;

    @ManyToOne
    @JoinColumn(name = "fk_exam")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "fk_teacher")
    private Teacher qTeacher;


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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
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

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Teacher getqTeacher() {
        return qTeacher;
    }

    public void setqTeacher(Teacher qTeacher) {
        this.qTeacher = qTeacher;
    }
}
