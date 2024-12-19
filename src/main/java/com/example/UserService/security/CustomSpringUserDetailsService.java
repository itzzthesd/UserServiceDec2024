package com.example.UserService.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.UserService.models.User;
import com.example.UserService.repositories.UserRepository;

@Service
public class CustomSpringUserDetailsService implements UserDetailsService{
    private UserRepository userRepository;

    public CustomSpringUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional == null){
            throw new UsernameNotFoundException("User not found.");
        }

        User user = userOptional.get();
        return new CustomSpringUserDetails(user);
    }
    
}
