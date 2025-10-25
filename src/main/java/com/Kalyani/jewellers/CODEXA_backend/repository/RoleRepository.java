package com.Kalyani.jewellers.CODEXA_backend.repository;

import com.Kalyani.jewellers.CODEXA_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    void deleteByRoleId(Integer id);
    Optional<Role> findRoleByRoleId(Integer id);
}
