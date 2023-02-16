package com.myhotel.carsolution.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService{

    private @Value("${application.user}") String user;
    private @Value("${application.pass}") String pass;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return  new User(user,pass,new ArrayList<>());
    }
}
