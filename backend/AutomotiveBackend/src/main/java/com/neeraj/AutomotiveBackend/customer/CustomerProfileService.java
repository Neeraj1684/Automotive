package com.neeraj.AutomotiveBackend.customer;

import com.neeraj.AutomotiveBackend.auth.User;
import com.neeraj.AutomotiveBackend.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;

    private CustomerProfile getCurrentProfile(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return customerProfileRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public CustomerProfile getMyProfile(){
        return getCurrentProfile();
    }

    public CustomerProfile updateProfile(CustomerProfile updated){
        CustomerProfile profile = getCurrentProfile();

        profile.setFirstName(updated.getFirstName());
        profile.setLastName(updated.getLastName());
        profile.setPhone(updated.getPhone());

        return customerProfileRepository.save(profile);
    }


}
