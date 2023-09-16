package com.erigateway.registration.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDTO {

    private String email ;
    private String password ;

    private  String phoneNumber ;
}
