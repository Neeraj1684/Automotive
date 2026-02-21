package com.neeraj.AutomotiveBackend.appointments;


import com.neeraj.AutomotiveBackend.auth.User;
import com.neeraj.AutomotiveBackend.auth.UserRepository;
import com.neeraj.AutomotiveBackend.customer.CustomerProfile;
import com.neeraj.AutomotiveBackend.customer.CustomerProfileRepository;
import com.neeraj.AutomotiveBackend.dto.AppointmentRequest;
import com.neeraj.AutomotiveBackend.vehicle.Vehicle;
import com.neeraj.AutomotiveBackend.vehicle.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final AppointmentRepository appointmentRepository;

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

    public Appointment bookAppointment(AppointmentRequest request){
        CustomerProfile profile = getCurrentProfile();

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(()->new RuntimeException("Vehicle not found"));

        if(!vehicle.getCustomer().getId().equals(profile.getId())){
            throw new RuntimeException("This vehicle does not belong to you");
        }

        Appointment appointment = Appointment.builder()
                .appointmentDate(request.getAppointmentDate())
                .status(AppointmentStatus.BOOKED)
                .vehicle(vehicle)
                .customer(profile)
                .build();

        return appointmentRepository.save(appointment);

    }

    public List<Appointment> getMyAppointments(){
        CustomerProfile profile = getCurrentProfile();
        return appointmentRepository.findByCustomerId(profile.getId());
    }
}
