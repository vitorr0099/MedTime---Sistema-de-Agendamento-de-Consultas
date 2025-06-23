package com.medTime.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String crm;
    private Long specializationId;
    private String photo_url;
    // Adicione outros campos conforme necessário
} 