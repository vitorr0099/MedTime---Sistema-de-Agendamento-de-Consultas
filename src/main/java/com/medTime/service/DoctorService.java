package com.medTime.service;

import com.medTime.model.Doctor;
import com.medTime.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findBySpecialization(Long specializationId) {
        return doctorRepository.findBySpecializationId(specializationId);
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }
} 