package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.ExamDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;

public interface ExamService extends BaseEntityService<Exam,Long> {
    boolean saveExam(ExamDTO examDTO , Long courseId);

    boolean editExam(ExamDTO examDTO , Long oldExamId);

    List<Exam> findExamsOfCourse(Long courseId);

}
