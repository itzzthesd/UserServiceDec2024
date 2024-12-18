package com.example.UserService.services;

import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import com.example.UserService.dtos.UserDto;
import com.example.UserService.models.Session;
import com.example.UserService.models.SessionStatus;
import com.example.UserService.models.User;
import com.example.UserService.repositories.SessionRepository;
import com.example.UserService.repositories.UserRepository;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository
                        , BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto signUp(String email, String password){
        User user = new User();

        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);

    }

    public ResponseEntity<UserDto> logIn(String email, String password){
        Optional<User> optUser = userRepository.findByEmail(email);
        if(optUser.isEmpty()){
            return null;
        }
        User user = optUser.get();

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            return null;
        }

        String token = RandomStringUtils.randomAlphanumeric(30);

        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);

        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth_token: " + token );

        UserDto userDto = user.from(user);

        ResponseEntity<UserDto> res = new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        return res;
    }

    public SessionStatus validate(String token, Long userId){
        Optional<Session> sessOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if(sessOptional.isEmpty()){
            return null;
        }

        return sessOptional.get().getSessionStatus();
    }
}
