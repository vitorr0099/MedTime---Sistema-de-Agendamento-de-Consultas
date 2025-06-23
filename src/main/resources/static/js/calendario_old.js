// let currentDate = new Date();
// let selectedDate = null;

// // Inicialização do calendário
// function initializeCalendar() {
//     updateCalendarHeader();
//     renderCalendar();
//     setupEventListeners();
// }

// // Atualizar o cabeçalho do calendário
// function updateCalendarHeader() {
//     const monthNames = [
//         'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
//         'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'
//     ];
//     document.getElementById('currentMonth').textContent = 
//         `${monthNames[currentDate.getMonth()]} ${currentDate.getFullYear()}`;
// }

// // Renderizar o calendário
// function renderCalendar() {
//     const firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
//     const lastDay = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
//     const startingDay = firstDay.getDay();
//     const totalDays = lastDay.getDate();

//     const calendarDays = document.getElementById('calendario-dias');
//     calendarDays.innerHTML = '';

//     // Adicionar dias vazios no início
//     for (let i = 0; i < startingDay; i++) {
//         const emptyDay = document.createElement('div');
//         emptyDay.className = 'calendario-dia';
//         calendarDays.appendChild(emptyDay);
//     }

//     // Adicionar os dias do mês
//     for (let day = 1; day <= totalDays; day++) {
//         const dayElement = document.createElement('div');
//         dayElement.className = 'calendario-dia';
//         dayElement.textContent = day;
//         dayElement.dataset.date = day;

//         // Verificar se o dia está disponível
//         const date = new Date(currentDate.getFullYear(), currentDate.getMonth(), day);
//         if (date < new Date().setHours(0, 0, 0, 0)) {
//             dayElement.classList.add('unavailable');
//         } else {
//             dayElement.addEventListener('click', () => selectDate(date));
//         }

//         calendarDays.appendChild(dayElement);
//     }

//     // Carregar horários disponíveis para o mês atual
//     loadAvailableDates();
// }

// // Configurar event listeners
// function setupEventListeners() {
//     document.getElementById('prevMonth').addEventListener('click', () => {
//         currentDate.setMonth(currentDate.getMonth() - 1);
//         updateCalendarHeader();
//         renderCalendar();
//     });

//     document.getElementById('nextMonth').addEventListener('click', () => {
//         currentDate.setMonth(currentDate.getMonth() + 1);
//         updateCalendarHeader();
//         renderCalendar();
//     });
// }

// // Selecionar uma data
// function selectDate(date) {
//     selectedDate = date;
    
//     // Remover seleção anterior
//     document.querySelectorAll('.calendario-dia.selected').forEach(el => {
//         el.classList.remove('selected');
//     });

//     // Adicionar seleção ao dia clicado
//     const dayElement = document.querySelector(`[data-date="${date.getDate()}"]`);
//     if (dayElement) {
//         dayElement.classList.add('selected');
//     }

//     // Carregar horários disponíveis para a data selecionada
//     loadAvailableTimeSlots(date);
// }

// // Carregar datas disponíveis
// async function loadAvailableDates() {
//     const doctorId = new URLSearchParams(window.location.search).get('doctor');
//     if (!doctorId) return;

//     try {
//         const date = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
//         const slots = await getAvailableTimeSlots(doctorId, date.toISOString().split('T')[0]);
        
//         // Marcar dias com horários disponíveis
//         slots.forEach(slot => {
//             const slotDate = new Date(slot);
//             const dayElement = document.querySelector(`[data-date="${slotDate.getDate()}"]`);
//             if (dayElement) {
//                 dayElement.classList.add('available');
//             }
//         });
//     } catch (error) {
//         console.error('Erro ao carregar datas disponíveis:', error);
//     }
// }

// // Carregar horários disponíveis
// async function loadAvailableTimeSlots(date) {
//     const doctorId = new URLSearchParams(window.location.search).get('doctor');
//     if (!doctorId) return;

//     const container = document.querySelector('.horarios-disponiveis');
//     container.innerHTML = '<p>Carregando horários...</p>';

//     try {
//         const slots = await getAvailableTimeSlots(doctorId, date.toISOString().split('T')[0]);
        
//         if (slots.length === 0) {
//             container.innerHTML = '<p>Nenhum horário disponível para esta data.</p>';
//             return;
//         }

//         container.innerHTML = slots.map(slot => {
//             const time = new Date(slot).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
//             return `
//                 <button onclick="scheduleSlot('${slot}')">
//                     ${time}
//                 </button>
//             `;
//         }).join('');
//     } catch (error) {
//         console.error('Erro ao carregar horários:', error);
//         container.innerHTML = '<p>Erro ao carregar horários. Tente novamente.</p>';
//     }
// }

// // Função para agendar um horário
// async function scheduleSlot(dateTime) {
//     // Tente obter o patientId e doctorId do localStorage ou sessionStorage
//     const patientId = localStorage.getItem('patientId') || sessionStorage.getItem('patientId');
//     const doctorId = new URLSearchParams(window.location.search).get('doctor');

//     if (!patientId || !doctorId) {
//         alert('Usuário ou médico não identificado. Faça login novamente.');
//         return;
//     }

//     try {
//         await scheduleAppointment(patientId, doctorId, dateTime);
//         // Redirecionar ou atualizar a interface após sucesso
//         window.location.href = '/especialidades.html';
//     } catch (error) {
//         // O feedback já é tratado pela função scheduleAppointment
//     }
// }

// // Inicializar o calendário quando a página carregar
// document.addEventListener('DOMContentLoaded', initializeCalendar); 