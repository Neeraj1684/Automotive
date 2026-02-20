package com.neeraj.AutomotiveBackend.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {

    // this is used for fetching logged_in customer
    Optional<CustomerProfile> findByUserId(Long userId);
}
