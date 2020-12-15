package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.UserInformationDTO;
import com.examcreator.finalproject.entities.classEntities.Role;
import com.examcreator.finalproject.entities.classEntities.User;
import com.examcreator.finalproject.service.base.BaseEntityService;
import javassist.compiler.ast.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseEntityService<User,Long> {
    boolean registerUser(UserInformationDTO userInformationDTO);

    Optional<User> loginUser(String username);

    boolean changeTheRole(Long ID, Role role);

    boolean changeSituationTheUser(Long ID);

    Page<User> searchAllUser(String keyword, Pageable pageable);
}
