package com.neeraj.AutomotiveBackend.vehicle;

import com.neeraj.AutomotiveBackend.auth.User;
import com.neeraj.AutomotiveBackend.auth.UserRepository;
import com.neeraj.AutomotiveBackend.customer.CustomerProfile;
import com.neeraj.AutomotiveBackend.customer.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;

    private CustomerProfile getCurrentProfile(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return customerProfileRepository.findByUserId(user.getId())
                .orElseThrow();
    }

    public Vehicle addVehicle(Vehicle vehicle){
        CustomerProfile profile = getCurrentProfile();
        vehicle.setCustomer(profile);

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getMyVehicles(){
        CustomerProfile profile = getCurrentProfile();
        return vehicleRepository.findByCustomerId(profile.getId());
    }

    public Vehicle updateVehicle(Vehicle updated){
        CustomerProfile profile = getCurrentProfile();

        Vehicle existing = vehicleRepository.findById(updated.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if(!existing.getCustomer().getId().equals(profile.getId())){
            throw new RuntimeException("You are not allowed to update this vehicle");
        }

        existing.setVehicleNumber(updated.getVehicleNumber());
        existing.setBrand(updated.getBrand());
        existing.setModel(updated.getModel());
        existing.setYear(updated.getYear());

        return vehicleRepository.save(existing);
    }

    public void deleteVehicle(Long id){
        vehicleRepository.deleteById(id);
    }
}
