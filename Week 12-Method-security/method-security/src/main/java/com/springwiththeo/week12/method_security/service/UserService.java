package com.springwiththeo.week12.method_security.service;

import com.springwiththeo.week12.method_security.repository.User;
import com.springwiththeo.week12.method_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

}
