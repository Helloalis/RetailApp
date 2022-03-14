package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.NoSuchUserException;
import com.cognixia.jump.model.CustomUserDetails;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
     
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.get() == null) {
            throw new UsernameNotFoundException("Could not find user with that email");
        }
         
        return new CustomUserDetails(user.get());
    }
}