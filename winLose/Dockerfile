# ビルドステージ
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -Dmaven.test.skip=true

# 実行ステージ
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/winLose-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]