package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.DTOEntities.UserInformationDTO;
import com.examcreator.finalproject.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final ApplicationContext context;
    private final UserService userService;

    public RegistrationController(ApplicationContext context, UserService userService) {
        this.context = context;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserInformationDTO userRegistrationDTO() {
        return context.getBean(UserInformationDTO.class);
    }

    @GetMapping
    public String showRegisterPage(Model model) {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserInformationDTO userDTO) {
        boolean result = userService.registerUser(userDTO);
        return result ? "register" : "register";
    }
}
