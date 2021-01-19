package com.examcreator.finalproject.entities.DTOEntities;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class ExamDTO {
    private Long id;
    private String title;
    private String typeOfExam;
    private String description;
    private String date;
    private String startTime;
    private String endTime;

    public ExamDTO() {
    }

    public ExamDTO(Exam exam) {
        this.id = exam.getId();
        this.title = exam.getTitle();
        this.description = exam.getDescription();
        this.date = exam.getDate().toString();
        this.startTime = exam.getStartTime().toString();
        this.endTime = exam.getEndTime().toString();
        this.typeOfExam = exam.getTypeOfExam().name();
        System.out.println(this.typeOfExam);
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

    public String getTypeOfExam() {
        return typeOfExam;
    }

    public void setTypeOfExam(String typeOfExam) {
        this.typeOfExam = typeOfExam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
