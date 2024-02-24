FROM maven:3.8.5-openjdk-17 as build

WORKDIR /msgateway

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /msgateway

COPY --from=build  /msgateway/target/*.jar ./msgateway.jar

EXPOSE 8080

ENTRYPOINT java -jar msgateway.jar


ENV EUREKA_USERNAME user
ENV EUREKA_PASSWORD user

CMD ["java", "-jar", "msgateway.jar"]
