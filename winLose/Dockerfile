# ベースイメージ（Java 21 を使用）
FROM openjdk:21-jdk

# ② 作業ディレクトリを作成（/appという名前）
WORKDIR /app

# ③ JARファイルをコピー（名前はあなたのJAR名に合わせる）
COPY target/winLose-0.0.1-SNAPSHOT.jar app.jar

# ④ アプリケーションを実行する
CMD ["java", "-jar", "app.jar"]