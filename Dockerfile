# Use uma imagem base oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR da aplicação para o diretório de trabalho
COPY target/rabbitmq-1.0.jar /opt/app.jar

# Exponha a porta 8080 para permitir tráfego HTTP
EXPOSE 8080

# Define o comando padrão para executar a aplicação
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
