package com.erigateway.registration.controller;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.UserCreditionalDto;
import com.erigateway.registration.entity.dto.UserPasswordDto;
import com.erigateway.registration.entity.dto.UserProfileDto;
import com.erigateway.registration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserCreditionalDto userCreditionalDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userCreditionalDto));
    }

    @PutMapping("/updatedUserProfile")
    public ResponseEntity<User> updatedUserProfile(@RequestBody UserProfileDto userProfileDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updatedUserProfile(userProfileDto));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserPasswordDto userPasswordDto) {
        userService.changePassword(userPasswordDto);
        return ResponseEntity.ok("Password changed successfully.");
    }
}
