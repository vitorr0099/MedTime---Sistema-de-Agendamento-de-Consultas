package com.medTime.service;

import com.medTime.dto.DoctorDTO;
import com.medTime.dto.UserDTO;
import com.medTime.model.Patient;
import com.medTime.model.Specialization;
import com.medTime.model.User;
import com.medTime.model.UserType;
import com.medTime.repository.PatientRepository;
import com.medTime.repository.SpecializationRepository;
import com.medTime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecializationRepository specializationRepository;

    @Autowired
    PatientRepository patientRepository;

    @Transactional
    public void createUser(UserDTO userDTO) {
        // Create and save the user
        User newUser = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        newUser.setUserType(UserType.PATIENT);
        userRepository.save(newUser);

        // Create and save the corresponding patient record
        Patient newPatient = new Patient();
        newPatient.setName(userDTO.getName());
        newPatient.setEmail(userDTO.getEmail());
        patientRepository.save(newPatient);
    }

    public void createDoctor(DoctorDTO doctorDTO) {
        Specialization specialization = specializationRepository.findById(doctorDTO.getSpecializationId())
            .orElseThrow(() -> new RuntimeException("Especialização não encontrada"));

        User newDoctor = new User(
            doctorDTO.getName(),
            doctorDTO.getEmail(),
            doctorDTO.getPassword(),
            doctorDTO.getCrm(),
            specialization,
            doctorDTO.getPhoto_url()
        );
        userRepository.save(newDoctor);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public void updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }
        
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElse(null);
        
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Long getPatientIdByEmail(String email) {
        return patientRepository.findByEmail(email)
                .map(Patient::getId)
                .orElse(null);
    }
}
