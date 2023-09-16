package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.*;

public interface UserService {

    User saveUser(UserCreditionalDto userCreditionalDto);
    User updatedUserProfile(UserProfileDto userProfileDto);

    void changePassword(UserPasswordDto userPasswordDto);

    boolean isEmailExist (String email);

    LoginResponse login(UserLoginDto userLoginDto);
    public void resetPassword(PasswordResetDTO passwordResetDTO) throws Exception;
    LogOutResponse logOut();

}
