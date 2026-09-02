package com.cocobar.cocoapi.controller;

import com.cocobar.cocoapi.dto.ChangePasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Note: Inject your UserRepository/Service here to actually update the database.
    // This is the controller skeleton to receive the request.

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO request) {
        // TODO: Find user by request.getUsername()
        // TODO: Verify if request.getOldPassword() matches the database
        // TODO: Save request.getNewPassword() to database

        // Simulating a successful password change for now:
        return ResponseEntity.ok("Password updated successfully");
    }
}