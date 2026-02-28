package com.neeraj.AutomotiveBackend.dashboard;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class RecentServiceDTO {

    private String vehicleName;
    private String vehicleNumber;
    private LocalDate servicedOn;
}
