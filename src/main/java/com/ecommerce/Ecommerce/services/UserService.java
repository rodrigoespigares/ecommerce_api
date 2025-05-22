package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    public IUserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
