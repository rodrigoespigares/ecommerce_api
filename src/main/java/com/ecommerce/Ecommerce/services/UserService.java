package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.dto.ChangePasswordRequest;
import com.ecommerce.Ecommerce.dto.PorfileRequest;
import com.ecommerce.Ecommerce.models.User;
import com.ecommerce.Ecommerce.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public IUserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<PorfileRequest> findUserById(Long id) {
        return userRepository.findById(id).map(User::toUserDto);
    }
    
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
