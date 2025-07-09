# Etapa 1: Build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app

# Copia arquivos de configuração para cache de dependências
COPY pom.xml .

# Baixa as dependências (isso evita baixar toda hora, se o pom.xml não mudou)
RUN mvn dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Builda o projeto, ignorando os testes para agilizar
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final, só com o JDK e o jar pronto
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=builder /app/target/*.jar app.jar

# Define variáveis de ambiente para passar opções de JVM (opcional)
ENV JAVA_OPTS=""

# Comando para rodar a aplicação
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
