// Define o pacote (namespace) onde esta classe está localizada.
package com.medTime.controller;

// Importa a classe Appointment, que representa a entidade de agendamento.
import com.medTime.model.Appointment;
// Importa a classe User, que representa a entidade de usuário (incluindo médicos).
import com.medTime.model.User;
// Importa a classe AppointmentService, que contém a lógica de negócio para agendamentos.
import com.medTime.service.AppointmentService;
// Importa a classe AppointmentRequest, um DTO para receber dados de agendamento.
import com.medTime.dto.AppointmentRequest;
// Importa a anotação Autowired para injeção de dependência automática do Spring.
import org.springframework.beans.factory.annotation.Autowired;
// Importa a classe ResponseEntity para encapsular a resposta HTTP (status, cabeçalhos, corpo).
import org.springframework.http.ResponseEntity;
// Importa anotações do Spring Web para criar um controlador REST.
import org.springframework.web.bind.annotation.*;

// Importa a classe LocalDate para trabalhar com datas sem tempo. (Não está sendo usada diretamente, mas foi mantida).
import java.time.LocalDate;
// Importa a classe LocalDateTime para trabalhar com datas e horas.
import java.time.LocalDateTime;
// Importa a interface List para trabalhar com coleções de objetos.
import java.util.List;
// Importa a interface Map para trabalhar com coleções chave-valor.
import java.util.Map;
// Importa a interface Logger do SLF4J para registrar mensagens (logs) da aplicação.
import org.slf4j.Logger;
// Importa a classe LoggerFactory para criar instâncias de Logger.
import org.slf4j.LoggerFactory;

// @RestController: Marca a classe como um controlador REST, o que significa que ela lidará com requisições HTTP e retornará dados (como JSON).
@RestController
// @RequestMapping: Define o prefixo da URL para todos os endpoints (rotas) neste controlador. Todas as rotas começarão com "/api/appointments".
@RequestMapping("/api/appointments")
public class AppointmentController {
    // Cria uma instância de Logger para esta classe, permitindo registrar eventos e erros. 'static final' garante que seja uma constante da classe.
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    // @Autowired: Injeta (fornece) uma instância da classe AppointmentService. O Spring gerencia a criação e o ciclo de vida deste objeto.
    @Autowired
    private AppointmentService appointmentService;

    // @GetMapping: Mapeia requisições HTTP GET para a URL "/api/appointments/doctors/{specializationId}".
    // O {specializationId} é uma variável na URL.
    @GetMapping("/doctors/{specializationId}")
    public ResponseEntity<List<User>> getDoctorsBySpecialization(@PathVariable Long specializationId) {
        // 'try-catch': Bloco para tratamento de exceções. Se algo der errado, o 'catch' é executado.
        try {
            // Registra uma mensagem informativa (log) de que a busca por médicos começou.
            logger.info("Buscando médicos para especialização: {}", specializationId);
            // Chama o método no serviço para buscar médicos pela especialidade e armazena o resultado em uma lista.
            List<User> doctors = appointmentService.getDoctorsBySpecialization(specializationId);
            // Registra quantos médicos foram encontrados.
            logger.info("Encontrados {} médicos", doctors.size());
            // Retorna uma resposta HTTP 200 (OK) com a lista de médicos no corpo da resposta.
            return ResponseEntity.ok(doctors);
        // Captura qualquer exceção que possa ocorrer no bloco 'try'.
        } catch (Exception e) {
            // Registra um log de erro com a mensagem da exceção e o stack trace.
            logger.error("Erro ao buscar médicos: {}", e.getMessage(), e);
            // Retorna uma resposta HTTP 500 (Internal Server Error) se ocorrer um erro.
            return ResponseEntity.internalServerError().build();
        }
    }

    // @GetMapping: Mapeia requisições HTTP GET para "/api/appointments/available/{doctorId}".
    @GetMapping("/available/{doctorId}")
    public ResponseEntity<List<LocalDateTime>> getAvailableTimeSlots(
            // @PathVariable: Extrai o valor de 'doctorId' da URL.
            @PathVariable Long doctorId,
            // @RequestParam: Extrai o valor do parâmetro 'date' da URL (ex: ?date=2025-12-31).
            @RequestParam String date) {
        // Chama o serviço para obter a lista de horários disponíveis para o médico e a data.
        List<LocalDateTime> slots = appointmentService.getAvailableTimeSlots(doctorId, date);
        // Retorna uma resposta HTTP 200 (OK) com a lista de horários.
        return ResponseEntity.ok(slots);
    }

    // @PostMapping: Mapeia requisições HTTP POST para "/api/appointments/schedule". Usado para criar novos recursos (neste caso, agendamentos).
    @PostMapping("/schedule")
    public ResponseEntity<Appointment> scheduleAppointment(@RequestBody AppointmentRequest request) {
        // @RequestBody: Converte o corpo da requisição (JSON) em um objeto Java (AppointmentRequest).
        // Chama o serviço para criar o agendamento com os dados recebidos na requisição.
        Appointment appointment = appointmentService.scheduleAppointment(
            request.getPatientId(),      // Obtém o ID do paciente do objeto de requisição.
            request.getDoctorId(),       // Obtém o ID do médico do objeto de requisição.
            request.getAppointmentDateTime() // Obtém a data e hora do agendamento do objeto de requisição.
        );
        // Retorna uma resposta HTTP 200 (OK) com o objeto do agendamento criado.
        return ResponseEntity.ok(appointment);
    }

    // @GetMapping: Mapeia requisições HTTP GET para "/api/appointments/patient/{patientId}".
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable Long patientId) {
        // @PathVariable: Extrai o ID do paciente da URL.
        // Chama o serviço para buscar todos os agendamentos de um paciente específico.
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        // Retorna uma resposta HTTP 200 (OK) com a lista de agendamentos do paciente.
        return ResponseEntity.ok(appointments);
    }

    // @PutMapping: Mapeia requisições HTTP PUT para "/api/appointments/{appointmentId}/cancel".
    @PutMapping("/{appointmentId}/cancel")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long appointmentId) {
        try {
            logger.info("Tentativa de cancelamento do agendamento ID: {}", appointmentId);
            appointmentService.cancelAppointment(appointmentId);
            logger.info("Agendamento ID: {} cancelado com sucesso", appointmentId);
            return ResponseEntity.ok().body(Map.of("message", "Agendamento cancelado com sucesso"));
        } catch (RuntimeException e) {
            logger.error("Erro ao cancelar agendamento ID: {} - {}", appointmentId, e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Erro interno ao cancelar agendamento ID: {}", appointmentId, e);
            return ResponseEntity.internalServerError().body(Map.of("error", "Erro interno do servidor"));
        }
    }
}