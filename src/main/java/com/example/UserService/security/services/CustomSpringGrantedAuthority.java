package com.example.UserService.security.services;

import org.springframework.security.core.GrantedAuthority;

import com.example.UserService.models.Role;

public class CustomSpringGrantedAuthority implements GrantedAuthority{

    private Role role;

    private CustomSpringGrantedAuthority(Role role){
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getRole();
    }
    
}
