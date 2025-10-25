package com.Kalyani.jewellers.CODEXA_backend.service.implementation;

import com.Kalyani.jewellers.CODEXA_backend.model.Role;
import com.Kalyani.jewellers.CODEXA_backend.repository.RoleRepository;
import com.Kalyani.jewellers.CODEXA_backend.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    //get all the roles
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    //create a new role
    @Override
    public Role postRole(Role role) {
        return roleRepository.save(role);
    }

    //delete the user role by id
    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleRepository.deleteByRoleId(id);
    }

    //update the role of the user by id
    @Override
    public Role updateRole(Integer id, Role role) {
        Role existingRole=roleRepository.findRoleByRoleId(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        existingRole.setRoleName(role.getRoleName());
        return roleRepository.save(existingRole);
    }
}
