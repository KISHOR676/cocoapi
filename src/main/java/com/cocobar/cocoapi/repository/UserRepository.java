package com.cocobar.cocoapi.repository;

import com.cocobar.cocoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Make sure this is imported

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByBusinessEmail(String businessEmail);
    boolean existsByMobileNumber(String mobileNumber);

    // NEW: Needed for Login to fetch the user's details and hashed password
    Optional<User> findByBusinessEmail(String businessEmail);
}