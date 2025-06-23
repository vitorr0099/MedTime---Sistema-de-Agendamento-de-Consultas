package com.medTime.service;

import com.medTime.model.Appointment;
import com.medTime.model.Doctor;
import com.medTime.model.Patient;
import com.medTime.repository.AppointmentRepository;
import com.medTime.repository.DoctorRepository;
import com.medTime.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setId(1L);
        doctor.setName("Dr. House");

        patient = new Patient();
        patient.setId(1L);
        patient.setName("Paciente Teste");

        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDateTime(LocalDateTime.of(2025, 10, 10, 10, 0));
        appointment.setStatus("SCHEDULED");
    }

    // 1. Magic Number Test Smell

    /**
     * TESTE COM SMELL: Magic Number Test
     * O que o teste faz: Verifica se o número de horários disponíveis para um médico em um dia é 20.
     * Qual é o smell: O número '20' é um "número mágico" porque não tem um significado explícito.
     * Se a lógica de negócio (horário de trabalho do médico ou duração da consulta) mudar,
     * este teste pode quebrar e não será óbvio o porquê de '20' estar incorreto.
     */
    @Test
    @DisplayName("Smelly - Deve retornar 20 horários disponíveis")
    void getAvailableTimeSlots_Smelly_MagicNumber() {
        // Arrange
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        LocalDate date = LocalDate.of(2025, 1, 1);

        // Act
        List<LocalDateTime> slots = appointmentService.getAvailableTimeSlots(1L, date.toString());

        // Assert
        assertEquals(20, slots.size()); // SMELL: Magic Number '20'
    }

    /**
     * TESTE CORRIGIDO: Magic Number Test
     * O que foi corrigido: O "número mágico" foi substituído por uma variável calculada
     * (`expectedSlots`) que expressa a lógica de negócio.
     * Agora, o teste é mais legível e robusto a mudanças. Se o horário de trabalho mudar,
     * basta atualizar as variáveis `startTime` e `endTime` e o teste continuará correto.
     */
    @Test
    @DisplayName("Corrigido - Deve retornar o número correto de horários disponíveis")
    void getAvailableTimeSlots_Fixed_MagicNumber() {
        // Arrange
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        int slotDurationInMinutes = 30;
        long totalMinutes = endTime.toSecondOfDay() / 60 - startTime.toSecondOfDay() / 60;
        long expectedSlots = totalMinutes / slotDurationInMinutes;

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        LocalDate date = LocalDate.of(2025, 1, 1);

        // Act
        List<LocalDateTime> slots = appointmentService.getAvailableTimeSlots(1L, date.toString());

        // Assert
        assertEquals(expectedSlots, slots.size());
    }

    // 2. Eager Test Smell

    /**
     * TESTE COM SMELL: Eager Test
     * O que o teste faz: Tenta testar duas funcionalidades de uma vez: agendar uma consulta
     * e depois verificar se a consulta aparece na lista de agendamentos do paciente.
     * Qual é o smell: O teste tem mais de uma responsabilidade. Se falhar, pode ser
     * difícil identificar qual das duas funcionalidades está com problema, tornando o debug mais complexo.
     */
    @Test
    @DisplayName("Smelly - Deve agendar consulta e aparecer na lista do paciente")
    void scheduleAndFindAppointment_Smelly_EagerTest() {
        // Arrange (Agendamento)
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act (Agendamento)
        Appointment newAppointment = appointmentService.scheduleAppointment(1L, 1L, appointment.getAppointmentDateTime());

        // Assert (Agendamento)
        assertNotNull(newAppointment);

        // Arrange (Busca)
        // O teste continua para testar outra funcionalidade
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(newAppointment);
        when(appointmentRepository.findByPatientId(1L)).thenReturn(appointments);

        // Act (Busca)
        List<Appointment> foundAppointments = appointmentService.findByPatientId(1L);

        // Assert (Busca)
        assertEquals(1, foundAppointments.size());
        assertEquals(newAppointment.getId(), foundAppointments.get(0).getId());
    }

    /**
     * TESTE CORRIGIDO: Eager Test
     * O que foi corrigido: O teste original foi dividido em dois testes menores e focados,
     * cada um com uma única responsabilidade (Princípio da Responsabilidade Única).
     * Um teste verifica o agendamento e o outro, a busca. Isso torna os testes mais claros e fáceis de manter.
     */
    @Test
    @DisplayName("Corrigido - Deve agendar uma nova consulta com sucesso")
    void scheduleAppointment_Fixed() {
        // Arrange
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment newAppointment = appointmentService.scheduleAppointment(1L, 1L, appointment.getAppointmentDateTime());

        // Assert
        assertNotNull(newAppointment);
        assertEquals("SCHEDULED", newAppointment.getStatus());
        assertEquals(patient.getId(), newAppointment.getPatient().getId());
    }

    @Test
    @DisplayName("Corrigido - Deve encontrar agendamentos por ID do paciente")
    void findByPatientId_Fixed() {
        // Arrange
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        when(appointmentRepository.findByPatientId(1L)).thenReturn(appointments);

        // Act
        List<Appointment> foundAppointments = appointmentService.findByPatientId(1L);

        // Assert
        assertEquals(1, foundAppointments.size());
        assertEquals(appointment.getId(), foundAppointments.get(0).getId());
    }

    // 3. Assertion Roulette Smell

    /**
     * TESTE COM SMELL: Assertion Roulette
     * O que o teste faz: Verifica múltiplos atributos do objeto Appointment retornado após o agendamento.
     * Qual é o smell: Existem várias asserções sem mensagens explicativas. Se uma delas falhar,
     * o JUnit apenas informará qual linha falhou, mas não o "porquê" ou o contexto do erro,
     * tornando a depuração mais lenta. É como uma "roleta" para descobrir qual verificação deu errado.
     */
    @Test
    @DisplayName("Smelly - Deve agendar consulta com dados corretos")
    void scheduleAppointment_Smelly_AssertionRoulette() {
        // Arrange
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment newAppointment = appointmentService.scheduleAppointment(1L, 1L, appointment.getAppointmentDateTime());

        // Assert
        assertNotNull(newAppointment);
        assertEquals(1L, newAppointment.getId());
        assertEquals("SCHEDULED", newAppointment.getStatus());
        assertEquals(1L, newAppointment.getPatient().getId());
        assertEquals(1L, newAppointment.getDoctor().getId());
        assertEquals(LocalDateTime.of(2025, 10, 10, 10, 0), newAppointment.getAppointmentDateTime());
    }

    /**
     * TESTE CORRIGIDO: Assertion Roulette
     * O que foi corrigido: Foram adicionadas mensagens de diagnóstico claras para cada asserção.
     * Agora, se uma verificação falhar, a mensagem de erro será muito mais informativa,
     * explicando exatamente qual atributo não correspondeu ao esperado.
     */
    @Test
    @DisplayName("Corrigido - Deve agendar consulta com dados corretos e asserções claras")
    void scheduleAppointment_Fixed_AssertionRoulette() {
        // Arrange
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateTime(anyLong(), any(LocalDateTime.class))).thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment newAppointment = appointmentService.scheduleAppointment(1L, 1L, appointment.getAppointmentDateTime());

        // Assert
        assertNotNull(newAppointment, "O agendamento não deveria ser nulo");
        assertAll("Verificação completa do agendamento",
            () -> assertEquals(1L, newAppointment.getId(), "O ID do agendamento está incorreto."),
            () -> assertEquals("SCHEDULED", newAppointment.getStatus(), "O status inicial deveria ser 'SCHEDULED'."),
            () -> assertEquals(1L, newAppointment.getPatient().getId(), "O ID do paciente está incorreto."),
            () -> assertEquals(1L, newAppointment.getDoctor().getId(), "O ID do médico está incorreto."),
            () -> assertEquals(LocalDateTime.of(2025, 10, 10, 10, 0), newAppointment.getAppointmentDateTime(), "A data/hora do agendamento está incorreta.")
        );
    }
    
    // 4. Conditional Test Logic Smell

    /**
     * TESTE COM SMELL: Conditional Test Logic
     * O que o teste faz: Usa uma estrutura condicional (if/else) para verificar o resultado do cancelamento.
     * Qual é o smell: Um teste unitário deve ser determinístico e ter um único caminho de execução.
     * A lógica condicional indica que o teste está tentando cobrir múltiplos cenários, o que
     * deve ser feito em testes separados. Isso torna o teste mais complexo e menos legível.
     */
    @Test
    @DisplayName("Smelly - Deve cancelar um agendamento se estiver agendado")
    void cancelAppointment_Smelly_ConditionalLogic() {
        // Arrange
        // O agendamento pode ser cancelado pois a data é futura (24h de antecedência)
        appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(2));
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        appointmentService.cancelAppointment(1L);

        // Assert
        if ("CANCELLED".equals(appointment.getStatus())) {
            assertTrue(true); // O teste passa se o status for CANCELLED
        } else {
            fail("O status do agendamento deveria ser CANCELLED, mas era " + appointment.getStatus());
        }
    }

    /**
     * TESTE CORRIGIDO: Conditional Test Logic
     * O que foi corrigido: A estrutura condicional foi removida e substituída por uma asserção direta.
     * O teste agora segue um caminho único e simplesmente verifica se o estado final do objeto
     * é o esperado. Se o status não for "CANCELLED", o teste falhará com uma mensagem clara.
     */
    @Test
    @DisplayName("Corrigido - Deve mudar o status para CANCELLED ao cancelar")
    void cancelAppointment_Fixed_ConditionalLogic() {
        // Arrange
        appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(2));
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        appointmentService.cancelAppointment(1L);

        // Assert
        assertEquals("CANCELLED", appointment.getStatus(), "O status do agendamento deveria ter sido atualizado para CANCELLED.");
        verify(appointmentRepository).save(appointment);
    }
    
    // 5. Duplicate Assert Smell

    /**
     * TESTE COM SMELL: Duplicate Assert
     * O que o teste faz: Verifica duas vezes a mesma condição (se o status é "CANCELLED").
     * Qual é o smell: A segunda asserção é redundante e não adiciona nenhum valor ao teste.
     * Isso polui o código do teste e pode levar a confusão, especialmente se as asserções
     * forem mais complexas. Não há motivo para testar a mesma coisa duas vezes.
     */
    @Test
    @DisplayName("Smelly - Deve cancelar um agendamento e verificar o status duas vezes")
    void cancelAppointment_Smelly_DuplicateAssert() {
        // Arrange
        appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(2));
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        appointmentService.cancelAppointment(1L);

        // Assert
        assertEquals("CANCELLED", appointment.getStatus());
        //... algum código ou comentário que separa as duas asserções
        assertEquals("CANCELLED", appointment.getStatus()); // SMELL: asserção duplicada
    }

    /**
     * TESTE CORRIGIDO: Duplicate Assert
     * O que foi corrigido: A asserção duplicada foi simplesmente removida. O teste continua
     * garantindo a mesma qualidade de verificação, mas agora é mais limpo, conciso e direto ao ponto.
     */
    @Test
    @DisplayName("Corrigido - Deve cancelar um agendamento")
    void cancelAppointment_Fixed_DuplicateAssert() {
        // Arrange
        appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(2));
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        appointmentService.cancelAppointment(1L);

        // Assert
        assertEquals("CANCELLED", appointment.getStatus());
        verify(appointmentRepository).save(appointment);
    }
} 