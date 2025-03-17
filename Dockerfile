# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 17, try this
FROM eclipse-temurin:17-alpine

ARG serviceName
ARG serviceVersion

# Refer to Maven build -> finalName
ARG JAR_FILE=target/${serviceName}-${serviceVersion}.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/$servicename-$serviceversion.jar /opt/app/app.jar
COPY target/${serviceName}-${serviceVersion}.jar app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]

## sudo docker run -p 8091:8091 -t ${project.artifactId}/${project.version}:${project.version}
## sudo docker run -p 91:8091 -t ${project.artifactId}/${project.version}:${project.version}
## sudo docker run -p 443:8443 -t ${project.artifactId}/${project.version}:${project.version}