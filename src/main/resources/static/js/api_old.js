// Configuração da API
const API_BASE_URL = 'http://localhost:8080';

// Funções para integração com o backend

// Buscar médicos por especialidade
async function getDoctorsBySpecialization(specializationId) {
    try {
        console.log('Buscando médicos para especialização:', specializationId);
        const response = await fetch(`${API_BASE_URL}/api/doctors/specialization/${specializationId}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const doctors = await response.json();
        console.log('Médicos encontrados:', doctors);
        return doctors;
    } catch (error) {
        console.error('Erro ao buscar médicos:', error);
        showError('Erro ao carregar médicos. Por favor, tente novamente.');
        return [];
    }
}

// Buscar horários disponíveis
async function getAvailableTimeSlots(doctorId, date) {
    try {
        const response = await fetch(`${API_BASE_URL}/api/appointments/available/${doctorId}?date=${date}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const slots = await response.json();
        return slots;
    } catch (error) {
        console.error('Erro ao buscar horários:', error);
        showError('Erro ao carregar horários. Por favor, tente novamente.');
        return [];
    }
}

// Agendar consulta
async function scheduleAppointment(patientId, doctorId, dateTime) {
    try {
        const response = await fetch(`${API_BASE_URL}/api/appointments/schedule`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                patientId: patientId,
                doctorId: doctorId,
                appointmentDateTime: dateTime
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        showSuccess('Consulta agendada com sucesso!');
        return result;
    } catch (error) {
        console.error('Erro ao agendar consulta:', error);
        showError('Erro ao agendar consulta. Por favor, tente novamente.');
        throw error;
    }
}

// Buscar consultas do paciente
async function getPatientAppointments(patientId) {
    try {
        const response = await fetch(`${API_BASE_URL}/api/appointments/patient/${patientId}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const appointments = await response.json();
        return appointments;
    } catch (error) {
        console.error('Erro ao buscar consultas:', error);
        showError('Erro ao carregar consultas. Por favor, tente novamente.');
        return [];
    }
}

// Funções de utilidade para feedback ao usuário
function showError(message) {
    // Implementar um sistema de notificação de erro
    alert(message); // Temporário - substituir por uma UI mais elegante
}

function showSuccess(message) {
    // Implementar um sistema de notificação de sucesso
    alert(message); // Temporário - substituir por uma UI mais elegante
}

// Funções de navegação
function navigateToSpecialization(specializationId) {
    window.location.href = `especialidades.html?specialization=${specializationId}`;
}

function navigateToCalendar(doctorId) {
    window.location.href = `calendario.html?doctor=${doctorId}`;
}

// Funções de formatação
function formatDate(date) {
    return date.toLocaleDateString('pt-BR');
}

function formatTime(time) {
    return time.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
} 