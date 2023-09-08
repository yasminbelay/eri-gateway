package com.erigateway.registration.entity.dto;




/*
* will be changed to return Token when we implement security */


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    public  String token;
}
