package com.neeraj.AutomotiveBackend.dashboard;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {
    private Long vehicleCount;
    private Long upcomingAppointments;

    private NextServiceDTO nextService;

    private List<RecentServiceDTO> recentServices;
}
