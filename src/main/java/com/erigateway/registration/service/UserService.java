package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.UserCreditionalDto;
import com.erigateway.registration.entity.dto.UserPasswordDto;
import com.erigateway.registration.entity.dto.UserProfileDto;

public interface UserService {

    User saveUser(UserCreditionalDto userCreditionalDto);
    User updatedUserProfile(UserProfileDto userProfileDto);

    void changePassword(UserPasswordDto userPasswordDto);

    boolean isEmailExist (String email);

}
