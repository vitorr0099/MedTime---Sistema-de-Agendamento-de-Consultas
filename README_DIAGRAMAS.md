# Diagramas de Classes UML - MedTime

Este diretório contém os diagramas de classes UML do projeto MedTime, um sistema de agendamento de consultas médicas.

## Arquivos Disponíveis

### 1. `DIAGRAMA_CLASSES_UML.md`
- **Formato**: Markdown com Mermaid
- **Descrição**: Diagrama de classes em formato Mermaid que pode ser visualizado diretamente no GitHub ou em editores que suportam Mermaid
- **Como visualizar**: 
  - No GitHub: O diagrama será renderizado automaticamente
  - Em editores como VS Code: Instale a extensão "Mermaid Preview"
  - Online: Use o [Mermaid Live Editor](https://mermaid.live/)

### 2. `diagrama_classes_plantuml.puml`
- **Formato**: PlantUML
- **Descrição**: Diagrama de classes em formato PlantUML, mais detalhado e profissional
- **Como visualizar**:
  - **Online**: Use o [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/)
  - **VS Code**: Instale a extensão "PlantUML"
  - **IntelliJ IDEA**: Instale o plugin "PlantUML integration"
  - **Eclipse**: Instale o plugin "PlantUML"

## Estrutura do Sistema

O sistema MedTime possui as seguintes classes principais:

### Entidades de Domínio
- **User**: Classe principal que representa usuários (médicos e pacientes)
- **Doctor**: Representa médicos com informações específicas
- **Patient**: Representa pacientes
- **Specialization**: Representa especialidades médicas
- **Appointment**: Representa agendamentos de consultas
- **UserType**: Enumeração dos tipos de usuário

### DTOs (Data Transfer Objects)
- **UserDTO**: Para transferência de dados de usuário
- **DoctorDTO**: Para transferência de dados de médico
- **AppointmentDTO**: Para transferência de dados de agendamento
- **AppointmentRequest**: Para requisições de agendamento

## Relacionamentos Principais

1. **User ↔ Specialization**: Muitos usuários podem ter uma especialização
2. **Doctor ↔ Specialization**: Muitos médicos podem ter uma especialização
3. **Appointment ↔ Doctor**: Muitos agendamentos podem pertencer a um médico
4. **Appointment ↔ Patient**: Muitos agendamentos podem pertencer a um paciente
5. **User ↔ UserType**: Cada usuário tem um tipo específico

## Como Usar os Diagramas

### Para Visualização Rápida
Use o arquivo `DIAGRAMA_CLASSES_UML.md` que contém o diagrama em formato Mermaid.

### Para Apresentações e Documentação
Use o arquivo `diagrama_classes_plantuml.puml` que gera um diagrama mais profissional e detalhado.

### Para Edição
1. Abra o arquivo `.puml` em um editor que suporte PlantUML
2. Faça as modificações necessárias
3. Gere a imagem do diagrama
4. Atualize o arquivo Markdown se necessário

## Tecnologias Utilizadas

- **JPA/Hibernate**: Para persistência de dados
- **Spring Boot**: Framework principal
- **Lombok**: Para redução de código boilerplate
- **Jakarta Persistence**: Para anotações de mapeamento ORM

## Observações Importantes

1. O sistema utiliza anotações JPA para mapeamento objeto-relacional
2. Existem relacionamentos Many-to-One entre as entidades
3. Os DTOs são utilizados para transferência de dados entre camadas
4. O sistema suporta tanto médicos quanto pacientes através da classe User
5. As classes estão organizadas seguindo padrões de projeto Java

## Comandos Úteis

### Para gerar imagem do PlantUML:
```bash
# Se você tiver o PlantUML instalado
plantuml diagrama_classes_plantuml.puml

# Ou use o servidor online
curl -X POST -d @diagrama_classes_plantuml.puml http://www.plantuml.com/plantuml/png/
```

### Para visualizar Mermaid:
- Cole o código do diagrama em: https://mermaid.live/
- Ou use a extensão Mermaid Preview no VS Code 