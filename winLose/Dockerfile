# ビルドステージ（JDK 21 に対応）
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

# 実行ステージ（JRE 21 に対応）
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/winLose-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]