package com.erigateway.registration.entity.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {

    private String email;
    private String password;

}

