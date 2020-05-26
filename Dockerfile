FROM java:8
WORKDIR /app
COPY projectx-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]