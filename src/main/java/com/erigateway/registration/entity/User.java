package com.erigateway.registration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long UserId;
    private String Username;
    private String firstName;
    private String lastName;


    @Column(name = "Date_of_Birth")
    private String dob;
    private String gender;

    private String country;
    private String address;
    private Long phoneNumber;
    private String email;
    private String password;
}
