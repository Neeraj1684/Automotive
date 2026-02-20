package com.neeraj.AutomotiveBackend.customer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/profile")
@RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService service;

    @GetMapping
    public CustomerProfile getMyProfile(){
        return service.getMyProfile();
    }

    public CustomerProfile updateProfile(@RequestBody CustomerProfile profile){
        return service.updateProfile(profile);
    }

}
