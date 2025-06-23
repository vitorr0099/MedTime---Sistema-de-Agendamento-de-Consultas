package com.medTime.controller;

import com.medTime.dto.DoctorDTO;
import com.medTime.dto.UserDTO;
import com.medTime.model.User;
import com.medTime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return new ResponseEntity<>("Usuario criado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/admin/createDoctor")
    public ResponseEntity<String> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            userService.createDoctor(doctorDTO);
            return new ResponseEntity<>("Médico criado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar médico: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("Usuário deletado com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("não encontrado")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Erro ao deletar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            userService.updateUser(id, userDTO);
            return new ResponseEntity<>("Usuário atualizado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar usuário: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            User user = userService.authenticateUser(userDTO.getEmail(), userDTO.getPassword());
            if (user != null) {
                // Buscar patientId se for paciente
                Long patientId = null;
                if (user.getUserType() != null && user.getUserType().name().equals("PATIENT")) {
                    patientId = userService.getPatientIdByEmail(user.getEmail());
                }
                return new ResponseEntity<>(Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "userType", user.getUserType(),
                    "patientId", patientId
                ), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciais inválidas", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao autenticar: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
