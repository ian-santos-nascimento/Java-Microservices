FROM maven:3.8.5-openjdk-17 as build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build  /app/target/*.jar ./eureka.jar

ENTRYPOINT java -jar eureka.jar

EXPOSE 8761

ENV EUREKA_USERNAME user
ENV EUREKA_PASSWORD user

CMD ["java", "-jar", "eureka.jar"]
