package com.backauth.aplicacion;

import com.backauth.core.UserRepository;
import com.backauth.core.dominio.AuthResponse;
import com.backauth.core.dominio.LoginRequest;
import com.backauth.core.dominio.RegisterRequest;
import com.backauth.core.dominio.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword()));
        User user= userRepository.getUser(request.getUserEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request)
    {
        User user = User.builder()
                .userId(request.getUserId())
                .userIdTipe(request.getUserIdTipe())
                .userName(request.getUserName())
                .userLastname(request.getUserLastname())
                .userPhoneNumber(request.getUserPhoneNumber())
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userRole(request.getRoleId())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}

