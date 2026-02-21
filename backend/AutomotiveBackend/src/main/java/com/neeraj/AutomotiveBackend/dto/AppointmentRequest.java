package com.neeraj.AutomotiveBackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequest {
    private Long vehicleId;
    private LocalDate appointmentDate;
}
