package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.UserCreditionalDto;
import com.erigateway.registration.entity.dto.UserPasswordDto;
import com.erigateway.registration.entity.dto.UserProfileDto;
import com.erigateway.registration.exception.ResourceNotFound;
import com.erigateway.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation  implements UserService{


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public User saveUser(UserCreditionalDto userCreditionalDto) {
        ensureEmailIsUnique(userCreditionalDto.getEmail());
        userCreditionalDto.setEmail(userCreditionalDto.getEmail());
        userCreditionalDto.setFirstName(userCreditionalDto.getFirstName());
        userCreditionalDto.setLastName(userCreditionalDto.getLastName());
        userCreditionalDto.setPassword(userCreditionalDto.getPassword());
//        System.out.println(userCreditionalDto.toString());
        return userRepository.save(modelMapper.map(userCreditionalDto, User.class));
    }

    @Override
    public User updatedUserProfile(UserProfileDto userProfileDto) {
//        ensureEmailIsUnique(userProfileDto.getEmail());
        User userInDB = userRepository.findUserByEmail(userProfileDto.getEmail()).get();

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
        User userPasswordInDB = userRepository.findUserByEmail(userPasswordDto.getEmail()).get();

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

    public void ensureEmailIsUnique(String email) {
        if(this.isEmailExist(email)) throw new ResourceNotFound("Email already used!");
    }
    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        return user != null;
    }
}
