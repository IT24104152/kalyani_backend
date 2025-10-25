package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.model.Role;
import com.Kalyani.jewellers.CODEXA_backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * Retrieves all roles from the database.
     * @return List of Role objects
     */
    @GetMapping("/get-role")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    /**
     * Creates a new role.
     * @param role Role object containing role details
     * @return The saved Role object
     */
    @PostMapping("/post-role")
    public Role postRole(@RequestBody Role role) {
        return roleService.postRole(role);
    }

    /**
     * Deletes a role by ID.
     * @param id ID of the role to delete
     * @return Confirmation message
     */
    @DeleteMapping("/delete-role/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return "Role has been deleted";
    }

    /**
     * Updates a role's details by ID.
     * @param id ID of the role to update
     * @param role Role object containing updated details
     * @return Updated Role object
     */
    @PutMapping("/update-role/{id}")
    public Role updateRole(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }
}
