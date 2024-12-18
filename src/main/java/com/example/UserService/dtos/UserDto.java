package com.example.UserService.dtos;

import java.util.HashSet;
import java.util.Set;

import com.example.UserService.models.Role;
import com.example.UserService.models.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {
     private String email;
    private Set<Role> roles = new HashSet<>();

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}