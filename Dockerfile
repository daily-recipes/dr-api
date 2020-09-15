# minikube start --insecure-registry="pc:5000"
# 1. docker build api -t recipes-service:0.8
# 2. docker tag recipes-service:0.8 elio2004/recipes-service:0.8
# 3. docker push elio2004/recipes-service:0.8

FROM openjdk:15-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY build/libs/daily-recipes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]