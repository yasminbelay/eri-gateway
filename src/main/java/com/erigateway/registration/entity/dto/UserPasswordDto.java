package com.erigateway.registration.entity.dto;


import lombok.Data;

@Data
public class UserPasswordDto {

    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
