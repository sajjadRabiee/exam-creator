package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.Teacher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TeacherDTO {
    private String username;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        this.username = teacher.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
