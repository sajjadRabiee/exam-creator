package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.DTOEntities.UserInformationDTO;
import com.examcreator.finalproject.entities.classEntities.*;
import com.examcreator.finalproject.repository.UserRepository;
import com.examcreator.finalproject.security.PasswordEncoderSecurity;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import javassist.compiler.ast.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl extends BaseEntityServiceImpl<User,Long, UserRepository> implements UserService {
    private final ApplicationContext context;
    private final UserRepository userRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final PasswordEncoderSecurity passwordEncoder;

    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired ApplicationContext context, TeacherService teacherService, StudentService studentService, PasswordEncoderSecurity passwordEncoder) {
        super(userRepository,context);
        this.context = context;
        this.userRepository = userRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> loginUser(String username){
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean registerUser(UserInformationDTO userDTO) {
        User user = null;

        String roleName = userDTO.getRoleName();
        if(roleName.toLowerCase().contains("teacher")){
            user = context.getBean(Teacher.class);
        }else if(roleName.toLowerCase().contains("student")){
            user = context.getBean(Student.class);
        }
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getFirstName());

        Name name = context.getBean(Name.class);
        name.setFirstName(userDTO.getFirstName());
        name.setLastName(userDTO.getLastName());
        user.setName(name);

        Address address = context.getBean(Address.class);
        address.setCountry(userDTO.getCountry());
        address.setCity(userDTO.getCity());
        address.setRestAddress(userDTO.getRestAddress());
        address.setZipCode(userDTO.getZipCode());
        user.setAddress(address);

        User savedUser = userRepository.save(user);
        return savedUser != null;

    }

    @Override
    public boolean changeTheRole(Long ID,Role role) {
        Optional<User> oUserById = userRepository.findById(ID);
        User user = oUserById.get();
        user.setRole(role);
        UserInformationDTO newUser = context.getBean(UserInformationDTO.class, user);
        userRepository.delete(user);
        return registerUser(newUser);
    }

    @Override
    public boolean changeSituationTheUser(Long ID) {
        Optional<User> oUserById = userRepository.findById(ID);
        User user = oUserById.get();
        if(user.isEnabled()){
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
        User save = userRepository.save(user);
        return save != null;
    }

    @Override
    public Page<User> searchAllUser(String keyword, Pageable pageable) {
        if(keyword != null){
            return userRepository.search(keyword,pageable);
        }
        return userRepository.findAll(pageable);
    }
}
