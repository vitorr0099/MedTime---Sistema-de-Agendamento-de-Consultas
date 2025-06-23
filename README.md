# 🏥 MedTime - Sistema de Agendamento Médico

Sistema web para agendamento de consultas médicas desenvolvido em Java com Spring Boot.

## 📋 Descrição

O MedTime é uma aplicação web que permite aos pacientes agendar consultas médicas com médicos de diferentes especialidades. O sistema oferece uma interface intuitiva para visualização de médicos, especialidades e agendamento de consultas.

## 🚀 Funcionalidades

- **Cadastro e Login de Usuários**: Suporte para médicos e pacientes
- **Gestão de Especialidades**: Cadastro e visualização de especialidades médicas
- **Gestão de Médicos**: Cadastro de médicos com informações detalhadas
- **Agendamento de Consultas**: Sistema completo de agendamento
- **Interface Responsiva**: Design moderno e adaptável

## 🛠️ Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3.x
- **Frontend**: HTML, CSS, JavaScript
- **Banco de Dados**: MySQL/PostgreSQL
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **Containerização**: Docker

## 📁 Estrutura do Projeto

```
medTime/
├── src/
│   ├── main/
│   │   ├── java/com/medTime/
│   │   │   ├── controller/     # Controladores REST
│   │   │   ├── model/         # Entidades JPA
│   │   │   ├── repository/    # Repositórios de dados
│   │   │   ├── service/       # Lógica de negócio
│   │   │   ├── dto/          # Data Transfer Objects
│   │   │   └── config/       # Configurações
│   │   └── resources/
│   │       ├── static/       # Arquivos estáticos (HTML, CSS, JS)
│   │       └── templates/    # Templates Thymeleaf
│   └── test/                 # Testes unitários
├── docker-compose.yaml       # Configuração Docker
├── pom.xml                   # Dependências Maven
└── README.md                 # Este arquivo
```

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL ou PostgreSQL
- Git

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/medTime.git
cd medTime
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/medtime
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurações JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 3. Execute o projeto

```bash
# Compilar e executar
mvn spring-boot:run

# Ou compilar primeiro
mvn clean compile
mvn spring-boot:run
```

### 4. Acesse a aplicação

Abra seu navegador e acesse: `http://localhost:8080`

## 🐳 Executando com Docker

### Usando Docker Compose

```bash
# Construir e executar
docker-compose up --build

# Executar em background
docker-compose up -d
```

### Usando Docker individual

```bash
# Construir a imagem
docker build -t medtime .

# Executar o container
docker run -p 8080:8080 medtime
```

## 📊 Diagrama de Classes

O projeto inclui diagramas UML detalhados:

- [Diagrama de Classes UML](DIAGRAMA_CLASSES_UML.md) - Visualização em Mermaid
- [Diagrama PlantUML](diagrama_classes_plantuml.puml) - Formato profissional
- [Guia dos Diagramas](README_DIAGRAMAS.md) - Instruções de uso

## 🧪 Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com cobertura
mvn jacoco:report
```

## 📝 API Endpoints

### Usuários
- `GET /api/users` - Listar usuários
- `POST /api/users` - Criar usuário
- `GET /api/users/{id}` - Buscar usuário por ID

### Médicos
- `GET /api/doctors` - Listar médicos
- `POST /api/doctors` - Criar médico
- `GET /api/doctors/{id}` - Buscar médico por ID

### Agendamentos
- `GET /api/appointments` - Listar agendamentos
- `POST /api/appointments` - Criar agendamento
- `PUT /api/appointments/{id}` - Atualizar agendamento

### Especialidades
- `GET /api/specializations` - Listar especialidades
- `POST /api/specializations` - Criar especialidade

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Autores

- **Seu Nome** - *Desenvolvimento inicial* - [SeuGitHub](https://github.com/seu-usuario)

## 🙏 Agradecimentos

- Professor e colegas da disciplina
- Comunidade Spring Boot
- Contribuidores do projeto

## 📞 Suporte

Se você encontrar algum problema ou tiver dúvidas, abra uma [issue](https://github.com/seu-usuario/medTime/issues) no GitHub.

---

⭐ Se este projeto te ajudou, considere dar uma estrela no repositório! 