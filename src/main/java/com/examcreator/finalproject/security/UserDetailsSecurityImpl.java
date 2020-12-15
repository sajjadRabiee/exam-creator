package com.examcreator.finalproject.security;

import com.examcreator.finalproject.entities.classEntities.User;
import com.examcreator.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsSecurityImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsSecurityImpl(@Autowired UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> oUser = userService.loginUser(username);
        if (oUser.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserDetailsSecurity(oUser.get());
    }
}
