package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.ExamDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.entities.enumEntities.TypeOfExam;
import com.examcreator.finalproject.repository.CourseRepository;
import com.examcreator.finalproject.repository.ExamRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl extends BaseEntityServiceImpl<Exam,Long, ExamRepository> implements ExamService {
    private final ApplicationContext context;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public ExamServiceImpl(ExamRepository examRepository, ApplicationContext context, CourseRepository courseRepository) {
        super(examRepository, context);
        this.context = context;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public boolean saveExam(ExamDTO examDTO, Long courseId) {
        Exam exam = context.getBean(Exam.class);
        exam.setTitle(examDTO.getTitle());
        if(examDTO.getTypeOfExam().equals("optional")){
            exam.setTypeOfExam(TypeOfExam.TYPE_OPTIONAL);
        }else if(examDTO.getTypeOfExam().equals("text")){
            exam.setTypeOfExam(TypeOfExam.TYPE_TEXT);
        }else{
            exam.setTypeOfExam(TypeOfExam.TYPE_COMPOUND);
        }
        exam.setDate(LocalDate.parse(examDTO.getDate()));
        exam.setDescription(examDTO.getDescription());
        exam.setStartTime(LocalTime.parse(examDTO.getStartTime()));
        exam.setEndTime(LocalTime.parse(examDTO.getEndTime()));
        Course course = courseRepository.findById(courseId).get();
        exam.setCourse(course);
        Exam save = examRepository.save(exam);
        return save != null;
    }

    @Override
    public boolean editExam(ExamDTO examDTO, Long oldExamId) {
        Exam exam = examRepository.findById(oldExamId).get();
        exam.setTitle(examDTO.getTitle());
        exam.setDate(LocalDate.parse(examDTO.getDate()));
        exam.setDescription(examDTO.getDescription());
        exam.setStartTime(LocalTime.parse(examDTO.getStartTime()));
        exam.setEndTime(LocalTime.parse(examDTO.getEndTime()));
        Exam save = examRepository.save(exam);
        return save != null;
    }

    @Override
    public List<Exam> findExamsOfCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        List<Exam> exams = examRepository.findAllByCourse(course);
        return exams;
    }
}
