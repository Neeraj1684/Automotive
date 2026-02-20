package com.neeraj.AutomotiveBackend.vehicle;

import com.neeraj.AutomotiveBackend.customer.CustomerProfile;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vehicleNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customer;
}
