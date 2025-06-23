package com.medTime.controller;

import com.medTime.model.Doctor;
import com.medTime.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/specialization/{specializationId}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable Long specializationId) {
        List<Doctor> doctors = doctorService.findBySpecialization(specializationId);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }
} 