package com.examcreator.finalproject.repository;

import com.examcreator.finalproject.entities.classEntities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u where u.username like %?1% " +
            "or u.name.firstName like %?1%" +
            "or u.name.lastName like %?1%" +
            "or u.address.country like %?1%" +
            "or u.address.city like %?1%" +
            "or u.address.restAddress like %?1%" +
            "or u.address.zipCode like %?1%")
    Page<User> search(String keyword, Pageable pageable);

}
