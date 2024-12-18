package com.example.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.models.Role;
import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long>{

    List<Role> findAllByIdIn(List<Long> ids);
} 
