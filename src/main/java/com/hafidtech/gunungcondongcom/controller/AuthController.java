package com.hafidtech.gunungcondongcom.controller;


import com.hafidtech.gunungcondongcom.exception.ApiGunungCondongException;
import com.hafidtech.gunungcondongcom.exception.AppException;
import com.hafidtech.gunungcondongcom.model.role.Role;
import com.hafidtech.gunungcondongcom.model.role.RoleName;
import com.hafidtech.gunungcondongcom.model.user.User;
import com.hafidtech.gunungcondongcom.payload.ApiResponse;
import com.hafidtech.gunungcondongcom.payload.JwtAuthenticationResponse;
import com.hafidtech.gunungcondongcom.payload.LoginRequest;
import com.hafidtech.gunungcondongcom.payload.SignUpRequest;
import com.hafidtech.gunungcondongcom.repository.RoleRepository;
import com.hafidtech.gunungcondongcom.repository.UserRepository;
import com.hafidtech.gunungcondongcom.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String USER_ROLE_NOT_SET = "User role not set";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
            throw new ApiGunungCondongException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new ApiGunungCondongException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        String firstName = signUpRequest.getFirstName().toLowerCase();
        String lastName = signUpRequest.getLastName().toLowerCase();
        String username = signUpRequest.getUsername().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        User user = new User(firstName, lastName, username, email, password);
        List<Role> roles = new ArrayList<>();

        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
            roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        } else {
            roles.add(roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }

        user.setRoles(roles);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
    }
}
