package com.examcreator.finalproject.entities.classEntities.OtherObjects;

import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Lob
    private String context;

    @Lob
    private String answer;

    @OneToMany(mappedBy = "question")
    private List<Score> score = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "question_exam",
            joinColumns = {@JoinColumn(name = "fk_question")},
            inverseJoinColumns = {@JoinColumn(name = "fk_exam")}
    )
    private List<Exam> examList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fk_teacher")
    private Teacher teacher;

    @PreRemove
    public void doItBeforeRemove(){
        examList.clear();
        for(Score s:score){
            s.setQuestion(null);
        }
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

    public Long getScore(Exam exam) {
        long score = 0;
        for(Score s : this.score){
           if(s.getExam().equals(exam)){
               score = s.getScore();
           }
        }
        return score;
    }

    public Score getScoreObj(Exam exam) {
        Score score = null;
        for(Score s : this.score){
            if(s.getExam().equals(exam)){
                score = s;
            }
        }
        return score;
    }

    public void setScore(Score score) {
        this.score.add(score);
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

    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    public void setExam(Exam exam) {
        this.examList.add(exam);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
