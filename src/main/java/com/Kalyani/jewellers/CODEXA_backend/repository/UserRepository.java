package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String username);
    Optional<User> findByUserId(Integer userId);
    List<User> getUsersByUserId(Integer id);
}
