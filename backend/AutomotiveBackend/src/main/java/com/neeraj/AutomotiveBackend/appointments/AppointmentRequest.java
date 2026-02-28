package com.neeraj.AutomotiveBackend.appointments;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequest {
    private Long id;
    private Long vehicleId;
    private LocalDate appointmentDate;
}
