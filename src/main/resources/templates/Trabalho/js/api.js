// Configuração da API
const API_BASE_URL = 'http://localhost:8080';

// Funções para integração com o backend

// Buscar médicos por especialidade
async function getDoctorsBySpecialization(specializationId) {
    try {
        console.log('Buscando médicos para especialização:', specializationId, 'Tipo:', typeof specializationId);
        const response = await fetch(`${API_BASE_URL}/api/doctors/specialization/${specializationId}`);
        console.log('Status da resposta:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const doctors = await response.json();
        console.log('Médicos encontrados:', doctors);
        return doctors;
    } catch (error) {
        console.error('Erro ao buscar médicos:', error);
        return [];
    }
}

// Buscar horários disponíveis
async function getAvailableTimeSlots(doctorId, date) {
    try {
        const response = await fetch(`${API_BASE_URL}/api/appointments/available/${doctorId}?date=${date}`);
        const slots = await response.json();
        return slots;
    } catch (error) {
        console.error('Erro ao buscar horários:', error);
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
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('Erro ao agendar consulta:', error);
        throw error;
    }
}

// Buscar consultas do paciente
async function getPatientAppointments(patientId) {
    try {
        const response = await fetch(`${API_BASE_URL}/api/appointments/patient/${patientId}`);
        const appointments = await response.json();
        return appointments;
    } catch (error) {
        console.error('Erro ao buscar consultas:', error);
        return [];
    }
}

// Carregar médicos na página
async function loadDoctors(specializationId) {
    console.log('Iniciando carregamento de médicos para especialização:', specializationId);
    const doctors = await getDoctorsBySpecialization(specializationId);
    const container = document.querySelector('.especialidades_opcoes');
    
    if (!container) {
        console.error('Container não encontrado');
        return;
    }

    if (!doctors || doctors.length === 0) {
        console.log('Nenhum médico encontrado');
        container.innerHTML = '<p>Nenhum médico disponível para esta especialidade.</p>';
        return;
    }

    console.log('Renderizando', doctors.length, 'médicos');
    container.innerHTML = doctors.map(doctor => `
        <div class="especialidade_container">
            <img src="${doctor.photo_url || '/logo/logo.png'}" 
                 alt="${doctor.name}" 
                 class="icone_especialidade" 
                 onerror="this.src='/logo/logo.png'" />
            <span>DR(A). ${doctor.name.toUpperCase()}</span>
            <p><strong>${doctor.specialization.name}</strong><br>CRM: ${doctor.crm}</p>
            <div class="botoes-container">
                <a href="#" class="ver-agenda-btn" onclick="showCalendar(${doctor.id})">Agenda</a>
                <a href="#" class="ver-detalhes-btn" onclick="showDetails(${doctor.id})">Detalhes</a>
            </div>
        </div>
    `).join('');
}

// Mostrar calendário para um médico específico
function showCalendar(doctorId) {
    // Salvar o ID do médico selecionado
    localStorage.setItem('selectedDoctorId', doctorId);
    // Redirecionar para a página do calendário
    window.location.href = '/calendario';
}

// Mostrar detalhes de um médico específico
function showDetails(doctorId) {
    localStorage.setItem('selectedDoctorId', doctorId);
    window.location.href = '/medico-detalhes';
}

// Inicializar o calendário
function initializeCalendar() {
    const doctorId = localStorage.getItem('selectedDoctorId');
    if (!doctorId) return;

    const today = new Date();
    const dateStr = today.toISOString().split('T')[0];
    
    getAvailableTimeSlots(doctorId, dateStr).then(slots => {
        // Marcar os dias que têm horários disponíveis
        slots.forEach(slot => {
            const date = new Date(slot);
            // Adicionar classe para destacar dias com horários disponíveis
            const dayElement = document.querySelector(`[data-date="${date.getDate()}"]`);
            if (dayElement) {
                dayElement.classList.add('available');
            }
        });
    });
}

// Carregar horários disponíveis para um dia específico
async function loadAvailableSlots(date) {
    const doctorId = localStorage.getItem('selectedDoctorId');
    if (!doctorId) return;

    const slots = await getAvailableTimeSlots(doctorId, date);
    const container = document.querySelector('.horarios-disponiveis');
    if (!container) return;

    container.innerHTML = slots.map(slot => {
        const time = new Date(slot).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
        return `
            <button onclick="scheduleSlot('${slot}')">${time}</button>
        `;
    }).join('');
}

// Agendar horário
async function scheduleSlot(dateTime) {
    const doctorId = localStorage.getItem('selectedDoctorId');
    const patientId = localStorage.getItem('patientId'); // Você precisa definir isso quando o usuário fizer login
    
    try {
        await scheduleAppointment(patientId, doctorId, dateTime);
        alert('Consulta agendada com sucesso!');
        window.location.href = '/especialidades.html';
    } catch (error) {
        alert('Erro ao agendar consulta. Por favor, tente novamente.');
    }
}

// IMPORTANTE: Certifique-se de salvar o patientId no localStorage ao fazer login, por exemplo:
// localStorage.setItem('patientId', userId); 