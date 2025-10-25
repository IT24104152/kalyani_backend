package com.Kalyani.jewellers.CODEXA_backend.service.implementation;

import com.Kalyani.jewellers.CODEXA_backend.model.User;
import com.Kalyani.jewellers.CODEXA_backend.repository.UserRepository;
import com.Kalyani.jewellers.CODEXA_backend.service.JWTService;
import com.Kalyani.jewellers.CODEXA_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    //get all users
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //get user by id
    @Override
    public List<User> getUsersById(Integer id) {
        return userRepository.getUsersByUserId(id);
    }

    //update the user by id
    @Override
    public User updateUser(Integer id, User user) {
        User existingUser = userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setPasswordHash(encoder.encode(user.getPasswordHash()));
        existingUser.setRole(user.getRole());
        existingUser.setUserFname(user.getUserFname());
        existingUser.setUserLname(user.getUserLname());
        existingUser.setEmail(user.getEmail());
        existingUser.setActive(user.getActive());
        return userRepository.save(existingUser);
    }

    //delete the user by id
    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
