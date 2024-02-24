FROM maven:3.8.5-openjdk-17 as build

WORKDIR /msclient

COPY . .

RUN if [ -d "target" ]; then \
        mvn clean package -DskipTests; \
    else \
        mvn package -DskipTests; \
    fi

FROM openjdk:17-jdk-alpine

WORKDIR /msclient

COPY --from=build  /msclient/target/*.jar ./msclient.jar

ENTRYPOINT java -jar msclient.jar


ENV EUREKA_USERNAME user
ENV EUREKA_PASSWORD user

CMD ["java","-Dspring.profiles.active=dev" ,"-jar", "msclient.jar"]
