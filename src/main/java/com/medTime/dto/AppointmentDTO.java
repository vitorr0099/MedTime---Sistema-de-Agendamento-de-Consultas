package com.medTime.dto;

import java.time.LocalDateTime;

public record AppointmentDTO(
    Long doctorId,
    LocalDateTime appointmentDateTime
) {} 