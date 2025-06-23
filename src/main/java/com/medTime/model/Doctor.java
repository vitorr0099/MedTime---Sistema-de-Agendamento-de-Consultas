package com.medTime.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String crm;
    private String email;
    private String description;
    private String photo_url;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;
} 