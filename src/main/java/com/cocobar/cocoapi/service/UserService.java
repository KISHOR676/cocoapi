package com.cocobar.cocoapi.service;

import com.cocobar.cocoapi.dto.ChangePasswordDTO;
import com.cocobar.cocoapi.dto.LoginRequest;
import com.cocobar.cocoapi.dto.RegistrationRequest;
import com.cocobar.cocoapi.model.User;
import com.cocobar.cocoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User registerUser(RegistrationRequest request) throws Exception {
        if (userRepository.existsByBusinessEmail(request.getBusinessEmail())) {
            throw new Exception("Email already registered!");
        }
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new Exception("Mobile number already registered!");
        }

        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setMobileNumber(request.getMobileNumber());
        newUser.setBusinessEmail(request.getBusinessEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

    // NEW: Login Logic
    public User loginUser(LoginRequest request) throws Exception {
        // 1. Find the user by email
        User user = userRepository.findByBusinessEmail(request.getBusinessEmail())
                .orElseThrow(() -> new Exception("Invalid email or password"));
        // We keep the error generic for security reasons

        // 2. Check if the password matches the hashed password in DB
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid email or password");
        }

        // 3. If everything is correct, return the user
        return user;
    }

    // Add this inside UserService.java
    public void changePassword(ChangePasswordDTO request) throws Exception {
        // 1. Find the user by username (You likely already have a findByUsername method from login)
        User user = userRepository.findByBusinessEmail(request.getBusinessEmail())
                .orElseThrow(() -> new Exception("User not found"));

        // 2. Verify the old password matches the database
        // Note: If you are using BCrypt password encoding, use passwordEncoder.matches() instead of .equals()
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new Exception("Incorrect current password");
        }

        // 3. Update the password and save
        // Note: If using BCrypt, encode the new password here before saving
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
}