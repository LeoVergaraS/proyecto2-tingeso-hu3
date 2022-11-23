FROM openjdk:17 
ARG JAR_FILE=target/autorizacion-service.jar
COPY ${JAR_FILE} autorizacion-service.jar
ENTRYPOINT ["java","-jar","/autorizacion-service.jar"]