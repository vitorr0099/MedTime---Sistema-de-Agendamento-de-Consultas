package com.medTime.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    // Adicione outros campos conforme necessário
}
