package com.diploma.ekg.service.impl;

import com.diploma.ekg.entity.User;
import com.diploma.ekg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userService.findUser(mail).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User wasn't found");
        }
        return buildUserFromEntity(user);
    }

    private org.springframework.security.core.userdetails.UserDetails buildUserFromEntity(User user) {
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder
                = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder();
        userBuilder.username(user.getEmail());
        userBuilder.password(user.getPassword());
        userBuilder.authorities("USER");
        return userBuilder.build();
    }
}
