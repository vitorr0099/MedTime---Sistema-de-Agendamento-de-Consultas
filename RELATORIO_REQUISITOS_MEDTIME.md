# Relatório de Requisitos do Sistema MedTime

## **REQUISITOS FUNCIONAIS**

### **1. Gestão de Usuários**
- **RF001**: Sistema deve permitir cadastro de usuários (pacientes) com nome, email e senha
- **RF002**: Sistema deve permitir cadastro de médicos com CRM, especialização e foto
- **RF003**: Sistema deve permitir autenticação de usuários (login/logout)
- **RF004**: Sistema deve diferenciar tipos de usuário (PACIENTE e MÉDICO)
- **RF005**: Sistema deve permitir atualização de dados do usuário
- **RF006**: Sistema deve permitir exclusão de usuários

### **2. Gestão de Especialidades Médicas**
- **RF007**: Sistema deve manter cadastro de especialidades médicas (Cardiologia, Neurologia, Ginecologia, Ortopedia)
- **RF008**: Sistema deve permitir consulta de especialidades disponíveis
- **RF009**: Sistema deve associar médicos às suas respectivas especialidades

### **3. Gestão de Médicos**
- **RF010**: Sistema deve permitir visualização de médicos por especialidade
- **RF011**: Sistema deve exibir informações detalhadas do médico (nome, CRM, descrição, foto)
- **RF012**: Sistema deve permitir busca de médicos específicos por ID

### **4. Agendamento de Consultas**
- **RF013**: Sistema deve permitir agendamento de consultas entre pacientes e médicos
- **RF014**: Sistema deve verificar disponibilidade de horários antes do agendamento
- **RF015**: Sistema deve gerar slots de horários disponíveis (30 minutos cada)
- **RF016**: Sistema deve permitir seleção de data e horário para consulta
- **RF017**: Sistema deve impedir agendamentos em horários já ocupados
- **RF018**: Sistema deve definir horário de funcionamento (8:00 às 18:00)

### **5. Gestão de Agendamentos**
- **RF019**: Sistema deve permitir consulta de agendamentos por paciente
- **RF020**: Sistema deve exibir histórico de consultas do paciente
- **RF021**: Sistema deve manter status dos agendamentos (SCHEDULED, COMPLETED, CANCELLED)
- **RF022**: Sistema deve permitir visualização de detalhes do agendamento (médico, especialidade, data/hora)
- **RF023**: Sistema deve permitir cancelamento de agendamentos por pacientes
- **RF024**: Sistema deve validar prazo de cancelamento (máximo 24 horas antes da consulta)
- **RF025**: Sistema deve impedir cancelamento de agendamentos já realizados
- **RF026**: Sistema deve impedir cancelamento de agendamentos já cancelados
- **RF027**: Sistema deve exibir status visual dos agendamentos (Agendado, Cancelado, Realizado)
- **RF028**: Sistema deve fornecer feedback visual sobre possibilidade de cancelamento

### **6. Interface do Usuário**
- **RF029**: Sistema deve fornecer interface web responsiva para pacientes
- **RF030**: Sistema deve permitir navegação entre especialidades médicas
- **RF031**: Sistema deve exibir calendário interativo para seleção de datas
- **RF032**: Sistema deve mostrar horários disponíveis de forma clara
- **RF033**: Sistema deve permitir acesso aos agendamentos do usuário logado
- **RF034**: Sistema deve exibir badges de status nos agendamentos
- **RF035**: Sistema deve mostrar mensagens de aviso quando cancelamento não for possível
- **RF036**: Sistema deve fornecer confirmação visual após cancelamento bem-sucedido

## **REQUISITOS NÃO FUNCIONAIS**

### **1. Performance**
- **RNF001**: Sistema deve responder a requisições em menos de 3 segundos
- **RNF002**: Sistema deve suportar múltiplos usuários simultâneos
- **RNF003**: Sistema deve otimizar consultas ao banco de dados
- **RNF004**: Sistema deve validar horários de cancelamento em tempo real

### **2. Segurança**
- **RNF005**: Sistema deve implementar autenticação de usuários
- **RNF006**: Sistema deve validar dados de entrada
- **RNF007**: Sistema deve proteger contra ataques CSRF (desabilitado atualmente)
- **RNF008**: Sistema deve criptografar senhas dos usuários
- **RNF009**: Sistema deve validar permissões para cancelamento de agendamentos

### **3. Confiabilidade**
- **RNF010**: Sistema deve validar disponibilidade de horários antes do agendamento
- **RNF011**: Sistema deve impedir agendamentos duplicados
- **RNF012**: Sistema deve tratar exceções e erros adequadamente
- **RNF013**: Sistema deve registrar logs de operações importantes
- **RNF014**: Sistema deve validar regras de negócio para cancelamentos
- **RNF015**: Sistema deve impedir cancelamentos fora do prazo permitido
- **RNF016**: Sistema deve validar status atual do agendamento antes do cancelamento

### **4. Usabilidade**
- **RNF017**: Interface deve ser intuitiva e fácil de navegar
- **RNF018**: Sistema deve fornecer feedback visual para ações do usuário
- **RNF019**: Sistema deve ser responsivo para diferentes dispositivos
- **RNF020**: Sistema deve apresentar mensagens de erro claras
- **RNF021**: Sistema deve exibir status dos agendamentos de forma visual
- **RNF022**: Sistema deve fornecer feedback imediato sobre possibilidade de cancelamento
- **RNF023**: Sistema deve recarregar dados após operações de cancelamento

### **5. Arquitetura e Tecnologia**
- **RNF024**: Sistema deve ser desenvolvido em Java com Spring Boot
- **RNF025**: Sistema deve utilizar banco de dados MySQL
- **RNF026**: Sistema deve seguir padrão REST para APIs
- **RNF027**: Sistema deve implementar arquitetura em camadas (Controller, Service, Repository)
- **RNF028**: Sistema deve utilizar JPA/Hibernate para persistência
- **RNF029**: Sistema deve implementar validações de negócio na camada de serviço

### **6. Disponibilidade**
- **RNF030**: Sistema deve estar disponível 24/7
- **RNF031**: Sistema deve permitir agendamentos em tempo real
- **RNF032**: Sistema deve sincronizar dados entre frontend e backend
- **RNF033**: Sistema deve permitir cancelamentos em tempo real

### **7. Escalabilidade**
- **RNF034**: Sistema deve permitir adição de novas especialidades médicas
- **RNF035**: Sistema deve suportar crescimento do número de usuários
- **RNF036**: Sistema deve permitir expansão de funcionalidades
- **RNF037**: Sistema deve permitir configuração de regras de cancelamento flexíveis

### **8. Manutenibilidade**
- **RNF038**: Código deve seguir boas práticas de desenvolvimento
- **RNF039**: Sistema deve utilizar logging para monitoramento
- **RNF040**: Sistema deve ter documentação clara das APIs
- **RNF041**: Sistema deve permitir deploy via Docker
- **RNF042**: Sistema deve implementar tratamento de exceções consistente
