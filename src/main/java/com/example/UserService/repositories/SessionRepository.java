package com.example.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UserService.models.Session;
import java.util.List;
import java.util.Optional;


public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
} 
