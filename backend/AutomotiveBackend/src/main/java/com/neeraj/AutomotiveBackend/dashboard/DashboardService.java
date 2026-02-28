package com.neeraj.AutomotiveBackend.dashboard;

import com.neeraj.AutomotiveBackend.appointments.Appointment;
import com.neeraj.AutomotiveBackend.appointments.AppointmentRepository;
import com.neeraj.AutomotiveBackend.appointments.AppointmentStatus;
import com.neeraj.AutomotiveBackend.auth.User;
import com.neeraj.AutomotiveBackend.auth.UserRepository;
import com.neeraj.AutomotiveBackend.customer.CustomerProfile;
import com.neeraj.AutomotiveBackend.customer.CustomerProfileRepository;
import com.neeraj.AutomotiveBackend.vehicle.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final AppointmentRepository appointmentRepository;

    private CustomerProfile getCurrentProfile(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        return customerProfileRepository.findByUserId(user.getId()).orElseThrow();
    }

    public DashboardResponse getDashboard(){
        CustomerProfile profile = getCurrentProfile();

        Long customerId = profile.getId();

        long vehicleCount = vehicleRepository.countByCustomerId(customerId);

        long upcomingCount = appointmentRepository.countByCustomerIdAndStatusAndAppointmentDateAfter(
                customerId,
                AppointmentStatus.BOOKED,
                LocalDate.now()
        );

        Optional<Appointment> next = appointmentRepository.findTopByCustomerIdAndStatusAndAppointmentDateAfterOrderByAppointmentDateAsc(
                customerId,
                AppointmentStatus.BOOKED,
                LocalDate.now()
        );

        NextServiceDTO nextService = next.map(
                a->NextServiceDTO.builder()
                        .vehicleName(a.getVehicle().getModel())
                        .vehicleNumber(a.getVehicle().getVehicleNumber())
                        .appointmentDate(a.getAppointmentDate())
                        .build()
        ).orElse(null);

        List<Appointment> recent = appointmentRepository.findTop3ByCustomerIdAndStatusOrderByAppointmentDateDesc(
                customerId,
                AppointmentStatus.COMPLETED
        );

        List<RecentServiceDTO> recentList = recent.stream().map(
                a->RecentServiceDTO.builder()
                        .vehicleName(a.getVehicle().getModel())
                        .vehicleNumber(a.getVehicle().getVehicleNumber())
                        .servicedOn(a.getAppointmentDate())
                        .build()
        ).toList();

        return DashboardResponse.builder()
                .vehicleCount(vehicleCount)
                .upcomingAppointments(upcomingCount)
                .nextService(nextService)
                .recentServices(recentList)
                .build();
    }
}
