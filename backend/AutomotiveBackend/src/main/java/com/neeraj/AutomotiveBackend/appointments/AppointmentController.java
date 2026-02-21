package com.neeraj.AutomotiveBackend.appointments;

import com.neeraj.AutomotiveBackend.dto.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public Appointment book(@RequestBody AppointmentRequest request){
        return appointmentService.bookAppointment(request);
    }

    @GetMapping
    public List<Appointment> getMyAppointments(){
        return appointmentService.getMyAppointments();
    }

}
