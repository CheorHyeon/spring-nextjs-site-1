# 첫 번째 스테이지: 빌드 스테이지
FROM gradle:jdk21-graal-jammy AS builder
# 작업 디렉토리 설정
WORKDIR /app
# 소스 코드와 Gradle 래퍼 복사
COPY build.gradle .
COPY settings.gradle .
# Gradle 래퍼에 실행 권한 부여
RUN gradle wrapper
# 종속성 설치
RUN ./gradlew dependencies --no-daemon
# 소스 코드 복사
COPY src src
# 애플리케이션 빌드
RUN ./gradlew build --no-daemon
# 두 번째 스테이지: 실행 스테이지
# GraalVM을 기반으로 한 JDK 21 이미지를 사용하여 실행 환경 설정
FROM container-registry.oracle.com/graalvm/jdk:21
# 작업 디렉토리 설정
WORKDIR /app
# 첫 번째 스테이지에서 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar
# 실행할 JAR 파일 지정
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]