package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.classEntities.Role;
import com.examcreator.finalproject.entities.classEntities.User;
import com.examcreator.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private final UserService userService;


    public AdminController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "User/Admin/dashboard";
    }



    @GetMapping("/show-all-users")
    public String showAllUsers(@PageableDefault(size = 5) Pageable pageable , Model model ,@Param("keyword") String keyword){
        Page<User> page = userService.searchAllUser(keyword,pageable);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page",page);
        return "User/Admin/show-all-users";
    }

    @GetMapping("/change-role/{id}/{roleName}")
    public String changeUserRole(@PathVariable Long id, @PathVariable String roleName) {
        Role role = null;
        if (roleName.equals("student")) {
            role = Role.ROLE_STUDENT;
        } else if (roleName.equals("teacher")) {
            role = Role.ROLE_TEACHER;
        }
        userService.changeTheRole(id, role);
        return "redirect:/admin/show-all-users";
    }

    @GetMapping("/change-situation/{id}")
    public String changeSituationOfUser(@PathVariable Long id) {
        userService.changeSituationTheUser(id);
        return "redirect:/admin/show-all-users";
    }


}
