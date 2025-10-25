package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> getRoles();
    Role postRole(Role role);
    void deleteRole(Integer id);
    Role updateRole(Integer id, Role role);
}
