package com.erigateway.registration.repository;

import com.erigateway.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = ?1")
 User findByPhoneNumber(String phoneNumber);

}
