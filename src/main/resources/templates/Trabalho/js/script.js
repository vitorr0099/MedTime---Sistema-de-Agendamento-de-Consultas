const container = document.getElementById('cardContainer');
const showRegister = document.getElementById('show-register');
const showLogin = document.getElementById('show-login');

showRegister.addEventListener('click', () => {
  container.classList.add('flipped');
});

showLogin.addEventListener('click', () => {
  container.classList.remove('flipped');
});


document.addEventListener('DOMContentLoaded', function() {
  const btnCadastrar = document.getElementById('btn-cadastrar');
  if (btnCadastrar) {
      btnCadastrar.addEventListener('click', async function(e) {
          // Verifica se está na tela de cadastro
          if (document.getElementById('nome') && document.getElementById('email') && document.getElementById('new-usuario') && document.getElementById('new-senha')) {
              e.preventDefault();
              const nome = document.getElementById('nome').value;
              const email = document.getElementById('email').value;
              const usuario = document.getElementById('new-usuario').value;
              const senha = document.getElementById('new-senha').value;

              const response = await fetch('http://localhost:8080/users/createUser', {
                  method: 'POST',
                  headers: { 'Content-Type': 'application/json' },
                  body: JSON.stringify({
                      name: nome,
                      email: email,
                      password: senha,
                      // Adicione outros campos se existirem no UserDTO
                  })
              });

              if (response.ok) {
                  alert('Cadastro realizado com sucesso!');
                  // Redirecionar para login ou especialidades
                  window.location.href = 'index.html';
              } else {
                  alert('Erro ao cadastrar!');
              }
          }
      });
  }
}); 


// Login functionality
const btnLogin = document.querySelector('.btn-login');
if (btnLogin && !document.getElementById('btn-login')) {
    btnLogin.addEventListener('click', async function(e) {
        e.preventDefault();
        const email = document.getElementById('usuario').value;
        const senha = document.getElementById('senha').value;

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
                const user = await response.json();
                // Store user info in sessionStorage
                sessionStorage.setItem('user', JSON.stringify(user));
                // Salvar o id do paciente no localStorage para agendamento
                if (user.patientId) {
                    localStorage.setItem('patientId', user.patientId);
                }
                window.location.href = 'especialidades.html';
            } else {
                const error = await response.text();
                alert('Erro no login: ' + error);
            }
        } catch (error) {
            alert('Erro ao tentar fazer login. Por favor, tente novamente.');
        }
    });
}