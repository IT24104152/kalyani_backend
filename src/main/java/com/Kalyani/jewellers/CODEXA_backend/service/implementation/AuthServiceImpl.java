package com.Kalyani.jewellers.CODEXA_backend.service.implementation;

import com.Kalyani.jewellers.CODEXA_backend.dto.LoginDTO;
import com.Kalyani.jewellers.CODEXA_backend.dto.RegisterDTO;
import com.Kalyani.jewellers.CODEXA_backend.exception.InvalidPasswordException;
import com.Kalyani.jewellers.CODEXA_backend.exception.UnverifiedAccountException;
import com.Kalyani.jewellers.CODEXA_backend.exception.UserNotFoundException;
import com.Kalyani.jewellers.CODEXA_backend.model.Role;
import com.Kalyani.jewellers.CODEXA_backend.model.User;
import com.Kalyani.jewellers.CODEXA_backend.model.UserPrincipal;
import com.Kalyani.jewellers.CODEXA_backend.repository.RoleRepository;
import com.Kalyani.jewellers.CODEXA_backend.repository.UserRepository;
import com.Kalyani.jewellers.CODEXA_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    //Authenticate the user when loggin in
    public UserPrincipal authenticate(LoginDTO input) {
        User seller = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!seller.getActive())
            throw new UnverifiedAccountException("Account not verified. Please verify your account.");


        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPasswordHash())
            );
            // If we reach here, authentication succeeded
            return (UserPrincipal) auth.getPrincipal();
        } catch (BadCredentialsException ex) {
            throw new InvalidPasswordException("Password is incorrect");
          }
    }

    //signing up the user
    @Override
    public User signup(RegisterDTO input) {
        Role role = roleRepository.findById(input.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

        User user = new User();
        user.setUserFname(input.getFirstName());
        user.setUserLname(input.getLastName());
        user.setEmail(input.getEmail());
        user.setRole(role);
        user.setPasswordHash(passwordEncoder.encode(input.getPassword()));
        if("admin".equalsIgnoreCase(user.getRole().getRoleName())){
            user.setActive(true);
        }
        else{
            user.setActive(false);
        }
        userRepository.save(user);

        return user;
    }

    private boolean isSellerEnabled(User user) {
        // You need an 'enabled' field in Seller entity
        return user.getActive();
    }

}
