# ベースイメージとしてOpenJDK17を使用（必要に応じてバージョン変更）
FROM eclipse-temurin:17-jdk

# JARファイルをコンテナにコピー
COPY target/*.jar app.jar

# アプリを起動
ENTRYPOINT ["java", "-jar", "/app.jar"]