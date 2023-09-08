package com.erigateway.registration.entity.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long UserId;

    private String firstName;

    private String lastName;


    @Column(name = "Date_of_Birth")
    private String dob;
    private String gender;

    private String country;
    private String address;
    private Long phoneNumber;
    private String Username;
    private String email;

}
