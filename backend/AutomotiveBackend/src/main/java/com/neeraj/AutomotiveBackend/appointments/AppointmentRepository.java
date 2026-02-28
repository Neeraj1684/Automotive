package com.neeraj.AutomotiveBackend.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomerId(Long customerId);
    Optional<Appointment> findById(Long id);

    long countByCustomerIdAndStatusAndAppointmentDateAfter(
            Long customerId,
            AppointmentStatus status,
            LocalDate date
    );

    Optional<Appointment> findTopByCustomerIdAndStatusAndAppointmentDateAfterOrderByAppointmentDateAsc(
            Long customerId,
            AppointmentStatus status,
            LocalDate date
    );

    List<Appointment> findTop3ByCustomerIdAndStatusOrderByAppointmentDateDesc(
            Long customerId,
            AppointmentStatus status
    );

    boolean existsByVehicleIdAndAppointmentDateAndStatus(
            Long vehicleId,
            LocalDate appointmentDate,
            AppointmentStatus status
    );
}
