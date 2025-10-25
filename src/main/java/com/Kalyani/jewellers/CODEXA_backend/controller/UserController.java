package com.Kalyani.jewellers.CODEXA_backend.controller;

import com.Kalyani.jewellers.CODEXA_backend.dto.LoginDTO;
import com.Kalyani.jewellers.CODEXA_backend.dto.RegisterDTO;
import com.Kalyani.jewellers.CODEXA_backend.model.User;
import com.Kalyani.jewellers.CODEXA_backend.model.UserPrincipal;
import com.Kalyani.jewellers.CODEXA_backend.response.LoginResponse;
import com.Kalyani.jewellers.CODEXA_backend.service.AuthService;
import com.Kalyani.jewellers.CODEXA_backend.service.JWTService;
import com.Kalyani.jewellers.CODEXA_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthService authService;

    /**
     * Registers a new user.
     * Encrypts the password using BCrypt before storing it in the database.
     * @param registerDTO A DTO containing user registration details.
     * @return A response indicating successful registration.
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) {
        User registeredSeller = authService.signup(registerDTO);
        return ResponseEntity.ok(registeredSeller);
    }

    /**
     * Authenticates a user during login.
     * Generates a JWT token if credentials are correct.
     * @param loginUserDto DTO containing login credentials
     * @return ResponseEntity with JWT token, user ID, roleId, roleName, userFname, userLname
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginUserDto) {
        UserPrincipal userPrincipal = authService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(userPrincipal.getUsername());
        Integer userId = userPrincipal.getUserId();
        Integer roleId = userPrincipal.getRoleId();
        String roleName = userPrincipal.getRoleName();
        String userFname = userPrincipal.getUserFname();
        String userLname = userPrincipal.getUserLname();

        // Return token and expiration
        return ResponseEntity.ok(new LoginResponse(jwtToken, userId, roleId, roleName, userFname, userLname));
    }

    /**
     * Retrieves all users from the database.
     * @return List of User objects
     */
    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userService.getUsers();
    }


    /**
     * Retrieves a user by ID.
     * @param id User ID to fetch
     * @return List containing the user with the specified ID
     */
    @GetMapping("/get-users/{id}")
    public List<User> getUsers(@PathVariable Integer id) {
        return userService.getUsersById(id);
    }

    /**
     * Updates a user's details.
     * @param id ID of the user to update
     * @param user User object with updated details
     * @return Updated User object
     */
    @PutMapping("/update-user/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /**
     * Deletes a user by ID.
     * @param id ID of the user to delete
     * @return Confirmation message
     */
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "User deleted with id: " + id;
    }
}
