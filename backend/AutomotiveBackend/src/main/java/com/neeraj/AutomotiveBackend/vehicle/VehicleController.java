package com.neeraj.AutomotiveBackend.vehicle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> getMyVehicles(){
        return vehicleService.getMyVehicles();
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id,@RequestBody Vehicle updated){
        updated.setId(id);
        return vehicleService.updateVehicle(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
    }
}
