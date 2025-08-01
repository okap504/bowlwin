# ビルドステージ：Mavenでビルド（Java 21 + Maven公式イメージ）
FROM maven:3.9.0-eclipse-temurin-21 AS build

WORKDIR /app

# pom.xmlを先にコピーして依存解決をキャッシュしやすくする
COPY pom.xml .

# ソースコードをコピー
COPY src ./src

# ビルド実行（テストはスキップ）
RUN mvn clean package -DskipTests

# 実行ステージ：軽量なJDKイメージ
FROM openjdk:21-jdk

WORKDIR /app

# ビルドステージから生成したJARファイルをコピー
COPY --from=build /app/target/winLose-0.0.1-SNAPSHOT.jar app.jar

# アプリ起動コマンド
CMD ["java", "-jar", "app.jar"]
