package com.neeraj.AutomotiveBackend.auth;

import com.neeraj.AutomotiveBackend.config.JwtService;
import com.neeraj.AutomotiveBackend.customer.CustomerProfile;
import com.neeraj.AutomotiveBackend.customer.CustomerProfileRepository;
import com.neeraj.AutomotiveBackend.dto.AuthResponse;
import com.neeraj.AutomotiveBackend.dto.LoginRequest;
import com.neeraj.AutomotiveBackend.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        com.neeraj.AutomotiveBackend.auth.User userEntity =
                com.neeraj.AutomotiveBackend.auth.User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.CUSTOMER)
                        .build();

        com.neeraj.AutomotiveBackend.auth.User savedUser = userRepository.save(userEntity);

        if(savedUser.getRole() == Role.CUSTOMER){
            CustomerProfile profile = CustomerProfile.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .phone(request.getPhone())
                    .user(savedUser)
                    .build();

            customerProfileRepository.save(profile);
        }

        UserDetails userDetails = User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        com.neeraj.AutomotiveBackend.auth.User userEntity =
                userRepository.findByEmail(request.getEmail())
                        .orElseThrow();

        UserDetails userDetails = User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
