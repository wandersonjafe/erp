# ── Stage 1: build com Maven
FROM eclipse-temurin:21-jdk-alpine AS builder

# Instala o Maven
RUN apk add --no-cache maven

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# ── Stage 2: imagem final leve
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]