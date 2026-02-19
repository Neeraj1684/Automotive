package com.neeraj.AutomotiveBackend.customer;

import com.neeraj.AutomotiveBackend.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;

    private CustomerProfile getCurrentProfile(){

    }


}
