package com.erigateway.registration.controller;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.*;
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
    public ResponseEntity<User> registerUser(@RequestBody UserCreditionalDto userCreditionalDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userCreditionalDto));
    }

    @PutMapping("/updatedUserProfile")
    public ResponseEntity<User> updatedUserProfile(@RequestBody UserProfileDto userProfileDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updatedUserProfile(userProfileDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userLoginDto));
    }


    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserPasswordDto userPasswordDto) {
        userService.changePassword(userPasswordDto);
        return ResponseEntity.ok("Password changed successfully.");
    }



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getUserByEmail() {
      return  "Hello World" ;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDTO passwordResetDTO) {
        try {
            userService.resetPassword(passwordResetDTO);
            return ResponseEntity.ok("Password reset successful.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
