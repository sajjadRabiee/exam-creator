package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import com.examcreator.finalproject.service.base.BaseEntityService;

import java.util.List;
import java.util.Optional;


public interface TeacherService extends BaseEntityService<Teacher,Long> {
    Optional<Teacher> findTeacherByUsername(String username);
    List<Teacher> findTeachersAreEnabled();
}
