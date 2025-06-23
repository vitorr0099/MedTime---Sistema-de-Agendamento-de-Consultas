# Diagrama de Classes UML - MedTime

## Visão Geral
Este documento apresenta o diagrama de classes UML do sistema MedTime, um sistema de agendamento de consultas médicas.

## Diagrama de Classes

```mermaid
classDiagram
    %% Enumerações
    class UserType {
        <<enumeration>>
        PATIENT
        DOCTOR
    }

    %% Entidades Principais
    class User {
        -Long id
        -String name
        -String email
        -String password
        -UserType userType
        -String crm
        -String photoUrl
        -Specialization specialization
        +User(name, email, password)
        +User(name, email, password, crm, specialization)
        +User(name, email, password, crm, specialization, photoUrl)
        +getId() Long
        +setId(id) void
        +getName() String
        +setName(name) void
        +getEmail() String
        +setEmail(email) void
        +getPassword() String
        +setPassword(password) void
        +getUserType() UserType
        +setUserType(userType) void
        +getCrm() String
        +setCrm(crm) void
        +getSpecialization() Specialization
        +setSpecialization(specialization) void
        +getPhotoUrl() String
        +setPhotoUrl(photoUrl) void
    }

    class Specialization {
        -Long id
        -String name
        -String description
        +Specialization(name)
        +getId() Long
        +setId(id) void
        +getName() String
        +setName(name) void
    }

    class Doctor {
        -Long id
        -String name
        -String crm
        -String email
        -String description
        -String photo_url
        -Specialization specialization
    }

    class Patient {
        -Long id
        -String name
        -String email
        -String phone
        -String cpf
    }

    class Appointment {
        -Long id
        -Doctor doctor
        -Patient patient
        -LocalDateTime appointmentDateTime
        -String status
        +Appointment(patient, doctor, appointmentDateTime)
        +getId() Long
        +setId(id) void
        +getPatient() Patient
        +setPatient(patient) void
        +getDoctor() Doctor
        +setDoctor(doctor) void
        +getAppointmentDateTime() LocalDateTime
        +setAppointmentDateTime(appointmentDateTime) void
        +getStatus() String
        +setStatus(status) void
    }

    %% DTOs
    class UserDTO {
        -Long id
        -String name
        -String email
        -String password
    }

    class DoctorDTO {
        -Long id
        -String name
        -String email
        -String password
        -String crm
        -Long specializationId
        -String photo_url
    }

    class AppointmentDTO {
        -Long doctorId
        -LocalDateTime appointmentDateTime
    }

    class AppointmentRequest {
        -Long doctorId
        -LocalDateTime appointmentDateTime
    }

    %% Relacionamentos
    User ||--o{ Specialization : has
    Doctor ||--o{ Specialization : has
    Appointment ||--o{ Doctor : has
    Appointment ||--o{ Patient : has
    User ||--o{ UserType : has

    %% Herança (se aplicável)
    Doctor --|> User : extends
    Patient --|> User : extends
```

## Descrição das Classes

### Entidades Principais

1. **User**
   - Classe principal que representa usuários do sistema
   - Pode ser tanto médico quanto paciente
   - Contém informações básicas como nome, email, senha
   - Para médicos, inclui CRM e especialização
   - Para médicos, pode incluir URL da foto

2. **UserType**
   - Enumeração que define os tipos de usuário
   - PATIENT: Paciente
   - DOCTOR: Médico

3. **Specialization**
   - Representa as especialidades médicas
   - Contém nome e descrição da especialidade

4. **Doctor**
   - Representa médicos no sistema
   - Contém informações específicas como CRM, descrição, foto
   - Relacionada com uma especialização

5. **Patient**
   - Representa pacientes no sistema
   - Contém informações como telefone e CPF

6. **Appointment**
   - Representa agendamentos de consultas
   - Relaciona um médico com um paciente
   - Contém data/hora do agendamento e status

### DTOs (Data Transfer Objects)

1. **UserDTO**
   - DTO para transferência de dados de usuário
   - Contém informações básicas do usuário

2. **DoctorDTO**
   - DTO para transferência de dados de médico
   - Inclui informações específicas do médico

3. **AppointmentDTO**
   - DTO para transferência de dados de agendamento
   - Contém ID do médico e data/hora do agendamento

4. **AppointmentRequest**
   - DTO para requisições de agendamento
   - Similar ao AppointmentDTO

## Relacionamentos

- **User ↔ Specialization**: Muitos usuários podem ter uma especialização (Many-to-One)
- **Doctor ↔ Specialization**: Muitos médicos podem ter uma especialização (Many-to-One)
- **Appointment ↔ Doctor**: Muitos agendamentos podem pertencer a um médico (Many-to-One)
- **Appointment ↔ Patient**: Muitos agendamentos podem pertencer a um paciente (Many-to-One)
- **User ↔ UserType**: Cada usuário tem um tipo específico (One-to-One)

## Observações

1. O sistema utiliza JPA/Hibernate para persistência
2. As classes estão anotadas com `@Entity` para mapeamento ORM
3. Existem relacionamentos Many-to-One entre as entidades
4. Os DTOs são utilizados para transferência de dados entre camadas
5. O sistema suporta tanto médicos quanto pacientes através da classe User 