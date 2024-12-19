package com.example.UserService.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.UserService.models.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomSpringUserDetails implements UserDetails{

    private User user;

    public CustomSpringUserDetails(User user){
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;
    }

    @Override
    public String getPassword() {
       return this.user.getPassword();
    }

    @Override
    public String getUsername() {
       
        return this.user.getEmail();
    }

}
