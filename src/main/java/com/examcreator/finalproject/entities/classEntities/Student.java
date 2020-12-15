package com.examcreator.finalproject.entities.classEntities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "student")
public class Student extends User {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "studentList")
    private List<Course> courseList = new ArrayList<>();

    @PreRemove
    public void doItBeforeRemove(){
        for(Course c:courseList){
            c.getStudentList().remove(this);
        }
    }

    public Student() {
        setRole(Role.ROLE_STUDENT);
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
