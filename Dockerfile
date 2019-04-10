FROM openjdk:latest
COPY ./target/group-2-project.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "group-2-project.jar", "data:3306"]