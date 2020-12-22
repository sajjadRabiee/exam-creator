package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Users.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> findTeacherByUsername(String username);
    List<Teacher> findAllByEnabledIsTrue();
}
