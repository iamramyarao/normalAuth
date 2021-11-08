package com.example.samplelogin.service;


import com.example.samplelogin.model.CustomUserDetail;
import com.example.samplelogin.model.User;
import com.example.samplelogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Ramya Rao on 08-11-2021 18:05
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    public static String display="";
    //Fetch the user from repository
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        display = email;
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User is not available"));
        return user.map(CustomUserDetail::new).get();
    }
}
