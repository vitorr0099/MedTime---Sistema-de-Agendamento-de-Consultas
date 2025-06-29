# Diagrama Entidade-Relacionamento (DER) - Sistema MedTime

## Visão Geral
O sistema MedTime é uma aplicação de agendamento de consultas médicas que gerencia médicos, pacientes, especialidades e agendamentos.

## Entidades e Atributos

### 1. USERS (Usuários)
**Chave Primária:** `id` (Long, Auto-incremento)

**Atributos:**
- `id` (Long, PK, Auto-incremento)
- `name` (String, NOT NULL)
- `email` (String, NOT NULL, UNIQUE)
- `password` (String, NOT NULL)
- `userType` (UserType, NOT NULL) - Enum: PATIENT, DOCTOR
- `crm` (String) - Apenas para médicos
- `photoUrl` (String) - Apenas para médicos

**Relacionamentos:**
- `specialization_id` (FK) → SPECIALIZATIONS.id (Many-to-One)

### 2. DOCTORS (Médicos)
**Chave Primária:** `id` (Long, Auto-incremento)

**Atributos:**
- `id` (Long, PK, Auto-incremento)
- `name` (String)
- `crm` (String)
- `email` (String)
- `description` (String)
- `photo_url` (String)

**Relacionamentos:**
- `specialization_id` (FK) → SPECIALIZATIONS.id (Many-to-One)

### 3. PATIENTS (Pacientes)
**Chave Primária:** `id` (Long, Auto-incremento)

**Atributos:**
- `id` (Long, PK, Auto-incremento)
- `name` (String)
- `email` (String)
- `phone` (String)
- `cpf` (String)

### 4. SPECIALIZATIONS (Especialidades)
**Chave Primária:** `id` (Long, Auto-incremento)

**Atributos:**
- `id` (Long, PK, Auto-incremento)
- `name` (String, UNIQUE)
- `description` (String)

### 5. APPOINTMENTS (Agendamentos)
**Chave Primária:** `id` (Long, Auto-incremento)

**Atributos:**
- `id` (Long, PK, Auto-incremento)
- `appointmentDateTime` (LocalDateTime)
- `status` (String) - Valores: SCHEDULED, COMPLETED, CANCELLED

**Relacionamentos:**
- `doctor_id` (FK) → DOCTORS.id (Many-to-One)
- `patient_id` (FK) → PATIENTS.id (Many-to-One)

## Relacionamentos

### 1. USERS ↔ SPECIALIZATIONS
- **Tipo:** Many-to-One (N:1)
- **Cardinalidade:** Muitos usuários podem ter uma especialidade
- **Ocorrência:** Apenas para usuários do tipo DOCTOR

### 2. DOCTORS ↔ SPECIALIZATIONS
- **Tipo:** Many-to-One (N:1)
- **Cardinalidade:** Muitos médicos podem ter uma especialidade
- **Ocorrência:** Obrigatório para médicos

### 3. APPOINTMENTS ↔ DOCTORS
- **Tipo:** Many-to-One (N:1)
- **Cardinalidade:** Muitos agendamentos podem pertencer a um médico
- **Ocorrência:** Obrigatório

### 4. APPOINTMENTS ↔ PATIENTS
- **Tipo:** Many-to-One (N:1)
- **Cardinalidade:** Muitos agendamentos podem pertencer a um paciente
- **Ocorrência:** Obrigatório

## Diagrama em Texto (ASCII)

```
┌─────────────────┐         ┌──────────────────┐
│     USERS       │         │  SPECIALIZATIONS │
├─────────────────┤         ├──────────────────┤
│ PK id           │         │ PK id            │
│ name            │         │ name (UNIQUE)    │
│ email (UNIQUE)  │         │ description      │
│ password        │         └──────────────────┘
│ userType        │                    ↑
│ crm             │                    │
│ photoUrl        │                    │
│ FK specialization_id ────────────────┘
└─────────────────┘

┌─────────────────┐         ┌──────────────────┐
│     DOCTORS     │         │  SPECIALIZATIONS │
├─────────────────┤         ├──────────────────┤
│ PK id           │         │ PK id            │
│ name            │         │ name (UNIQUE)    │
│ crm             │         │ description      │
│ email           │         └──────────────────┘
│ description     │                    ↑
│ photo_url       │                    │
│ FK specialization_id ─────────────────┘
└─────────────────┘

┌─────────────────┐
│    PATIENTS     │
├─────────────────┤
│ PK id           │
│ name            │
│ email           │
│ phone           │
│ cpf             │
└─────────────────┘

┌─────────────────┐
│   APPOINTMENTS  │
├─────────────────┤
│ PK id           │
│ appointmentDateTime │
│ status          │
│ FK doctor_id ───┼──→ DOCTORS.id
│ FK patient_id ──┼──→ PATIENTS.id
└─────────────────┘
```

## Observações Importantes

1. **Duplicação de Entidades:** O sistema possui tanto a entidade `USERS` quanto `DOCTORS` e `PATIENTS` separadamente, o que pode indicar uma redundância no modelo.

2. **UserType:** A entidade `USERS` usa um enum `UserType` para diferenciar entre pacientes e médicos.

3. **Especialidades:** Tanto `USERS` quanto `DOCTORS` podem ter especialidades, criando uma possível inconsistência no modelo.

4. **Agendamentos:** Os agendamentos relacionam-se com `DOCTORS` e `PATIENTS` separadamente, não com `USERS`.

5. **Campos Opcionais:** Alguns campos como `crm`, `photoUrl` são específicos para médicos e podem ser nulos para pacientes.

## Recomendações de Melhoria

1. **Consolidar Entidades:** Considerar unificar `USERS`, `DOCTORS` e `PATIENTS` em uma única entidade com discriminação por tipo.

2. **Normalização:** Revisar a necessidade de manter entidades separadas para médicos e pacientes.

3. **Integridade Referencial:** Garantir que as chaves estrangeiras tenham constraints apropriados.

4. **Índices:** Considerar índices para campos frequentemente consultados como `email`, `crm`, `cpf`. 