FROM maven:3.8.5-openjdk-17 as build

WORKDIR /mscartoes

COPY . .

RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /mscartoes

COPY --from=build  /mscartoes/target/*.jar ./mscartoes.jar

ENTRYPOINT java -jar mscartoes.jar


ENV EUREKA_USERNAME user
ENV EUREKA_PASSWORD user

CMD ["java", "-jar", "mscartoes.jar"]
