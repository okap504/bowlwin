# ベースイメージ（Java 21 を使用）
FROM openjdk:21-jdk

# JARファイルをコンテナにコピー
COPY target/*.jar app.jar

# アプリ起動
ENTRYPOINT ["java", "-jar", "app.jar"]