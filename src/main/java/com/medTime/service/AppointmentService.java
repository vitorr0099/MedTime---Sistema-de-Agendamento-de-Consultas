package com.medTime.service;

import com.medTime.dto.AppointmentDTO;
import com.medTime.model.Appointment;
import com.medTime.model.Doctor;
import com.medTime.model.Patient;
import com.medTime.model.User;
import com.medTime.model.UserType;
import com.medTime.repository.AppointmentRepository;
import com.medTime.repository.DoctorRepository;
import com.medTime.repository.PatientRepository;
import com.medTime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<User> getDoctorsBySpecialization(Long specializationId) {
        logger.info("Buscando médicos para especialização ID: {} e tipo: {}", specializationId, UserType.DOCTOR);
        List<User> doctors = userRepository.findByUserTypeAndSpecialization_Id(UserType.DOCTOR, specializationId);
        logger.info("Encontrados {} médicos", doctors.size());
        if (doctors.isEmpty()) {
            logger.warn("Nenhum médico encontrado para a especialização {}", specializationId);
        } else {
            for (User doctor : doctors) {
                logger.info("Médico encontrado: ID={}, Nome={}, CRM={}, Especialização={}", 
                    doctor.getId(), 
                    doctor.getName(), 
                    doctor.getCrm(),
                    doctor.getSpecialization() != null ? doctor.getSpecialization().getId() : "null");
            }
        }
        return doctors;
    }

    public List<LocalDateTime> getAvailableTimeSlots(Long doctorId, String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(8, 0); // 8:00 AM
        LocalTime endTime = LocalTime.of(18, 0); // 6:00 PM

        // Gerar slots de 30 minutos
        for (LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(30)) {
            LocalDateTime slot = LocalDateTime.of(date, time);
            if (isSlotAvailable(doctorId, slot)) {
                availableSlots.add(slot);
            }
        }

        return availableSlots;
    }

    public Appointment scheduleAppointment(Long patientId, Long doctorId, LocalDateTime dateTime) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        if (!isSlotAvailable(doctorId, dateTime)) {
            throw new RuntimeException("Horário não disponível");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDateTime(dateTime);
        appointment.setStatus("SCHEDULED");

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    private boolean isSlotAvailable(Long doctorId, LocalDateTime dateTime) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateTime(doctorId, dateTime).isEmpty();
    }

    public Appointment createAppointment(Long patientId, AppointmentDTO appointmentDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Doctor doctor = doctorRepository.findById(appointmentDTO.doctorId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        // Verificar se o horário está disponível
        LocalDateTime appointmentTime = appointmentDTO.appointmentDateTime();
        List<Appointment> existingAppointments = appointmentRepository
                .findDoctorAppointmentsForDay(doctor, appointmentTime, appointmentTime.plusMinutes(45));

        if (!existingAppointments.isEmpty()) {
            throw new RuntimeException("Horário não disponível");
        }

        Appointment appointment = new Appointment(patient, doctor, appointmentTime);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return appointmentRepository.findByPatient(patient);
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        // Verificar se o agendamento já foi cancelado
        if ("CANCELLED".equals(appointment.getStatus())) {
            throw new RuntimeException("Agendamento já foi cancelado");
        }

        // Verificar se o agendamento já foi realizado
        if ("COMPLETED".equals(appointment.getStatus())) {
            throw new RuntimeException("Não é possível cancelar um agendamento já realizado");
        }

        // Verificar se o cancelamento está dentro do prazo (24 horas antes)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentTime = appointment.getAppointmentDateTime();
        LocalDateTime cancellationDeadline = appointmentTime.minusHours(24);

        if (now.isAfter(cancellationDeadline)) {
            throw new RuntimeException("Não é possível cancelar o agendamento. O prazo para cancelamento (24 horas antes da consulta) já expirou.");
        }

        // Cancelar o agendamento
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
        
        logger.info("Agendamento ID: {} cancelado com sucesso. Data da consulta: {}", 
            appointmentId, appointmentTime);
    }
} 