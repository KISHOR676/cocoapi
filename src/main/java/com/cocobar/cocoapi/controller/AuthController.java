package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.dto.LoginRequest;
import com.cocobar.cocoapi.dto.RegistrationRequest;
import com.cocobar.cocoapi.model.User;
import com.cocobar.cocoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {
        try {
            User registeredUser = userService.registerUser(request);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // NEW: Login API Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            User loggedInUser = userService.loginUser(request);
            return ResponseEntity.ok(loggedInUser); // 200 OK
        } catch (Exception e) {
            // 401 Unauthorized for bad credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}