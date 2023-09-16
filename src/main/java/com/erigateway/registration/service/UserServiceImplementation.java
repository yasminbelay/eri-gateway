package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.*;
import com.erigateway.registration.exception.ResourceNotFound;
import com.erigateway.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation  implements UserService{


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImplementation.class);

//    @Override
//    public User saveUser(UserCreditionalDto userCreditionalDto) {
//        ensureEmailIsUnique(userCreditionalDto.getEmail());
//        userCreditionalDto.setEmail(userCreditionalDto.getEmail());
//        userCreditionalDto.setFirstName(userCreditionalDto.getFirstName());
//        userCreditionalDto.setLastName(userCreditionalDto.getLastName());
//        userCreditionalDto.setPassword(userCreditionalDto.getPassword());
//       //  System.out.println(userCreditionalDto.toString());
//        return userRepository.save(modelMapper.map(userCreditionalDto, User.class));
//    }

    @Override
    public User saveUser(UserCreditionalDto userCreditionalDto) {
        String email = userCreditionalDto.getEmail();

        log.info("Saving new user with email: {}", email);
        ensureEmailIsUnique(email);
        User newUser = modelMapper.map(userCreditionalDto, User.class);
        log.info("User saved successfully with email: {}", email);
        return userRepository.save(newUser);
    }

    private void ensureEmailIsUnique(String email) {
        if(this.isEmailExist(email)) throw new ResourceNotFound("Email already used!");
    }


    @Override
    public User updatedUserProfile(UserProfileDto userProfileDto) {
//        ensureEmailIsUnique(userProfileDto.getEmail());
        User userInDB = userRepository.findUserByEmail(userProfileDto.getEmail());

        userInDB.setFirstName(userProfileDto.getFirstName());
        userInDB.setLastName(userProfileDto.getLastName());
        userInDB.setUsername(userProfileDto.getUsername());
        userInDB.setDob(userProfileDto.getDob());
        userInDB.setGender(userProfileDto.getGender());
        userInDB.setCountry(userProfileDto.getCountry());
        userInDB.setAddress(userProfileDto.getAddress());
        userInDB.setPhoneNumber(userProfileDto.getPhoneNumber());


//        return userRepository.save(modelMapper.map(userProfileDto, User.class));
        return userRepository.save(userInDB);
    }

    @Override
    public void changePassword(UserPasswordDto userPasswordDto) {
        User userPasswordInDB = userRepository.findUserByEmail(userPasswordDto.getEmail());

        if(!userPasswordDto.getOldPassword().equals(userPasswordInDB.getPassword()))
        {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        if (!userPasswordDto.getNewPassword().equals(userPasswordDto.getConfirmNewPassword())) {
            throw new IllegalArgumentException("New password and confirm password do not match");
        }

        userPasswordInDB.setPassword(userPasswordDto.getNewPassword());
        userRepository.save(userPasswordInDB);
    }


    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }


    @Override
    public LoginResponse login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        LOG.info("Attempting login for user with email: {}", email);

        try {
            User userInDB = userRepository.findUserByEmail(email);
//                    .orElseThrow(() -> new ResourceNotFound("User not found"));

            LOG.info("User found with email: {}", email);

            if (password.equals(userInDB.getPassword())) {
                LOG.info("Login successful for user with email: {}", email);
                return new LoginResponse("Login successful");
            } else {
                LOG.warn("Login failed for user with email: {}", email);
                return new LoginResponse("Password is incorrect");
            }
        } catch (ResourceNotFound e) {
            LOG.warn("Login failed for user with email: {}", email);
            return new LoginResponse("Email is incorrect");
        } catch (Exception e) {
            LOG.error("Error during login for user with email: {}", email, e);
            throw e;
        }
    }

//    public LoginResponse resetPassword(PasswordReset passwordReset) throws Exception {
//
//        String email  = passwordReset.getEmail()  ;
//
//        String password = passwordReset.getPassword() ;
//
//        Optional<User> userInDB = userRepository.findUserByEmail(email)  ;
//
//        if (userInDB.isPresent()){
//            User user  = userInDB.get() ;
//            System.out.println(user);
//
//            user.setEmail(passwordReset.getEmail());
//
//            user.setPassword(passwordReset.getPassword());
//
//            return new LoginResponse("Success") ;
//        }
//
//        else {
//            throw  new Exception("User doesnt exists") ;
//        }
//    }

    public void resetPassword(PasswordResetDTO passwordResetDTO) throws Exception {

        String email = passwordResetDTO.getEmail();
        String newPassword = passwordResetDTO.getPassword();
        String phoneNumber = passwordResetDTO.getPhoneNumber();
        LOG.info("Attempting to reset password  for user with email: {}", email);
        LOG.info("Attempting to reset for user with phone Number : {}", phoneNumber);


        User userByEmail = userRepository.findUserByEmail(email);
        User userByPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);


        if (userByEmail != null && userByPhoneNumber != null) {
            LOG.info("User: {}", userByEmail);

            // Update the user's password with the new password
            userByEmail.setPassword(newPassword);
            userRepository.save(userByEmail);
            LOG.info("Log in successful for user {}" , userByEmail.getEmail());
        } else {
            throw new Exception("User doesn't exist");

        }
    }


}
