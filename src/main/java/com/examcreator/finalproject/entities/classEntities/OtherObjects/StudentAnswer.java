package com.examcreator.finalproject.entities.classEntities.OtherObjects;

import com.examcreator.finalproject.entities.classEntities.Users.Student;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Scope("prototype")

@Entity
public class StudentAnswer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String answer;

    @Column
    private boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "fk_score")
    private Score score;

    @ManyToOne
    @JoinColumn(name = "fk_student")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
