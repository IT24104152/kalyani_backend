package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    List<User> getUsersById(Integer id);
    User updateUser(Integer id, User user);
    void deleteUser(Integer id);
}
