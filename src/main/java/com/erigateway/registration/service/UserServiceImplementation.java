package com.erigateway.registration.service;

import com.erigateway.registration.entity.User;
import com.erigateway.registration.entity.dto.UserCreditionalDto;
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
        System.out.println(userCreditionalDto.toString());
        return userRepository.save(modelMapper.map(userCreditionalDto, User.class));
    }

//    @Override
//    public User registeredUser(UserProfileDto userProfileDto, String email ) {
////        ensureEmailIsUnique(userProfileDto.getEmail());
//        userProfileDto.setFirstName(userProfileDto.getFirstName());
//        userProfileDto.setLastName(userProfileDto.getLastName());
//        userProfileDto.setDob(userProfileDto.getDob());
//        userProfileDto.setGender(userProfileDto.getGender());
//        userProfileDto.setCountry(userProfileDto.getCountry());
//        userProfileDto.setAddress(userProfileDto.getAddress());
//        userProfileDto.setPhoneNumber(userProfileDto.getPhoneNumber());
//        return userRepository.save(modelMapper.map(userProfileDto, User.class));
//    }

    public void ensureEmailIsUnique(String email) {
        if(this.isEmailExist(email)) throw new ResourceNotFound("Email already used!");
    }
    @Override
    public boolean isEmailExist(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        return user != null;
    }
}
