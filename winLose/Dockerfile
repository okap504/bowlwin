# ビルドステージ：Maven + JDK 17（安定して利用できる）
FROM maven:3.9.4-jdk-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# 実行ステージ：Java 21ランタイム
FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/winLose-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
