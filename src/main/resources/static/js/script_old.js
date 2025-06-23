// Funções de inicialização
document.addEventListener('DOMContentLoaded', function() {
    // Inicializar o calendário se estiver na página de calendário
    if (document.querySelector('.calendario-container')) {
        initializeCalendar();
    }

    // Carregar médicos se estiver na página de especialidades
    const specializationId = new URLSearchParams(window.location.search).get('specialization');
    if (specializationId) {
        loadDoctors(specializationId);
    }

    // Login functionality
    const btnLogin = document.querySelector('.btn-login');
    if (btnLogin && !document.getElementById('btn-cadastrar')) {
        btnLogin.addEventListener('click', async function(e) {
            e.preventDefault();
            const email = document.getElementById('usuario').value;
            const senha = document.getElementById('senha').value;

            if (!email || !senha) {
                alert('Por favor, preencha todos os campos');
                return;
            }

            try {
                const response = await fetch('http://localhost:8080/users/login', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        email: email,
                        password: senha
                    })
                });

                if (response.ok) {
                    const userData = await response.json();
                    // Store user info in sessionStorage
                    sessionStorage.setItem('user', JSON.stringify(userData));
                    window.location.href = 'especialidades.html';
                } else {
                    const errorText = await response.text();
                    alert('Erro no login: ' + errorText);
                }
            } catch (error) {
                console.error('Login error:', error);
                alert('Erro ao tentar fazer login. Por favor, tente novamente.');
            }
        });
    }

//     // Registration functionality
//     const btnCadastrar = document.getElementById('btn-cadastrar');
//     if (btnCadastrar) {
//         btnCadastrar.addEventListener('click', async function(e) {
//             e.preventDefault();
//             const nome = document.getElementById('nome').value;
//             const email = document.getElementById('email').value;
//             const usuario = document.getElementById('new-usuario').value;
//             const senha = document.getElementById('new-senha').value;

//             const response = await fetch('http://localhost:8080/users/createUser', {
//                 method: 'POST',
//                 headers: { 'Content-Type': 'application/json' },
//                 body: JSON.stringify({
//                     name: nome,
//                     email: email,
//                     password: senha
//                 })
//             });

//             if (response.ok) {
//                 alert('Cadastro realizado com sucesso!');
//                 window.location.href = 'index.html';
//             } else {
//                 alert('Erro ao cadastrar!');
//             }
//         });
//     }
// });

// Funções de navegação
function navigateToSpecialization(specializationId) {
    window.location.href = `especialidades.html?specialization=${specializationId}`;
}

function navigateToCalendar(doctorId) {
    window.location.href = `calendario.html?doctor=${doctorId}`;
}

// Funções de utilidade
function formatDate(date) {
    return date.toLocaleDateString('pt-BR');
}

function formatTime(time) {
    return time.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
} 