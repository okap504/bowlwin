# ビルドステージ：Java 21 + Maven
FROM maven:3.9.0-jdk-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# 実行ステージ：軽量JDK21
FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/winLose-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
