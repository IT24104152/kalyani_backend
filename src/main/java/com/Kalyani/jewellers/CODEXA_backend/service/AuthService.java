package com.Kalyani.jewellers.CODEXA_backend.service;

import com.Kalyani.jewellers.CODEXA_backend.dto.LoginDTO;
import com.Kalyani.jewellers.CODEXA_backend.dto.RegisterDTO;
import com.Kalyani.jewellers.CODEXA_backend.model.User;
import com.Kalyani.jewellers.CODEXA_backend.model.UserPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserPrincipal authenticate(LoginDTO input);
    User signup(RegisterDTO input);
}
