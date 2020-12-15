package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.TeacherDTO;
import com.examcreator.finalproject.entities.classEntities.Course;
import com.examcreator.finalproject.entities.classEntities.Teacher;
import com.examcreator.finalproject.entities.classEntities.User;
import com.examcreator.finalproject.repository.TeacherRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl extends BaseEntityServiceImpl<Teacher,Long, TeacherRepository> implements TeacherService {
    private final ApplicationContext context;
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(@Autowired TeacherRepository teacherRepository, @Autowired ApplicationContext context) {
        super(teacherRepository,context);
        this.context = context;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findTeacherByUsername(String username) {
        return teacherRepository.findTeacherByUsername(username);
    }

    @Override
    public List<Teacher> findTeachersAreEnabled() {
        return teacherRepository.findAllByEnabledIsTrue();
    }
}
