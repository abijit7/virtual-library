package com.Library.security.config.auth;

import com.Library.User.User;
import com.Library.User.UserRepo;
import com.Library.User.UserRole;
import com.Library.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager ;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
var user = User.builder()
        .firstName(registerRequest.getFirstName())
        .lastName(registerRequest.getLastName())
        .email(registerRequest.getEmail())
        .password(passwordEncoder.encode(registerRequest.getPassword()))
        .role(UserRole.USER)
        .build();
userRepo.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        )
);
var user  = this.userRepo.findByEmail(authenticationRequest.getEmail());
        var jwt = jwtService.generateToken(user);
return AuthenticationResponse.builder()
                .token(jwt).build();
    }
}
