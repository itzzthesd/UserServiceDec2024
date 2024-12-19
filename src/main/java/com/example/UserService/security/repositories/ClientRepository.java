package com.example.UserService.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UserService.security.models.Client;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{
    Optional<Client> findByClientId(String clientId);
} 