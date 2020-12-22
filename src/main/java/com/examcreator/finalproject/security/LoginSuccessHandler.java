package com.examcreator.finalproject.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasRoleAdmin = false;
        boolean hasRoleTeacher = false;
        boolean hasRoleStudent = false;
        for (GrantedAuthority userAuthentication : authorities) {
            if (userAuthentication.getAuthority().equals("ROLE_ADMIN")) {
                hasRoleAdmin = true;
            } else if(userAuthentication.getAuthority().equals("ROLE_TEACHER")){
                hasRoleTeacher = true;
            } else if(userAuthentication.getAuthority().equals("ROLE_STUDENT")){
                hasRoleStudent = true;
            }
        }
        if(hasRoleAdmin){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/admin/dashboard");
        }else if(hasRoleTeacher){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/teacher/dashboard");
        }else if(hasRoleStudent){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/student/dashboard");
        }
    }
}
