package com.neeraj.AutomotiveBackend.appointments;

import com.neeraj.AutomotiveBackend.dto.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment book(@RequestBody AppointmentRequest request){
        return appointmentService.bookAppointment(request);
    }

    @GetMapping
    public List<Appointment> getMyAppointments(){
        return appointmentService.getMyAppointments();
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id,@RequestBody AppointmentRequest request){
        request.setId(id);
        return appointmentService.updateAppointment(request);
    }

    @DeleteMapping("/{id}")
    public Appointment cancelAppointment(@PathVariable Long id){
        return appointmentService.cancelAppointment(id);
    }

}
