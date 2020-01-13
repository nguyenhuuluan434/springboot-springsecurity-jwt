package io.lsa.boot.security.jwt.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    //this method will connect to database and get username/hashed password
    private final static Map<String, String> fakeUser = new HashMap<String, String>() {
        {
            put("luannh", "luannh");
            put("test", "test");
        }
    };

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String pass = fakeUser.get(s);
        if (Objects.isNull(pass))
            throw new UsernameNotFoundException("wrong user name");
        return new User(s, pass, new ArrayList<>());
    }
}
