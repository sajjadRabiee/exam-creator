package com.examcreator.finalproject.service;

import com.examcreator.finalproject.entities.classEntities.Users.Admin;
import com.examcreator.finalproject.repository.AdminRepository;
import com.examcreator.finalproject.service.base.BaseEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseEntityServiceImpl<Admin,Long, AdminRepository> implements AdminService {
    public AdminServiceImpl(@Autowired AdminRepository adminRepository,@Autowired ApplicationContext context) {
        super(adminRepository, context);
    }
}
