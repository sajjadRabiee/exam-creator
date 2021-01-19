package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.OtherObjects.Score;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.StudentAnswer;
import com.examcreator.finalproject.entities.classEntities.Users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    Optional<StudentAnswer> findByScoreAndStudent(Score score , Student student);
}
