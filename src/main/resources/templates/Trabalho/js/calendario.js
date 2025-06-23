const currentDate = document.querySelector(".data-atual"),
daysTag = document.querySelector(".dias");
const prevNextIcon = document.querySelectorAll(".icones span");

let date = new Date(),
currYear = date.getFullYear(),
currMonth = date.getMonth();

const months = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

// Função para verificar disponibilidade do dia
async function checkDayAvailability(year, month, day) {
    const doctorId = localStorage.getItem('selectedDoctorId');
    if (!doctorId) return false;

    const dateStr = `${year}-${String(month + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    const slots = await getAvailableTimeSlots(doctorId, dateStr);
    return slots.length > 0;
}

const renderCalendario = async () => {
    let firstDateofMonth = new Date(currYear, currMonth, 1).getDay();
    let lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate();
    let lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay();
    let lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();
    let liTag = "";

    // Dias do mês anterior
    for (let i = firstDateofMonth; i > 0; i--) {
        liTag += `<li class="inativo">${lastDateofLastMonth - i + 1}</li>`;
    }

    // Dias do mês atual
    for (let i = 1; i <= lastDateofMonth; i++) {
        let isToday = i === date.getDate() && currMonth === new Date().getMonth()
                      && currYear === new Date().getFullYear() ? "ativo" : "";
        
        // Verificar se é um dia passado
        const isPastDay = new Date(currYear, currMonth, i) < new Date().setHours(0,0,0,0);
        if (isPastDay) {
            liTag += `<li class="inativo">${i}</li>`;
            continue;
        }

        // Adicionar data-date para referência
        liTag += `<li class="${isToday}" data-date="${i}" onclick="handleDayClick(${currYear}, ${currMonth}, ${i})">${i}</li>`;
    }

    // Dias do próximo mês
    for (let i = lastDayofMonth; i < 6; i++) {
        liTag += `<li class="inativo">${i - lastDayofMonth + 1}</li>`;
    }

    currentDate.innerText = `${months[currMonth]} ${currYear}`;
    daysTag.innerHTML = liTag;

    // Verificar disponibilidade para cada dia do mês atual
    for (let i = 1; i <= lastDateofMonth; i++) {
        const dayElement = document.querySelector(`[data-date="${i}"]`);
        if (dayElement && !dayElement.classList.contains('inativo')) {
            const hasSlots = await checkDayAvailability(currYear, currMonth, i);
            if (hasSlots) {
                dayElement.classList.add('available');
            }
        }
    }
    // Adicionar seleção ao dia corrente
    const today = new Date();
    if (currYear === today.getFullYear() && currMonth === today.getMonth()) {
        const todayElement = document.querySelector(`.dias li[data-date='${today.getDate()}']`);
        if (todayElement) todayElement.classList.add('selected');
    }
}

// Função para lidar com o clique em um dia
async function handleDayClick(year, month, day) {
    // Remover seleção de todos os dias
    document.querySelectorAll('.dias li').forEach(li => li.classList.remove('selected'));
    // Adicionar seleção ao dia clicado
    const clickedDay = document.querySelector(`.dias li[data-date='${day}']`);
    if (clickedDay) clickedDay.classList.add('selected');
    const dateStr = `${year}-${String(month + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    await loadAvailableSlots(dateStr);
}

// Inicializar calendário
renderCalendario();

// Navegação entre meses
prevNextIcon.forEach(icon => {
    icon.addEventListener("click", () => {
        currMonth = icon.id === "anterior" ? currMonth - 1 : currMonth + 1;

        if (currMonth < 0 || currMonth > 11) {
            date = new Date(currYear, currMonth);
            currYear = date.getFullYear();
            currMonth = date.getMonth();
        }

        renderCalendario();


        
    });
});