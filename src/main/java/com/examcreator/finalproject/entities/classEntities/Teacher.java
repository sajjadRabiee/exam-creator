package com.examcreator.finalproject.entities.classEntities;

import com.examcreator.finalproject.entities.enumEntities.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cTeacher")
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(mappedBy = "qTeacher")
    private List<Question> questionList = new ArrayList<>();

    @PreRemove
    public void doItBeforeRemove(){
        for(Course c :courseList){
            c.setCTeacher(null);
        }
    }

    public Teacher() {
        super.setRole(Role.ROLE_TEACHER);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
