FROM openjdk:21-jdk-slim

WORKDIR /app

# Copiar o arquivo JAR do projeto
COPY target/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"] 