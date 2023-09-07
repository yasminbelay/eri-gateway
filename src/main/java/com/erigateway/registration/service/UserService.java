package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.UserCreditionalDto;
import com.erigateway.registration.entity.dto.UserProfileDto;

public interface UserService {

    User saveUser(UserCreditionalDto userCreditionalDto);
    User updatedUserProfile(UserProfileDto userProfileDto);

    boolean isEmailExist (String email);

}
