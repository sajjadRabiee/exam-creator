package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.classEntities.Admin;
import com.examcreator.finalproject.entities.classEntities.Teacher;
import com.examcreator.finalproject.entities.classEntities.User;
import com.examcreator.finalproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/index")
    public String showMainPage(){
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(){ return "login";}
}
