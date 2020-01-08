package io.lsa.boot.security.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    //this method will connect to database and get username/hashed password
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("luannh", "luannh", new ArrayList<>());
    }
}
