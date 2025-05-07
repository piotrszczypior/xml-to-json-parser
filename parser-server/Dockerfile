FROM gradle:8.13.0-jdk-21-and-23 AS builder
WORKDIR /app
COPY ./parser-server ./parser-server
COPY ./parser ./parser

WORKDIR /app/parser-server
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim AS runtime
WORKDIR /app
COPY --from=builder /app/parser-server/build/libs/parser-server-*.jar parser-server.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "parser-server.jar"]