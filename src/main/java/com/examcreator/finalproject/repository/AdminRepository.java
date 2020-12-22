package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.Users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}
